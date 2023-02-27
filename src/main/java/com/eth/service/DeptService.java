package com.eth.service;

import com.eth.form.DeptForm;
import com.eth.pojo.DeptPo;
import com.eth.form.DeptListForm;
import com.eth.vo.DeptInfoVo;
import com.eth.vo.DeptListItemVo;
import com.eth.vo.ResponseResult;

import java.util.List;

public interface DeptService {
    List<DeptPo> selectDeptList(DeptListForm form);

    List<DeptListItemVo>selectDeptOptionsList(DeptForm form);

    DeptInfoVo getDeptById(String id);

    ResponseResult getTreeselect();

    ResponseResult delDeptById(String id);

    ResponseResult updateDept(DeptForm form);

    ResponseResult insertDept(DeptForm form);
}
