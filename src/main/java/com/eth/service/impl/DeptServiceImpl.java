package com.eth.service.impl;

import com.eth.entity.Dept;
import com.eth.form.DeptListForm;
import com.eth.mapper.DeptMapper;
import com.eth.service.DeptService;
import com.eth.vo.DeptInfoVO;
import com.eth.vo.DeptListItemVO;
import com.eth.vo.ResponseResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public ResponseResult getTreeselect() {
        List<Dept> depts = deptMapper.selectDeptList(new DeptListForm());
        List<selectTreeItemChildren> dept1 = depts.stream().filter(dept -> "1".equals(dept.getRole()))
                .map(selectTreeItemChildren::new).collect(Collectors.toList());
        List<selectTreeItemChildren> dept2 = depts.stream().filter(dept -> "2".equals(dept.getRole()))
                .map(selectTreeItemChildren::new).collect(Collectors.toList());
        List<selectTreeItemChildren> dept3 = depts.stream().filter(dept -> "3".equals(dept.getRole()))
                .map(selectTreeItemChildren::new).collect(Collectors.toList());
        List<selectTreeItemChildren> dept4 = depts.stream().filter(dept -> "4".equals(dept.getRole()))
                .map(selectTreeItemChildren::new).collect(Collectors.toList());
        List<selectTreeItem> res = new ArrayList<>();
        res.add(new selectTreeItem(null, "全部", null));
        res.add(new selectTreeItem("1", "生产商", dept1));
        res.add(new selectTreeItem("2", "加工商", dept2));
        res.add(new selectTreeItem("3", "物流运输", dept3));
        res.add(new selectTreeItem("4", "销售终端", dept4));
        return new ResponseResult(HttpStatus.OK.value(), res);
    }

    @Override
    public DeptInfoVO getDeptById(String id) {
        Dept dept = deptMapper.selectById(id);
        DeptInfoVO infoVO = new DeptInfoVO();
        BeanUtils.copyProperties(dept, infoVO);
        return infoVO;
    }

    @Override
    public List<DeptListItemVO> selectDeptList() {
        List<Dept> depts = deptMapper.selectDeptList(new DeptListForm());
        List<DeptListItemVO> voList = depts.stream().map(dept -> {
            DeptListItemVO itemVO = new DeptListItemVO();
            itemVO.setDictLabel(dept.getDeptName());
            itemVO.setDictValue(dept.getDeptId());
            return itemVO;
        }).collect(Collectors.toList());
        return voList;
    }

    @Override
    public List<Dept> selectDeptList(DeptListForm form) {
        return deptMapper.selectDeptList(form);
    }

    @Data
    private class selectTreeItemChildren {
        private String deptId;
        private String deptName;

        public selectTreeItemChildren(Dept dept) {
            this.deptId = dept.getDeptId();
            this.deptName = dept.getDeptName();
        }
    }

    @Data
    @AllArgsConstructor
    private class selectTreeItem {
        private String role;
        private String deptName;
        private List<selectTreeItemChildren> children;
    }
}
