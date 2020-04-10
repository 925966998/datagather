package com.ky.redwood.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ParamsAspect {
    private static final Logger logger = LoggerFactory.getLogger(ParamsAspect.class);

    @Around("execution(* com.ky.redwood.controller..*.*(..))")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        logger.info("环绕通知的目标方法名：{},参数{}" + proceedingJoinPoint.getSignature().getName(), proceedingJoinPoint.getArgs());
        try {//obj之前可以写目标方法执行前的逻辑
            Object obj = proceedingJoinPoint.proceed();//调用执行目标方法
            return obj;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}