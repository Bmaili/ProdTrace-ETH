package com.eth.service.impl;

import com.eth.pojo.OperatorPo;
import com.eth.enums.ResultEnum;
import com.eth.form.OperatorForm;
import com.eth.form.OperatorListForm;
import com.eth.mapper.OperatorMapper;
import com.eth.service.OperatorService;
import com.eth.vo.OperatorInfoVO;
import com.eth.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    OperatorMapper operatorMapper;

    @Override
    public OperatorInfoVO getOperatorById(String id) {
        OperatorPo operatorPo = operatorMapper.selectById(id);
        OperatorInfoVO infoVO = new OperatorInfoVO();
        BeanUtils.copyProperties(operatorPo, infoVO);
        return infoVO;
    }

    @Override
    public ResponseResult delOperatorById(String id) {
        operatorMapper.delById(id);
        return new ResponseResult(ResultEnum.SUCCESS_OF_DELETE);
    }

    @Override
    public ResponseResult insertOperator(OperatorForm form) {
        OperatorPo operatorPo = new OperatorPo();
        BeanUtils.copyProperties(form, operatorPo);
        // String operatorId = UUID.randomUUID().toString().replaceAll("-", "").substring(0,8);
        String operatorId = String.valueOf(new SecureRandom().nextInt(99999999));
        operatorPo.setOperatorId(operatorId);
        operatorMapper.insertOperator(operatorPo);
        return new ResponseResult(ResultEnum.SUCCESS_OF_ADD);
    }

    @Override
    public ResponseResult updateOperator(OperatorForm form) {
        if(!StringUtils.hasText(form.getOperatorId())){
            return new ResponseResult(ResultEnum.BAD_REQUEST);
        }
        OperatorPo operatorPo = new OperatorPo();
        BeanUtils.copyProperties(form, operatorPo);
        operatorMapper.updateById(operatorPo);
        return new ResponseResult(ResultEnum.SUCCESS_OF_UPDATE);
    }

    @Override
    public  List<OperatorPo>  selectOperatorList(OperatorListForm form) {
        return operatorMapper.selectOperatorList(form);
    }
}

