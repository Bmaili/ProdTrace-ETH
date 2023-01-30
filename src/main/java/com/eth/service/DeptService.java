package com.eth.service;

import com.eth.entity.Dept;
import com.eth.form.DeptListForm;
import com.eth.vo.DeptInfoVO;
import com.eth.vo.DeptListItemVO;
import com.eth.vo.ResponseResult;

import java.util.List;

public interface DeptService {
    List<Dept> selectDeptList(DeptListForm form);

    List<DeptListItemVO>selectDeptList();

    DeptInfoVO getDeptById(String id);

    ResponseResult getTreeselect();
}
