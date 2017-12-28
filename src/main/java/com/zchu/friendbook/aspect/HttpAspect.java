package com.zchu.friendbook.aspect;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
public class HttpAspect {
    private static final Logger logger= LoggerFactory.getLogger(HttpAspect.class);


    @Pointcut("execution(public * com.zchu.friendbook.controller.BookController.*(..))")
    public void log(){

    }

    @Before("log()")
    public void doBefore(JoinPoint joinpoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        logger.info("url={}",request.getRequestURL());
        logger.info("ip={}",request.getRemoteAddr());
        logger.info("method={}",request.getMethod());
        logger.info("class_method={}",joinpoint.getSignature().getDeclaringTypeName()+"."+joinpoint.getSignature().getName());
        logger.info("args={}",joinpoint.getArgs());
    }

    @Before("log()")
    public void doAfter() {
        logger.info("方法执行后");
    }
}
