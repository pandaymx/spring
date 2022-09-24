package com.ymx.mapper.impl;

import com.ymx.mapper.BookMapper;

public class BookMapperImpl implements BookMapper {
    @Override
    public void save() {
        System.out.println("bookMapper");
    }
}
