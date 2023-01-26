package com.eth.error;


import com.eth.vo.ResponseResult;

/**
 * 数据库操作异常，在server层抛出,AOP环绕切面进行捕获，主要实现数据库的回滚操作和提供规范的返回格式
 *
 */
public class MySQLException extends RuntimeException {
    private ResponseResult result;

    public MySQLException(String message, ResponseResult result) {
        super(message);
        this.result = result;
    }

    public ResponseResult getResult() {
        return result;
    }

}
