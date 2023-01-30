package com.eth.form;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class LoginForm {
    @ApiModelProperty("账号")
    @NotNull("账号不能为空")
    private String username;

    @ApiModelProperty("登陆密码")
    @NotNull("密码不能为空")
    private String password;

    @ApiModelProperty("登录验证码")
    @NotNull("验证码不能为空")
    private String code;

    @ApiModelProperty("UUID")
    @NotNull("UUID不能为空")
    private String uuid;
}
