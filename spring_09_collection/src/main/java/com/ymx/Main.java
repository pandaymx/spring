package com.ymx;

import com.ymx.mapper.BookMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 获取资源
        BookMapper bean = context.getBean(BookMapper.class);
        bean.save();
    }
}