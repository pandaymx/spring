package com.ymx.mapper.impl;

import com.ymx.mapper.BookMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookMapperImpl implements BookMapper {
    private int[] array;
    private List<String> list;
    private Set<String> set;
    private Map<String, String> map;
    private Properties properties;

    @Override
    public void save() {
        System.out.println("bookMapper");
        System.out.println(Arrays.toString(array));
        System.out.println(list);
        System.out.println(set);
        System.out.println(map);
        System.out.println(properties);
    }
}
