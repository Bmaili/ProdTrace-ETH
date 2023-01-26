package com.eth.service;

import com.eth.form.OperatorListForm;
import com.eth.vo.ResponseResult;

public interface OperatorService  {
    ResponseResult getOperatorList(OperatorListForm form  );
}
