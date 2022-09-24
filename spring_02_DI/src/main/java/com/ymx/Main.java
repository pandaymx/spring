package com.ymx;

import com.ymx.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 获取资源
        BookService bookService = (BookService) context.getBean("bookService");
        bookService.save();
    }
}