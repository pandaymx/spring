package com.ymx.service.impl;

import com.ymx.mapper.BookMapper;
import com.ymx.mapper.impl.BookMapperImpl;
import com.ymx.service.BookService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public void save() {
        System.out.println("bookService");
        bookMapper.save();
    }
}
