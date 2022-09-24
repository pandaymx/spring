package com.ymx;

import com.ymx.config.SpringConfig;
import com.ymx.pojo.Account;
import com.ymx.service.AccountService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        AccountService bean = context.getBean(AccountService.class);
        Account byid = bean.findByid(1);
        System.out.println(byid);
    }
}