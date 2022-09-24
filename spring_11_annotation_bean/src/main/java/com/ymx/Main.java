package com.ymx;

import com.ymx.config.SpringConfig;
import com.ymx.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        // 获取资源
        BookService bookService = context.getBean(BookService.class);
        bookService.save();
    }
}