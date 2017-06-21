package com.xg.javase.jdk18;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.xg.javase.jdk18.bean.Account;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by xionggao on 2017/6/19.
 */
public class BasisUser {

    /**
     * 使用java8的lamdba将list 转为map
     */
    public static Map<Long,String> getIdNameMap(List<Account>accounts){
        return accounts.stream().collect(Collectors.toMap(Account::getAccountId,Account::getUserName));
    }

    /**
     * 收集成实体本身map
     * @param accounts
     * @return
     */
    public static Map<Long,Account> getIdAccountMap(List<Account>accounts){
        return accounts.stream().collect(Collectors.toMap(Account::getAccountId,account -> account));
    }

    /**
     * 更加优雅的方式收集实体本身map
     * @param accounts
     * @return
     */
    public static Map<String,Account> gitNameAccountMap(List<Account>accounts){
        return accounts.stream().collect(Collectors.toMap(Account::getUserName, Function.identity()));
    }

    public static void main(String[]args){
        List<Account> list = Lists.newArrayList();
        list.add(new Account(1001l,"张三"));
        list.add(new Account(1002l,"丽水"));
        Map<Long,String>map = getIdNameMap(list);
        String json = JSON.toJSONString(map);
        System.out.println("json="+json);

        list.add(new Account(1003l,"王五"));
        Map<Long,Account> accountMap = getIdAccountMap(list);
        System.out.println("json account="+accountMap);

    }
}
