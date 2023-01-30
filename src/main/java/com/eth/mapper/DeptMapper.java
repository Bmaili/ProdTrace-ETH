package com.eth.mapper;

import com.eth.entity.Dept;
import com.eth.form.DeptListForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptMapper {
    Dept selectById(String deptId);

    List<Dept> selectDeptList(DeptListForm form);
}
