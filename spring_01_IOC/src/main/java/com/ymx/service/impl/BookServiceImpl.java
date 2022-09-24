package com.ymx.service.impl;

import com.ymx.mapper.BookMapper;
import com.ymx.mapper.impl.BookMapperImpl;
import com.ymx.service.BookService;

public class BookServiceImpl implements BookService {
    private BookMapper bookMapper = new BookMapperImpl();

    @Override
    public void save() {
        System.out.println("bookService");
        bookMapper.save();
    }
}
