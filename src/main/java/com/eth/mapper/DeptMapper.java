package com.eth.mapper;

import com.eth.pojo.DeptPo;
import com.eth.form.DeptListForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptMapper {
    DeptPo selectById(String deptId);

    List<DeptPo> selectDeptList(DeptListForm form);

    void delById(String deptId);

    void updateById(DeptPo dept);

    void insertDept(DeptPo dept);
}
