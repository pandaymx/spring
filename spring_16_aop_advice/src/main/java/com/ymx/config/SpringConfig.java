package com.ymx.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.ymx")
@EnableAspectJAutoProxy
// 用注解开发的aop
public class SpringConfig {

}
