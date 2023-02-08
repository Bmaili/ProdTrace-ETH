package com.eth.service;

import com.eth.form.flow.CreateFlowForm;
import com.eth.form.flow.ProcessFlowForm;
import com.eth.form.flow.SaleFlowForm;
import com.eth.form.flow.TransportFlowForm;
import com.eth.vo.ResponseResult;

public interface FlowService {
      ResponseResult addCreateFlow(CreateFlowForm form);

      ResponseResult addProcessFlow(ProcessFlowForm form);

      ResponseResult addTransportFlow(TransportFlowForm form);

      ResponseResult addSaleFlow(SaleFlowForm form);
}
