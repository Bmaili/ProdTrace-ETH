package com.eth.service;

import com.eth.pojo.OperatorPo;
import com.eth.form.OperatorForm;
import com.eth.form.OperatorListForm;
import com.eth.vo.OperatorInfoVo;
import com.eth.vo.ResponseResult;

import java.util.List;

public interface OperatorService  {
    List<OperatorPo> selectOperatorList(OperatorListForm form);

    OperatorInfoVo getOperatorById(String id);

    ResponseResult delOperatorById(String id);

    ResponseResult updateOperator(OperatorForm form );

    ResponseResult insertOperator(OperatorForm form);
}
