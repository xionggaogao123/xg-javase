package com.xg.javase.jdk18;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xionggao on 2017/5/24.
 */
public class LambdaMain {

    /**
     * Lambda 表达式写法
     * (参数)-> 表达式
     * (参数)-> 语句
     * (参数)-> {语句}
     *
     */

    @Test
    public void test01old () {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("before java 1.8 , hello world");
            }
        }).start();
    }

    @Test
    public void test01New(){
        new Thread(()->System.out.println("hello world")).start();;
    }

    @Test
    public void test02Old (){
        List<String> stringList = new ArrayList<>();
        List<String> list = Arrays.asList("a","b","c","d");
        for(String s:list){
            stringList.add(s);
        }
        for(String s:list){
            System.out.println("--- the new list --"+s);
        }
    }

    @Test
    public void test02New (){
        List<String> list = Arrays.asList("a","b","c","d");
        List<String> stringList = new ArrayList<>();
        list.stream().forEach(data->stringList.add(data));
        stringList.forEach((d)->System.out.println(d));
        //将stringList中的字母全部换成大写
        stringList.stream().map(d->d.toUpperCase()).forEach(d->System.out.println(d));
    }



}
