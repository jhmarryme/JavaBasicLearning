package com.imooc.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

/**
 * description: spring aop
 *
 * @Author: Wjh
 * @Date: 2020/9/10 17:28
 * @Modified By:
 */
@Aspect
@Component
public class TimeAspect {

    @Around("execution(* com.imooc.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint point) throws Throwable {

        System.out.println("time aspect start");
        // 获取请求的参数
        Arrays.stream(point.getArgs()).forEach(System.out::println);

        long start = new Date().getTime();

        // 执行方法, 获取请求的结果
        Object o = point.proceed();

        System.out.println("time aspect 耗时:" + (new Date().getTime() - start));
        System.out.println("time aspect end");

        return o;
    }

}
