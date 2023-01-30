package com.eth.service;


import com.eth.form.LoginForm;
import com.eth.vo.ResponseResult;

public interface LoginService {

    ResponseResult login(LoginForm user);
    ResponseResult getUserInfo();

    // ResponseResult logout();
    //
    // ResponseResult regist(LoginForm user);
    //
    // ResponseResult getLoginCode(String email);
}
