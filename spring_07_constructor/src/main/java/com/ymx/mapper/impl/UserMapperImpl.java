package com.ymx.mapper.impl;

import com.ymx.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMapperImpl implements UserMapper {


    private int id;

    private String name;

    @Override
    public void save() {
        System.out.println("userMapper" + id + name);
    }
}
