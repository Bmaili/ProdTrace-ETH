package com.eth.service;

import com.eth.form.BatchForm;
import com.eth.form.BatchListForm;
import com.eth.pojo.BatchPo;
import com.eth.vo.BatchInfoVo;
import com.eth.vo.ResponseResult;

import java.util.List;

public interface BatchService {
    List<BatchPo> selectBatchList(BatchListForm form);

    BatchInfoVo getBatchById(String id);

    ResponseResult updateBatch(BatchForm form);

    ResponseResult insertBatch(BatchForm form);
}
