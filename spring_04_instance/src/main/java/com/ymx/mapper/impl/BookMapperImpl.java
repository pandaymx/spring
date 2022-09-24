package com.ymx.mapper.impl;

import com.ymx.mapper.BookMapper;

public class BookMapperImpl implements BookMapper {

    private BookMapperImpl(){
        System.out.println("无参构造");
    }
    @Override
    public void save() {
        System.out.println("bookMapper");
    }
}
