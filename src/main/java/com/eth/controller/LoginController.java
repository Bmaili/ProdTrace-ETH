package com.eth.controller;

import com.eth.form.LoginForm;
import com.eth.service.LoginService;
import com.eth.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "用户登陆登出注销接口")
public class LoginController {

    @Autowired
    LoginService loginService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ResponseResult userLogin(@RequestBody LoginForm user) {
        return loginService.login(user);
    }

    @ApiOperation("获取用户详细信息")
    @GetMapping("/getInfo")
    public ResponseResult getUserInfo() {
        return loginService.getUserInfo();
    }

    @ApiOperation("用户登出")
    @PostMapping("/user/logout")
    public ResponseResult userLogout() {
        return loginService.logout();
    }
}
