package com.eth.vo;

import com.eth.enums.ResultEnum;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class ResponseResult<T> {
    private Integer code;
    private String msg;
    private T data;

    public ResponseResult(T data) {
        this.code = HttpStatus.OK.value();
        this.data = data;
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
        this.data = null;
    }

    public ResponseResult(ResultEnum resultEnum, T data) {
        this(resultEnum);
        this.data = data;
    }
}

