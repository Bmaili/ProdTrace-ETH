package com.eth.aspect;

import com.eth.enums.ResultEnum;
import com.eth.error.MySQLException;
import com.eth.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Service层切面，日志处理，异常捕获，事务回滚
 */
@Aspect
@Slf4j
@Component
public class ServiceAspect {

    @Pointcut("execution(public * com.eth.service.*.*(..))")
    public void service() {}

    @Before("service()")
    public void before(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String method = signature.getDeclaringTypeName() + "." + signature.getName();
        log.info("-----------------------------------------------------");
        log.info("当前执行的service方法:" + method);
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            log.info("参数:" + arg);
        }
    }

    @AfterReturning(pointcut = "service()", returning = "ret")
    public void after(Object ret) {
        // log.info("service返回参数:" + ret);
        log.info("-----------------------------------------------------");

    }

    @Around("service()")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        try {
            result = point.proceed();//调用目标方法
        } catch (MySQLException throwable) {
            log.error(String.valueOf(throwable));
            return throwable.getResult();

        } catch (RuntimeException throwable) {
            log.error(String.valueOf(throwable));
            return new ResponseResult(ResultEnum.RUNTIME_ERROR);
        } catch (Throwable throwable) {
            log.error(String.valueOf(throwable));
            return new ResponseResult(500, "未知异常");
        }
        return result;
    }
}
