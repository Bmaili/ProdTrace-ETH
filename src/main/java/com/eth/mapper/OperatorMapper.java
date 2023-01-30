package com.eth.mapper;

import com.eth.bo.OperatorBO;
import com.eth.entity.Operator;
import com.eth.form.OperatorListForm;
import com.eth.vo.ResponseResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OperatorMapper {
    Operator selectById(String operatorId);

   List<Operator> selectOperatorList(OperatorListForm form);

    ResponseResult delById(String operatorId);

    ResponseResult updateById(String operatorId);


}