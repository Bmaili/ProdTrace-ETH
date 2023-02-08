package com.eth.controller;

import com.eth.form.flow.CreateFlowForm;
import com.eth.form.flow.ProcessFlowForm;
import com.eth.form.flow.SaleFlowForm;
import com.eth.form.flow.TransportFlowForm;
import com.eth.service.FlowService;
import com.eth.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(tags = "流程操作接口")
public class FlowController {

    @Autowired
    private FlowService flowService;

    @ApiOperation("添加生厂流程")
    @PostMapping(name = "添加生厂流程", value = "/createFlow")
    public ResponseResult addCreateFlow(@RequestBody @Valid CreateFlowForm form) {
        return flowService.addCreateFlow(form);
    }

    @ApiOperation("添加加工流程")
    @PostMapping(name = "添加加工流程", value = "/processFlow")
    public ResponseResult addProcessFlow(@RequestBody @Valid ProcessFlowForm form) {
        return flowService.addProcessFlow(form);
    }

    @ApiOperation("添加物流运输流程")
    @PostMapping(name = "添加加工流程", value = "/transportFlow")
    public ResponseResult addTransportFlow(@RequestBody @Valid TransportFlowForm form) {
        return flowService.addTransportFlow(form);
    }

    @ApiOperation("添加销售流程")
    @PostMapping(name = "添加销售流程", value = "/saleFlow")
    public ResponseResult addSaleFlow(@RequestBody @Valid SaleFlowForm form) {
        return flowService.addSaleFlow(form);
    }
}
