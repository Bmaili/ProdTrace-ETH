package com.eth.controller;

import com.eth.form.LoginForm;
import com.eth.service.LoginService;
import com.eth.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
// @RequestMapping("/user")
@Api(tags = "用户登陆登出注销接口")
public class LoginController {

    @Autowired
    LoginService loginService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ResponseResult userLogin(@RequestBody LoginForm user) {
        return loginService.login(user);
    }

    // @ApiOperation("用户登出")
    // @GetMapping("/logout")
    // public ResponseResult userLogout() {
    //     return loginService.userLogout();
    // }
    //
    // @ApiOperation("用户注册")
    // @PostMapping("/regist")
    // public ResponseResult userRegist(@RequestBody LoginForm user) {
    //     return loginService.userRegist(user);
    // }
    //
    // @ApiOperation("获得用户注册账号验证码")
    // @GetMapping("/getRegistCode")
    // public ResponseResult getRegistCode(@RequestParam(value = "email") String email) {
    //     return loginService.getRegistCode(email);
    // }
}
