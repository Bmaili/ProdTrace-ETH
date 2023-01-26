package com.eth.vo;

import com.eth.enums.ResultEnum;
import lombok.Data;


@Data
public class ResponseResult<T> {
    private Integer code;
    private String msg;
    private T date;


    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, T date) {
        this.code = code;
        this.date = date;
    }

    public ResponseResult(Integer code, String msg, T date) {
        this.code = code;
        this.msg = msg;
        this.date = date;
    }

    public ResponseResult(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
        this.date = null;
    }

    public ResponseResult(ResultEnum resultEnum, T date) {
        this(resultEnum);
        this.date = date;
    }
}

