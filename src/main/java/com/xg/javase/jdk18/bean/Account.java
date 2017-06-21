package com.xg.javase.jdk18.bean;

import lombok.Data;

/**
 * Created by xionggao on 2017/6/19.
 */
@Data
public class Account {

    private Long accountId;
    private String userName;

    public Account(){}

    public Account(Long accountId,String userName){
        this.accountId = accountId;
        this.userName = userName;
    }

}
