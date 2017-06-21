package com.xg.javase.jdk18;

import java.util.function.Predicate;

/**
 * Created by xionggao on 2017/6/20.
 */
public class FunctionMain {



    /**
     * Predicate 使用
     * test()
     * and()
     * or()
     */
    public static void testPredicate () {
        String name = "name";
        String password = "password";
        Predicate<String> predicate = str -> str.startsWith("n");
        Predicate<String> predicate1 = str -> str.startsWith("p");

        System.out.println("name --是否以n开头："+predicate.test(name));
        System.out.println("password --是否以P开头："+predicate1.test(password));
        System.out.println("name --是否以n开头and p 开头："+predicate.and(predicate1).test(password));
        System.out.println("password --是否以n or p 开头："+predicate1.or(predicate).test(password));
    }

    /**
     * Consumer 只有输入，没有输出
     *
     */
    public static void testConsumer () {

    }


    public static void main(String[] args) {
        testPredicate();

    }
}
