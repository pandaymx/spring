package com.ymx.mapper.impl;

import com.ymx.mapper.BookMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
// 设置单例模式
@Scope("prototype")
@Data
public class BookMapperImpl implements BookMapper {
    @Value("1")
    private int id;
    @Value("${jdbc.url}")
    private String name;
    @Override
    public void save() {
        System.out.println("bookMapper"+id+name);
    }
}
