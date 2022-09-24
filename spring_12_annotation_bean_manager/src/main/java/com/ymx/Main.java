package com.ymx;

import com.ymx.config.SpringConfig;
import com.ymx.mapper.BookMapper;
import com.ymx.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        // 获取资源
        BookMapper bean = context.getBean(BookMapper.class);
        BookMapper bean1 = context.getBean(BookMapper.class);
        System.out.println(bean);
        System.out.println(bean1);
        BookService bean2 = context.getBean(BookService.class);
        bean2.save();
    }
}