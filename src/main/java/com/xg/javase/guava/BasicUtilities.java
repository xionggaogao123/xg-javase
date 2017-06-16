package com.xg.javase.guava;

/**
 * Created by xionggao on 2017/6/16.
 * Desc 基本工具
 */

/**
 * 1.1 使用和避免null
 * 1.2 前置条件
 * 1.3 常见object方法
 * 1.4 排序
 * 1.5 Throwables
 */

import com.google.common.base.Optional;

/***
 *
 * Guava用Option<T> 表示可能为null的T类型引用，一个Optional实例可能包含非null的引用
 * 也可能什么也不包括，但从不说包含null值
 */
public class BasicUtilities {


    public static void main(String[]args){
        /**
         * 创建Optional实例
         * Optional.of(T) 创建指定引用的Optional实例，如果引用为null,则快速失败
         * Optional.absent() 创建引用缺少的Optional实例
         */
        int a = 6;

        Optional<Integer> possible = Optional.of(a);
        possible.isPresent();
        Integer orInt = possible.or(1);
        Integer num = possible.get();
        System.out.println("num="+num);
        /**
         * 创建指定引用的Optional实例，如引用为null则表示缺失
         */
        Optional<Integer> integerOptional = Optional.fromNullable(5);

        /**
         * Optional常见的实例
         *
         * boolean isPresent() 判断Optional实例是否包含非null的引用，若引用存着，则返回true
         *
         * T get() 返回Optional所包含的引用，若没有，则抛出异常
         *
         * T or(T) 返回Optional所包含的引用，若引用缺失，则返回指定的值
         */
    }

}
