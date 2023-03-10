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
        String traceId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16);
        BatchPo batchPo = new BatchPo();
        BeanUtils.copyProperties(form, batchPo);
        batchPo.setBatchId(traceId);
        batchPo.setStatus("0");
        batchMapper.insertBatch(batchPo);

        Map<String, Map> mapOfBase = blockJsonOfBaseInfo(form, traceId);
        ethUtils.uploadToBlock(traceId, mapOfBase);

        Map<String, Map> mapOfCreate = blockJsonOfCreate(form);
        ethUtils.uploadToBlock(traceId, mapOfCreate);
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

        Map<String, Map> map = blockJsonOfProcess(form);
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

        Map<String, Map> map = blockJsonOfTransport(form);
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

        Map<String, Map> map = blockJsonOfSale(form);
        ethUtils.uploadToBlock(traceId, map);
        return new ResponseResult(ResultEnum.SUCCESS_OF_ADD);
    }

    private Map<String, Map> blockJsonOfBaseInfo(CreateFlowForm form, String traceId) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("?????????", traceId);
        map.put("????????????", form.getProdId());
        map.put("????????????", form.getProdName());
        map.put("????????????", form.getCategory());

        HashMap<String, Map> jsonMap = new HashMap<>();
        jsonMap.put("????????????", map);
        return jsonMap;
    }


    private Map<String, Map> blockJsonOfCreate(CreateFlowForm form) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String df = sdf.format(new Date());
        map.put("???????????????", form.getDeptId());
        map.put("???????????????", form.getDeptName());
        map.put("?????????", form.getOperatorName());
        map.put("????????????", form.getChineseId());
        map.put("????????????", form.getPhone());
        map.put("??????", form.getNum());
        map.put("????????????", form.getUnit());
        map.put("?????????", form.getOrigin());
        map.put("?????????", form.getQuality());
        map.put("????????????", df);
        map.put("??????????????????", form.getFileList());
        map.put("??????", form.getNotes());

        HashMap<String, Map> jsonMap = new HashMap<>();
        jsonMap.put("????????????", map);
        return jsonMap;

    }

    private Map<String, Map> blockJsonOfProcess(ProcessFlowForm form) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String df = sdf.format(new Date());
        map.put("???????????????", form.getDeptId());
        map.put("???????????????", form.getDeptName());
        map.put("?????????", form.getOperatorName());
        map.put("????????????", form.getChineseId());
        map.put("????????????", form.getPhone());
        map.put("????????????", df);
        map.put("??????????????????", form.getFileList());
        map.put("??????", form.getNotes());

        HashMap<String, Map> jsonMap = new HashMap<>();
        jsonMap.put("????????????", map);
        return jsonMap;
    }

    private Map<String, Map> blockJsonOfTransport(TransportFlowForm form) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String df = sdf.format(new Date());
        map.put("???????????????", form.getDeptId());
        map.put("???????????????", form.getDeptName());
        map.put("?????????", form.getOperatorName());
        map.put("????????????", form.getChineseId());
        map.put("????????????", form.getPhone());
        map.put("?????????", form.getOrigin());
        map.put("?????????", form.getDestination());
        map.put("????????????", df);
        map.put("??????????????????", form.getFileList());
        map.put("??????", form.getNotes());

        HashMap<String, Map> jsonMap = new HashMap<>();
        jsonMap.put("????????????", map);
        return jsonMap;
    }

    private Map<String, Map> blockJsonOfSale(SaleFlowForm form) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String df = sdf.format(new Date());
        map.put("???????????????", form.getDeptId());
        map.put("???????????????", form.getDeptName());
        map.put("?????????", form.getOperatorName());
        map.put("????????????", form.getChineseId());
        map.put("????????????", form.getPhone());
        map.put("??????", form.getPrice());
        map.put("????????????", form.getAddress());
        map.put("????????????", df);
        map.put("??????????????????", form.getFileList());
        map.put("??????", form.getNotes());

        HashMap<String, Map> jsonMap = new HashMap<>();
        jsonMap.put("????????????", map);
        return jsonMap;
    }

    @Override
    public List getFlowByTraceId(String traceId) {
        return ethUtils.downloadFromBlock(traceId);
    }
}
