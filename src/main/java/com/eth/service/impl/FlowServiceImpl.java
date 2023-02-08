package com.eth.service.impl;

import com.eth.enums.ResultEnum;
import com.eth.ethereum.EthUtils;
import com.eth.form.flow.CreateFlowForm;
import com.eth.form.flow.ProcessFlowForm;
import com.eth.form.flow.SaleFlowForm;
import com.eth.form.flow.TransportFlowForm;
import com.eth.mapper.BatchMapper;
import com.eth.pojo.BatchPo;
import com.eth.service.FlowService;
import com.eth.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class FlowServiceImpl implements FlowService {
    @Autowired
    private EthUtils ethUtils;

    @Autowired
    private BatchMapper batchMapper;

    private String dateFormat = "yyyy-MM-dd HH:mm:ss";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult addCreateFlow(CreateFlowForm form) {
        String traceId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
        BatchPo batchPo = new BatchPo();
        BeanUtils.copyProperties(form, batchPo);
        batchPo.setBatchId(traceId);
        batchPo.setStatus("0");
        batchMapper.insertBatch(batchPo);

        Map<String, String> map = blockJsonOfCreater(form);
        ethUtils.uploadToBlock(traceId, map);
        return new ResponseResult(ResultEnum.SUCCESS_OF_ADD);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult addProcessFlow(ProcessFlowForm form) {
        String traceId = form.getBatchId();
        BatchPo batchPo = new BatchPo();
        batchPo.setBatchId(traceId);
        batchPo.setStatus("1");
        batchMapper.updateById(batchPo);

        Map<String, String> map = blockJsonOfProcess(form);
        ethUtils.uploadToBlock(traceId, map);
        return new ResponseResult(ResultEnum.SUCCESS_OF_ADD);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult addTransportFlow(TransportFlowForm form) {
        String traceId = form.getBatchId();
        BatchPo batchPo = new BatchPo();
        batchPo.setBatchId(traceId);
        batchPo.setStatus("1");
        batchMapper.updateById(batchPo);

        Map<String, String> map = blockJsonOfTransport(form);
        ethUtils.uploadToBlock(traceId, map);
        return new ResponseResult(ResultEnum.SUCCESS_OF_ADD);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult addSaleFlow(SaleFlowForm form) {
        String traceId = form.getBatchId();
        BatchPo batchPo = new BatchPo();
        batchPo.setBatchId(traceId);
        batchPo.setStatus("2");
        batchMapper.updateById(batchPo);

        Map<String, String> map = blockJsonOfSale(form);
        ethUtils.uploadToBlock(traceId, map);
        return new ResponseResult(ResultEnum.SUCCESS_OF_ADD);
    }

    private Map<String, String> blockJsonOfCreater(CreateFlowForm form) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String df = sdf.format(new Date());
        map.put("产品编号", form.getProdId());
        map.put("产品名称", form.getProdName());
        map.put("产品类别", form.getCategory());
        map.put("生产商编号", form.getDeptId());
        map.put("生产商全称", form.getDeptName());
        map.put("操作人", form.getOperatorName());
        map.put("身份证号", form.getChineseId());
        map.put("联系电话", form.getPhone());
        map.put("数量", form.getNum());
        map.put("计量单位", form.getUnit());
        map.put("源产地", form.getOrigin());
        map.put("保质期", form.getQuality());
        map.put("生产时间", df);
        map.put("文件链接列表", form.getFileUrlList());
        map.put("文件摘要列表", form.getFileSHA256List());
        return map;
    }

    private Map<String, String> blockJsonOfProcess(ProcessFlowForm form) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String df = sdf.format(new Date());
        map.put("加工商编号", form.getDeptId());
        map.put("加工商全称", form.getDeptName());
        map.put("操作人", form.getOperatorName());
        map.put("身份证号", form.getChineseId());
        map.put("联系电话", form.getPhone());
        map.put("加工时间", df);
        map.put("文件链接列表", form.getFileUrlList());
        map.put("文件摘要列表", form.getFileSHA256List());
        return map;
    }

    private Map<String, String> blockJsonOfTransport(TransportFlowForm form) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String df = sdf.format(new Date());
        map.put("运输商编号", form.getDeptId());
        map.put("运输商全称", form.getDeptName());
        map.put("操作人", form.getOperatorName());
        map.put("身份证号", form.getChineseId());
        map.put("联系电话", form.getPhone());
        map.put("始发地", form.getOrigin());
        map.put("目的地", form.getDestination());
        map.put("登记时间", df);
        map.put("文件链接列表", form.getFileUrlList());
        map.put("文件摘要列表", form.getFileSHA256List());
        return map;
    }

    private Map<String, String> blockJsonOfSale(SaleFlowForm form) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String df = sdf.format(new Date());
        map.put("销售端编号", form.getDeptId());
        map.put("销售端全称", form.getDeptName());
        map.put("操作人", form.getOperatorName());
        map.put("身份证号", form.getChineseId());
        map.put("联系电话", form.getPhone());
        map.put("报价", form.getPrice());
        map.put("上架时间", df);
        map.put("文件链接列表", form.getFileUrlList());
        map.put("文件摘要列表", form.getFileSHA256List());
        return map;
    }

    public String getFlowByTraceId(String traceId) {
        return ethUtils.downloadFromBlock(traceId);
    }
}
