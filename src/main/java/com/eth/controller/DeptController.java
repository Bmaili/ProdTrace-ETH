package com.eth.controller;

import com.eth.enums.ResultEnum;
import com.eth.form.DeptForm;
import com.eth.pojo.DeptPo;
import com.eth.form.DeptListForm;
import com.eth.service.DeptService;
import com.eth.service.UpFileService;
import com.eth.vo.DeptInfoVo;
import com.eth.vo.DeptListItemVo;
import com.eth.vo.ResponseResult;
import com.eth.vo.TableDataInfo;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dept")
@Api(tags = "企业操作接口")
@Slf4j
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private UpFileService upFileService;


    @ApiOperation("查询企业树状列表")
    @GetMapping(value = "/treeselect")
    public ResponseResult getTreeselect() {
        return deptService.getTreeselect();
    }

    @ApiOperation("查询企业列表")
    @GetMapping(name = "查询企业列表", value = "/list")
    public TableDataInfo deptList(@Valid DeptListForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<DeptPo> deptPos = deptService.selectDeptList(form);
        return new TableDataInfo(deptPos);
    }

    @ApiOperation("查询企业选项列表")
    @GetMapping(value = "/options")
    public ResponseResult getOptionsList(@Valid DeptForm form) {
        List<DeptListItemVo> itemVOS = deptService.selectDeptOptionsList(form);
        return new ResponseResult(itemVOS);
    }

    @ApiOperation("通过ID查询企业信息")
    @GetMapping(name = "查询企业")
    public ResponseResult getDept(@Valid String deptId) {
        DeptInfoVo info = deptService.getDeptById(deptId);
        return new ResponseResult(info);
    }

    @ApiOperation("通过ID删除企业")
    @DeleteMapping(name = "删除企业")
    public ResponseResult delDept(@Valid String deptId) {
        return deptService.delDeptById(deptId);
    }

    @ApiOperation("更新企业")
    @PutMapping(name = "更新企业")
    public ResponseResult updateDept(@RequestBody @Valid DeptForm form) {
        return deptService.updateDept(form);
    }

    @ApiOperation("增加企业")
    @PostMapping (name = "增加企业")
    public ResponseResult addDept(@RequestBody @Valid DeptForm form) {
        return deptService.insertDept(form);
    }

    @ApiOperation("上传企业资料图片")
    @PostMapping(value = "/upPic")
    public ResponseResult uploadFile(@RequestParam(value = "file", required = true) MultipartFile upload) {
        Map map = null;
        try {
             map = upFileService.upPicture(upload);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return new ResponseResult(ResultEnum.RUNTIME_ERROR);
        }
        return new ResponseResult(ResultEnum.SUCCESS_OF_ADD,map);
    }
}
