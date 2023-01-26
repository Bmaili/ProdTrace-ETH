package com.eth.error;

import com.eth.enums.ResultEnum;
import com.eth.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
@Slf4j
public class NotNullException {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseResult exceptionHandler(IllegalArgumentException e) {
        log.error("请求携带的参数不符合规范! ");
        return new ResponseResult(ResultEnum.BAD_REQUEST);
    }
}