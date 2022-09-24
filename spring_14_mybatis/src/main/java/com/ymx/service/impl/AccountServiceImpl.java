package com.ymx.service.impl;

import com.ymx.mapper.AccountMapper;
import com.ymx.pojo.Account;
import com.ymx.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;
    @Override
    public Account findByid(Integer id) {
        return accountMapper.selectById(id);
    }
}
