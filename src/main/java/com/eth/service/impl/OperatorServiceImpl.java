package com.eth.service.impl;

import com.eth.entity.Operator;
import com.eth.form.OperatorListForm;
import com.eth.mapper.OperatorMapper;
import com.eth.service.OperatorService;
import com.eth.vo.OperatorInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    OperatorMapper operatorMapper;

    @Override
    public OperatorInfoVO getOperatorById(String id) {
        Operator operator = operatorMapper.selectById(id);
        OperatorInfoVO infoVO = new OperatorInfoVO();
        BeanUtils.copyProperties(operator, infoVO);
        return infoVO;
    }

    @Override
    public  List<Operator>  selectOperatorList(OperatorListForm form) {
        return operatorMapper.selectOperatorList(form);
    }
}

