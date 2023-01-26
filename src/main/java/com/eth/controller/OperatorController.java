package com.eth.controller;

import com.eth.form.OperatorListForm;
import com.eth.service.OperatorService;
import com.eth.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;



@RestController
@RequestMapping("/operator")
@Api(tags = "操作员相关接口")
public class OperatorController   {

    @Autowired
    private OperatorService operatorService;


    @Autowired
    private HttpServletRequest httpServletRequest;

    @ApiOperation("查询操作员列表")
    @GetMapping(name = "查询操作员列表", value = "/list")
    public ResponseResult operatorList(@Valid OperatorListForm form) {
        return operatorService.getOperatorList(form);
    }


}
