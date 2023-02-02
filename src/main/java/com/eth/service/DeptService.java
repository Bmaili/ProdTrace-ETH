package com.eth.service;

import com.eth.form.DeptForm;
import com.eth.pojo.DeptPo;
import com.eth.form.DeptListForm;
import com.eth.vo.DeptInfoVO;
import com.eth.vo.DeptListItemVO;
import com.eth.vo.ResponseResult;

import java.util.List;

public interface DeptService {
    List<DeptPo> selectDeptList(DeptListForm form);

    List<DeptListItemVO>selectDeptList();

    DeptInfoVO getDeptById(String id);

    ResponseResult getTreeselect();

    ResponseResult delDeptById(String id);

    ResponseResult updateDept(DeptForm form);

    ResponseResult insertDept(DeptForm form);
}
