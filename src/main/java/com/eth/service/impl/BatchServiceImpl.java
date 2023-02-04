package com.eth.service.impl;

import com.eth.enums.ResultEnum;
import com.eth.form.BatchForm;
import com.eth.form.BatchListForm;
import com.eth.mapper.BatchMapper;
import com.eth.pojo.BatchPo;
import com.eth.service.BatchService;
import com.eth.vo.BatchInfoVO;
import com.eth.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class BatchServiceImpl implements BatchService {
    @Autowired
    private BatchMapper batchMapper;

    @Override
    public List<BatchPo> selectBatchList(BatchListForm form) {
        return batchMapper.selectBatchList(form);
    }

    @Override
    public BatchInfoVO getBatchById(String id) {
        BatchPo batchPo = batchMapper.selectById(id);
        BatchInfoVO infoVO = new BatchInfoVO();
        BeanUtils.copyProperties(batchPo, infoVO);
        return infoVO;
    }

    @Override
    public ResponseResult updateBatch(BatchForm form) {
        if (!StringUtils.hasText(form.getBatchId())) {
            return new ResponseResult(ResultEnum.BAD_REQUEST);
        }
        BatchPo batchPo = new BatchPo();
        BeanUtils.copyProperties(form, batchPo);
        batchMapper.updateById(batchPo);
        return new ResponseResult(ResultEnum.SUCCESS_OF_UPDATE);
    }

    @Override
    public ResponseResult insertBatch(BatchForm form) {
        BatchPo batchPo = new BatchPo();
        BeanUtils.copyProperties(form, batchPo);
        String batchId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
        batchPo.setDeptId(batchId);
        batchMapper.insertBatch(batchPo);
        return new ResponseResult(ResultEnum.SUCCESS_OF_ADD);
    }
}
