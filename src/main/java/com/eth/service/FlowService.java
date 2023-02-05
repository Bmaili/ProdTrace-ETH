package com.eth.service;

import com.eth.form.flow.CreaterFlowForm;
import com.eth.pojo.BlockchainTransaction;
import com.eth.vo.ResponseResult;

import java.io.IOException;

public interface FlowService {
      ResponseResult addCreaterFlow(CreaterFlowForm form);



}
