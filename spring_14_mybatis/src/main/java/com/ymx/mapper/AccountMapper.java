package com.ymx.mapper;

import com.ymx.pojo.Account;
import org.apache.ibatis.annotations.Select;

/**
 * @author panda
 * @description 针对表【tbl_account】的数据库操作Mapper
 * @createDate 2022-09-21 08:03:32
 * @Entity com.ymx.pojo.Account
 */

public interface AccountMapper {
    @Select("select * from spring_db.tbl_account where id = #{id}")
    Account selectById(int id);
}




