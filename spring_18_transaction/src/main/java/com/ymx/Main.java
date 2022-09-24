package com.ymx;

import com.ymx.config.SpringConfig;
import com.ymx.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    @Autowired
    private AccountService accountService;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        AccountService bean = context.getBean(AccountService.class);
        bean.transfer("Tom","Jerry",100);
    }
}