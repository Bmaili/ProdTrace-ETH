package com.eth.service.impl;

import com.eth.ethereum.EthUtils;
import com.eth.form.flow.CreaterFlowForm;
import com.eth.service.FlowService;
import com.eth.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
public class FlowServiceImpl implements FlowService {
    @Autowired
    private EthUtils ethUtils;

    private String dateFormat  = "yyyy-MM-dd HH:mm:ss";
    @Override
    public ResponseResult addCreaterFlow(CreaterFlowForm form) {
        Map<String, String> map = blockJsonOfCreater(form);
        ethUtils.uploadToBlock("3344",map);
        return null;
    }

    public String getFlowByTraceId(String traceId){
        return ethUtils.downloadFromBlock(traceId);
    }

    private Map<String,String> blockJsonOfCreater(CreaterFlowForm form){
        HashMap<String, String> map = new LinkedHashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String df = sdf.format(new Date());
        map.put("产品编号",form.getProdId());
        map.put("产品名称",form.getProdName());
        map.put("产品类别",form.getCategory());
        map.put("生产商",form.getDeptName());
        map.put("操作人",form.getOperatorName());
        map.put("联系电话",form.getPhone());
        map.put("数量",form.getNum());
        map.put("计量单位",form.getUnit());
        map.put("源产地",form.getOrigin());
        map.put("保质期",form.getQuality());
        map.put("文件链接列表",form.getFileUrlList());
        map.put("文件摘要列表",form.getFileSHA256List());
        map.put("创建时间",df);
        return map;
    }
}
