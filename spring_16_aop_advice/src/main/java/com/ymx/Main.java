package com.ymx;

import com.ymx.config.SpringConfig;
import com.ymx.mapper.BookMapper;
import com.ymx.mapper.impl.BookMapperImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookMapper bean = context.getBean(BookMapper.class);
        bean.update();
    }
}