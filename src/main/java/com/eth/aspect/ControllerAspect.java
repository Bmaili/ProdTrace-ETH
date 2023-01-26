package com.eth.aspect;

import com.eth.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 控制层切面，负责日志处理
 */
@Aspect
@Component
@Slf4j
public class ControllerAspect {

    @Pointcut("execution(public * com.eth.controller.*.*(..))")
    public void controller() {}

    @Before("controller()")
    public void before(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String method = signature.getDeclaringTypeName() + "." + signature.getName();
        log.info("-----------------------------------------------------");
        log.info("当前执行controller的方法:  " + method);
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            log.info("参数: " + arg);
        }
    }

    @AfterReturning(pointcut = "controller()", returning = "ret")
    public void after(Object ret) {
        if (!ret.getClass().getName().equals(ResponseResult.class.getName())) {
            log.info("controller返回参数：" + ret);
            log.info("-----------------------------------------------------");
            log.info("\n");
            return;
        }
        ResponseResult result = (ResponseResult) ret;
        log.info("controller返回参数：" + ((ResponseResult<?>) ret).getCode() + "  " + ((ResponseResult<?>) ret).getMsg());
        log.info("-----------------------------------------------------");
        log.info("\n");
    }
}
