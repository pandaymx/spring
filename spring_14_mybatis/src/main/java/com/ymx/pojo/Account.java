package com.ymx.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName tbl_account
 */

@Data
public class Account implements Serializable {

    private Integer id;
    private String name;
    private Integer money;
}