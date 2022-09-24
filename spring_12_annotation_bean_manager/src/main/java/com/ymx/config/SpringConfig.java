package com.ymx.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
// 注解生效的扫描包
@ComponentScan("com.ymx")
// 导入数据源，以数组方式来写
@PropertySource("jdbc.properties")
public class SpringConfig {

}
