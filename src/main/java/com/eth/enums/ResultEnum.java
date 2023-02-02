package com.eth.enums;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResultEnum {

    SUCCESS_GET(HttpStatus.OK.value(), "SUCCESS"),
    SUCCESS_OF_DELETE(HttpStatus.OK.value(), "删除成功！"),
    SUCCESS_OF_UPDATE(HttpStatus.OK.value(), "更新成功！"),
    SUCCESS_OF_ADD(HttpStatus.OK.value(), "添加成功！"),
    USER_NOT_EXIST(3, "该用户不存在！"),
    File_NOT_EXIST(4, "查找的资源不存在！"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器发生异常！"),
    DB_ERROR(5, "数据库操作异常"),
    RUNTIME_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "运行时异常! "),
    NEED_TOKEN(HttpStatus.UNAUTHORIZED.value(), "用户认证失败请重新登陆！"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "参数或者路径不符合要求！");


    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
