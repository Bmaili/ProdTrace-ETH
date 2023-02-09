package com.eth.controller;

import com.eth.form.flow.CreateFlowForm;
import com.eth.form.flow.ProcessFlowForm;
import com.eth.form.flow.SaleFlowForm;
import com.eth.form.flow.TransportFlowForm;
import com.eth.service.FlowService;
import com.eth.service.UpFileService;
import com.eth.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "流程操作接口")
public class FlowController {

    @Autowired
    private FlowService flowService;

    @Autowired
    private UpFileService upFileService;

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

    @ApiOperation("查询溯源流程")
    @GetMapping(name = "查询溯源流程",value = "getTrace")
    public ResponseResult getTraceById(@Valid String traceId){
         List flowList = flowService.getFlowByTraceId(traceId);
        return new ResponseResult(flowList);
    }

    @ApiOperation("上传辅助资料")
    @PostMapping(value = "/upFile")
    public ResponseResult uploadFile(@RequestParam(value = "file", required = true) MultipartFile upload) {
        return upFileService.upFlowFile(upload);
    }
}
