package com.ymx.mapper.impl;

import com.ymx.mapper.BookMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BookMapperImpl implements BookMapper {
    @Override
    public int select() {
        System.out.println("select");
        return 100;
    }

    @Override
    public void update() {
        System.out.println("update");
    }
}
