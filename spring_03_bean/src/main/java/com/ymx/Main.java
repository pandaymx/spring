package com.ymx;

import com.ymx.mapper.BookMapper;
import com.ymx.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 获取资源
        BookService bookService = (BookService) context.getBean("mapper");
        BookService bookService2 = (BookService) context.getBean("mapper");
        bookService.save();
        BookMapper bean = context.getBean(BookMapper.class);
        BookMapper bean2 = context.getBean(BookMapper.class);
        System.out.println(bean);
        System.out.println(bean2);
        System.out.println(bookService);
        System.out.println(bookService2);

    }
}