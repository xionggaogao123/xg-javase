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

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Ordering;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Comparator;

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

        //********************************************

        /**
         * 1.2 -前置条件
         * Preconditions 类中提供了若干个前置条件判断
         *
         * checkArgument(boolean) 检查boolean是是否为true,用来检查传递给方法的参数
         *
         * checkNotNull(T) 检查value是否为null ,该方法直接返回value，可以内嵌使用checkNotNulll
         *
         * checkState(boolean) 检查对象的某些状态
         *
         * checkElementIndex(int index, int size) 检查Index 作为索引值对某个列表，字符串或数组是否有效，index >=0 && index<size
         *
         * checkPositionIndex(int index,int size) 检查index作为位置值对某个列表，字符串或数组是否有效，index >=0 &&index=size
         *
         * 索引值常用来查找列表，字符串或数组中的元素，如List.get(int),String.charAt(int)
         *
         * 位置值和位置范围常用来截取列表，字符串或数组，如List.subList(int,int)
         * String.subString(int)
         * 推荐使用 checkNotNull 直接返回检查的参数，可以在构造函数中保存字段的单行赋值风格
         * this.field = checkNotNull(field)
         *
         */
        boolean flag1 = true;
        Preconditions.checkArgument(flag1);

        String str = "aa";
        String returnStr = Preconditions.checkNotNull(str);

        //int aa = Preconditions.checkElementIndex(1,1);

        int bb = Preconditions.checkPositionIndex(1,1);

        /**
         * 3 常见的Object 方法
         * equal 方法
         * hashCode方法
         */
        Boolean flag = Objects.equal("q","b"); //比较二个数

        /**
         * 4 排序
         */
        Ordering.natural();//对可排序类型做自然排序，如数字按大小，日期按先后排序

        Ordering.usingToString(); //按对象的字符串形式做字典排序

        Ordering.natural().compare(1,2);

        //通过链是调用，可以由给定的排序器衍生出其他排序器
        Ordering.natural().reverse(); //获取语义相反的排序器
        Ordering.natural().nullsFirst(); //使用当前排序器，但额外的把nulll值排到最前面
        Ordering.natural().nullsLast(); //使用当前排序器，但额外把null值排在最后面

        Integer cc = Ordering.natural().max(1,3); //二个数比较返回大的
        Integer dd = Ordering.natural().min(2,3);
        System.out.println("min="+dd);
        System.out.println("max="+cc);


        /**
         *  5 Throwables 简化异常和错误的传播与检查
         *
         *
         */




    }

}
