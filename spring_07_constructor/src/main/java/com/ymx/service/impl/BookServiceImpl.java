package com.ymx.service.impl;

import com.ymx.mapper.BookMapper;
import com.ymx.mapper.UserMapper;
import com.ymx.service.BookService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookServiceImpl implements BookService {
    private BookMapper bookMapper;
    private UserMapper userMapper;
    @Override
    public void save() {
        System.out.println("bookService");
        userMapper.save();
        bookMapper.save();
    }
}
