package com.eth.service;

import com.eth.entity.Operator;
import com.eth.form.OperatorListForm;
import com.eth.vo.OperatorInfoVO;

import java.util.List;

public interface OperatorService  {
    List<Operator> selectOperatorList(OperatorListForm form);

    OperatorInfoVO getOperatorById(String id);
}
