package com.ymx.service;

import com.ymx.mapper.AccountMapper;
import com.ymx.pojo.Account;

/**
 * @author panda
 * @description 针对表【tbl_account】的数据库操作Service
 * @createDate 2022-09-21 08:03:33
 */
public interface AccountService {
    Account findByid(Integer id);
}
