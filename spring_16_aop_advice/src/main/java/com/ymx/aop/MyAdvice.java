package com.ymx.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAdvice {

    // 将这个方法定义为切入点
    @Pointcut("execution(void com.ymx.mapper.BookMapper.update())")
    private void pt() {
    }

    @Before("pt()")
    public void before() {
        System.out.println("before");
    }
    @After("pt()")
    public void after() {
        System.out.println("after");
    }
    @Around("pt()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around before");
        pjp.proceed();
        System.out.println("around after");
        return 200;
    }
    @AfterReturning("pt()")
    public void afterReturning(){
        System.out.println("afterReturning");
    }
    @AfterThrowing("pt()")
    public void afterThrow(){
        System.out.println("afterThrow");
    }
}
