package com.eth.service.impl;

import com.eth.enums.ResultEnum;
import com.eth.form.DeptForm;
import com.eth.pojo.DeptPo;
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
import org.springframework.util.StringUtils;

import java.security.SecureRandom;
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
        List<DeptPo> deptPos = deptMapper.selectDeptList(new DeptListForm());
        List<selectTreeItemChildren> dept1 = deptPos.stream().filter(dept -> "1".equals(dept.getRole()))
                .map(selectTreeItemChildren::new).collect(Collectors.toList());
        List<selectTreeItemChildren> dept2 = deptPos.stream().filter(dept -> "2".equals(dept.getRole()))
                .map(selectTreeItemChildren::new).collect(Collectors.toList());
        List<selectTreeItemChildren> dept3 = deptPos.stream().filter(dept -> "3".equals(dept.getRole()))
                .map(selectTreeItemChildren::new).collect(Collectors.toList());
        List<selectTreeItemChildren> dept4 = deptPos.stream().filter(dept -> "4".equals(dept.getRole()))
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
    public ResponseResult delDeptById(String id) {
        deptMapper.delById(id);
        return new ResponseResult(ResultEnum.SUCCESS_OF_DELETE);
    }

    @Override
    public ResponseResult updateDept(DeptForm form) {
        if(!StringUtils.hasText(form.getDeptId())){
            return new ResponseResult(ResultEnum.BAD_REQUEST);
        }
        DeptPo deptPo = new DeptPo();
        BeanUtils.copyProperties(form, deptPo);
        deptMapper.updateById(deptPo);
        return new ResponseResult(ResultEnum.SUCCESS_OF_UPDATE);
    }

    @Override
    public ResponseResult insertDept(DeptForm form) {
        DeptPo deptPo = new DeptPo();
        BeanUtils.copyProperties(form, deptPo);
        String deptId = String.valueOf(new SecureRandom().nextInt(99999999));
        deptPo.setDeptId(deptId);
        deptMapper.insertDept(deptPo);
        return new ResponseResult(ResultEnum.SUCCESS_OF_ADD);
    }

    @Override
    public DeptInfoVO getDeptById(String id) {
        DeptPo deptPo = deptMapper.selectById(id);
        DeptInfoVO infoVO = new DeptInfoVO();
        BeanUtils.copyProperties(deptPo, infoVO);
        return infoVO;
    }

    @Override
    public List<DeptListItemVO> selectDeptOptionsList(DeptForm form) {
        DeptListForm listForm = new DeptListForm();
        BeanUtils.copyProperties(form,listForm);
        List<DeptPo> deptPos = deptMapper.selectDeptList(listForm);
        List<DeptListItemVO> voList = deptPos.stream().map(dept -> {
            DeptListItemVO itemVO = new DeptListItemVO();
            itemVO.setDictLabel(dept.getDeptName());
            itemVO.setDictValue(dept.getDeptId());
            return itemVO;
        }).collect(Collectors.toList());
        return voList;
    }

    @Override
    public List<DeptPo> selectDeptList(DeptListForm form) {
        return deptMapper.selectDeptList(form);
    }

    @Data
    private class selectTreeItemChildren {
        private String deptId;
        private String deptName;

        public selectTreeItemChildren(DeptPo deptPo) {
            this.deptId = deptPo.getDeptId();
            this.deptName = deptPo.getDeptName();
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
