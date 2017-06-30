package com.xg.javase.jdk18;

import com.google.common.collect.Lists;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import com.xg.javase.jdk18.bean.Account;
import lombok.Data;
import org.junit.Test;

import javax.swing.*;
import java.util.*;

/**
 * Created by xionggao on 2017/5/24.
 *
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

    public void test01New(){
        new Thread(()->System.out.println("hello world")).start();
    }

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

    public void test02New (){
        List<String> list = Arrays.asList("a","b","c","d");
        List<String> stringList = new ArrayList<>();
        list.stream().forEach(data->stringList.add(data));
        stringList.forEach((d)->System.out.println(d));
        //将stringList中的字母全部换成大写
        stringList.stream().map(d->d.toUpperCase()).forEach(d->System.out.println(d));
    }

    @Test
    public  void  test01 (){
        Runnable runnable = () -> System.out.println("hello world");
        runnable.run();
    }

    @Test
    public void test02 () {
        JButton jButton = new JButton();
        jButton.addActionListener(
                e -> System.out.println("hello world")
        );
    }

    @Data
    class Student {
        private String name;
        private Long score;

        public Student (String name, Long score) {
            this.name = name;
            this.score = score;
        }
    }
    /**
     * 排序
     */
    @Test
    public void test03 () {
        List<Student> list = Lists.newArrayList();
        Random random = new Random();
        for(int i=0; i<5; i++){
            list.add(new Student("a"+i,random.nextLong()*100));
        }
        Collections.sort(list,(Student student1,Student student2)->(int)(student1.getScore()-student2.getScore()));
        for(Student s:list){
            System.out.println(s);
        }
    }

}
