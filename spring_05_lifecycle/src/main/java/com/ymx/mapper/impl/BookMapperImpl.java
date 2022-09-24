package com.ymx.mapper.impl;

import com.ymx.mapper.BookMapper;

public class BookMapperImpl implements BookMapper {
    @Override
    public void save() {
        System.out.println("bookMapper");
    }

    public void init(){
        System.out.println("init");
    }
    public void destroy(){
        System.out.println("destroy");
    }
}
