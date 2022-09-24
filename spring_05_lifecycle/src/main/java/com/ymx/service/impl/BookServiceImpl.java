package com.ymx.service.impl;

import com.ymx.mapper.BookMapper;
import com.ymx.mapper.impl.BookMapperImpl;
import com.ymx.service.BookService;
import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.sql.SQLOutput;

@Data
public class BookServiceImpl implements BookService , InitializingBean, DisposableBean {
    private BookMapper bookMapper;

    @Override
    public void save() {
        System.out.println("bookService");
        bookMapper.save();
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("service destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("service init");
    }
}
