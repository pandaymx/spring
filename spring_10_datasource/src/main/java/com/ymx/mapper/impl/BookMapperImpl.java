package com.ymx.mapper.impl;

import com.ymx.mapper.BookMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookMapperImpl implements BookMapper {
    private String name;
    @Override
    public void save() {
        System.out.println("bookMapper");
        System.out.println(name);
    }
}
