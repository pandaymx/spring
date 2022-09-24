package com.ymx.service;

import org.springframework.transaction.annotation.Transactional;

public interface AccountService {

    @Transactional
    public void transfer(String out,String in,Integer money);
}
