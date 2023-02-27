package com.eth.controller;

import com.eth.pojo.OperatorPo;
import com.eth.form.OperatorForm;
import com.eth.form.OperatorListForm;
import com.eth.service.OperatorService;
import com.eth.service.UpFileService;
import com.eth.vo.OperatorInfoVo;
import com.eth.vo.ResponseResult;
import com.eth.vo.TableDataInfo;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/operator")
@Api(tags = "操作员相关接口")
public class OperatorController {

    @Autowired
    private OperatorService operatorService;

    @ApiOperation("查询操作员列表")
    @GetMapping(name = "查询操作员列表", value = "/list")
    public TableDataInfo operatorList(@Valid OperatorListForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<OperatorPo> operatorPos = operatorService.selectOperatorList(form);
        return new TableDataInfo(operatorPos);
    }

    @ApiOperation("通过ID查询操作员信息")
    @GetMapping(name = "查询操作员")
    public ResponseResult getOperator(@Valid String operatorId) {
        OperatorInfoVo operator = operatorService.getOperatorById(operatorId);
        return new ResponseResult(operator);
    }

    @ApiOperation("通过ID删除操作员")
    @DeleteMapping(name = "删除操作员")
    public ResponseResult delOperator(@Valid String operatorId) {
        return operatorService.delOperatorById(operatorId);
    }

    @ApiOperation("更新操作员")
    @PutMapping(name = "更新操作员")
    public ResponseResult updateOperator(@RequestBody @Valid OperatorForm form) {
        return operatorService.updateOperator(form);
    }

    @ApiOperation("增加操作员")
    @PostMapping(name = "增加操作员")
    public ResponseResult addOperator(@RequestBody @Valid OperatorForm form) {
        return operatorService.insertOperator(form);
    }

    @ApiOperation("更新操作员状态")
    @PutMapping(name = "更新操作员状态", value = "/changeStatus")
    public ResponseResult changeOperatorStatus(@RequestBody @Valid OperatorForm form) {
        return operatorService.updateOperator(form);
    }

    @ApiOperation("上传头像")
    @PostMapping(name = "上传头像", value = "/avatar")
    public ResponseResult uploadAvatar(@RequestParam(value = "file", required = true) MultipartFile upload) throws IOException {
        System.out.println(upload.getSize());
        String filePath = "\\临时头像文件.jpg";
        File targetFile = new File(filePath);
        upload.transferTo(targetFile);

        return operatorService.uploadAvatar(targetFile);
    }
}
