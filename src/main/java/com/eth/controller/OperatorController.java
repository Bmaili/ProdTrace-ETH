package com.eth.controller;

import com.eth.entity.Operator;
import com.eth.form.OperatorListForm;
import com.eth.service.OperatorService;
import com.eth.vo.DeptInfoVO;
import com.eth.vo.OperatorInfoVO;
import com.eth.vo.ResponseResult;
import com.eth.vo.TableDataInfo;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/operator")
@Api(tags = "操作员相关接口")
public class OperatorController   {

    @Autowired
    private OperatorService operatorService;

    @ApiOperation("查询操作员列表")
    @GetMapping(name = "查询操作员列表", value = "/list")
    public TableDataInfo operatorList(@Valid OperatorListForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<Operator> operators = operatorService.selectOperatorList(form);
        return new TableDataInfo(operators);
    }

    @ApiOperation("通过ID查询操作员信息")
    @GetMapping(name = "查询操作员")
    public ResponseResult getOperator(@Valid String operatorId) {
        OperatorInfoVO operator = operatorService.getOperatorById(operatorId);
        return new ResponseResult(operator);
    }


}
