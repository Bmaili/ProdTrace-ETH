package com.eth.controller;

import com.eth.entity.Dept;
import com.eth.entity.Operator;
import com.eth.form.DeptListForm;
import com.eth.form.OperatorListForm;
import com.eth.service.DeptService;
import com.eth.vo.DeptInfoVO;
import com.eth.vo.DeptListItemVO;
import com.eth.vo.ResponseResult;
import com.eth.vo.TableDataInfo;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dept")
@Api(tags = "企业操作接口")
public class DeptController {


    @Autowired
    private DeptService deptService;


    @ApiOperation("查询企业树状列表")
    @GetMapping( value = "/treeselect")
    public ResponseResult getTreeselect() {
        return deptService.getTreeselect();
    }

    @ApiOperation("查询企业列表")
    @GetMapping(name = "查询企业列表", value = "/list")
    public TableDataInfo deptList(@Valid DeptListForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<Dept> depts = deptService.selectDeptList(form);
        return new TableDataInfo(depts);
    }

    @ApiOperation("通过ID查询企业信息")
    @GetMapping(name = "查询企业")
    public ResponseResult getDept(@Valid String deptId) {
        DeptInfoVO info = deptService.getDeptById(deptId);
        return new ResponseResult(info);
    }

    @ApiOperation("查询企业选项列表")
    @GetMapping( value = "/listselect")
    public ResponseResult getListselect() {
        List<DeptListItemVO> itemVOS = deptService.selectDeptList();
        return new ResponseResult(itemVOS);
    }
}
