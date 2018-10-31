package com.zgy.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 定义一个切面类
 */

@Aspect
@Component
public class TimeAspect {

    @Around("execution(* com.zgy.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("TimeAspect 开始");
        Object[] args = joinPoint.getArgs();
        for(Object arg :args){
            System.out.println(arg);
        }
        long start = new Date().getTime();
        Object proceed = joinPoint.proceed();
        System.out.println("TimeAspect 耗时："+(new Date().getTime()-start)+"毫秒");
        System.out.println("TimeAspect 结束");

        return proceed;
    }
}
