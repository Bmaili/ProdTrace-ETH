package com.eth.mapper;

import com.eth.pojo.OperatorPo;
import com.eth.form.OperatorListForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OperatorMapper {
    OperatorPo selectById(String operatorId);

    List<OperatorPo> selectOperatorList(OperatorListForm form);

    void delById(String operatorId);

    void updateById(OperatorPo operatorPo);

    void insertOperator(OperatorPo operatorPo);


}