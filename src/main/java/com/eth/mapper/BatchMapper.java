package com.eth.mapper;

import com.eth.form.BatchListForm;
import com.eth.pojo.BatchPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BatchMapper {
    BatchPo selectById(String batchId);

    List<BatchPo> selectBatchList(BatchListForm form);

    void insertBatch(BatchPo batch);

    void updateById(BatchPo batch);
}
