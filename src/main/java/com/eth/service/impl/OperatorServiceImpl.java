package com.eth.service.impl;

import com.eth.mapper.LoginMapper;
import com.eth.pojo.LoginUserPo;
import com.eth.pojo.OperatorPo;
import com.eth.enums.ResultEnum;
import com.eth.form.OperatorForm;
import com.eth.form.OperatorListForm;
import com.eth.mapper.OperatorMapper;
import com.eth.service.OperatorService;
import com.eth.service.UpFileService;
import com.eth.vo.OperatorInfoVo;
import com.eth.vo.ResponseResult;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    private OperatorMapper operatorMapper;

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private UpFileService upFileService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Value("${business.user.defaultPassword}")
    private String DEFAULT_PASSWORD;

    @Override
    public OperatorInfoVo getOperatorById(String id) {
        OperatorPo operatorPo = operatorMapper.selectById(id);
        OperatorInfoVo infoVO = new OperatorInfoVo();
        BeanUtils.copyProperties(operatorPo, infoVO);
        return infoVO;
    }

    @Override
    public ResponseResult delOperatorById(String id) {
        operatorMapper.delById(id);
        return new ResponseResult(ResultEnum.SUCCESS_OF_DELETE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult insertOperator(OperatorForm form) {
        OperatorPo operatorPo = new OperatorPo();
        BeanUtils.copyProperties(form, operatorPo);
        String operatorId = String.valueOf(new SecureRandom().nextInt(99999999));
        operatorPo.setOperatorId(operatorId);
        addOperatorLoginInfo(operatorId);
        operatorMapper.insertOperator(operatorPo);
        return new ResponseResult(ResultEnum.SUCCESS_OF_ADD);
    }

    @Override
    public ResponseResult updateOperator(OperatorForm form) {
        if (!StringUtils.hasText(form.getOperatorId())) {
            return new ResponseResult(ResultEnum.BAD_REQUEST);
        }
        OperatorPo operatorPo = new OperatorPo();
        BeanUtils.copyProperties(form, operatorPo);
        operatorMapper.updateById(operatorPo);
        return new ResponseResult(ResultEnum.SUCCESS_OF_UPDATE);
    }

    @Override
    public List<OperatorPo> selectOperatorList(OperatorListForm form) {
        return operatorMapper.selectOperatorList(form);
    }

    private int addOperatorLoginInfo(String operatorId) {
        String password = new BCryptPasswordEncoder().encode(DEFAULT_PASSWORD);
        return loginMapper.addLoginUser(operatorId, password);
    }

    @Override
    public ResponseResult uploadAvatar(File upload) {
        Map<String,String> map = null;
        try {
            map = upFileService.upPicture(upload);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return new ResponseResult(ResultEnum.RUNTIME_ERROR);
        }
        String userId = (String) httpServletRequest.getAttribute("userId");
        OperatorPo operatorPo = new OperatorPo();
        operatorPo.setOperatorId(userId);
        operatorPo.setAvatar(map.get("picPath"));
        operatorMapper.updateById(operatorPo);
        return new ResponseResult(ResultEnum.SUCCESS_OF_ADD,map);
    }
}

