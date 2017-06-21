package com.xg.javase.guava;

/**
 * Created by xionggao on 2017/6/16.
 */

import com.alibaba.fastjson.JSON;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.*;
import com.xg.javase.jdk18.bean.Account;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 集合
 * 2.1 不可变集合
 * 2.2 新集合类型
 * 2.3 强大的集合工具类
 * 2.4 扩展工具类
 */
public class CollectionsGuava {

    public static final ImmutableMultiset<String> COLOR_NAMES =
            ImmutableMultiset.of("red","orange","yellow","green");

   /* class Foo{
        Set<Bar> bars ;
        Foo(Set<Bar>bars){
            this.bars = Immutan
        }
    }*/


    /**
     * list 转string 字符串
     */
   public static String listToString(){
       List<String> names = Lists.newArrayList("John", "Jane", "Adam", "Tom");
       Joiner.on(",").join(names);
       return names.toString();
   }

    /**
     * list 转 String ,跳过null
     * @return
     */
   public static String listToString2 (){
       List<String> stringList = Lists.newArrayList("john",null,"jahe","tom");
       String result = Joiner.on(",").skipNulls().join(stringList);
       return result;
   }

    /**
     * map转字符串
     * @return
     */
   public static String mapToString() {
       Map<String,Object> map = Maps.newHashMap();
       map.put("a",new Account(10001l,"张三"));
       map.put("b",new Account(10002l,"；李四"));
       String result = Joiner.on(",").withKeyValueSeparator("=").join(map);
       return result;
   }

    /**
     * 字符串转map
     * @return
     */
   public static String stringToMap() {
       String input = "Join=first,Adam=secod";
       Map<String,String>result = Splitter.on(",").withKeyValueSeparator("=").split(input);
       return  JSON.toJSONString(result);
   }


   //***************** Guava集合类 Maps 的使用 ***************
    /**
     * 将主键当作Map的key
     * 一般用在将一个list<Bean>转换成List<BeanDTO>
     */
    public static void uniqueIndex (List<Account>accounts){
        Map<Long,Account>accountMap = Maps.uniqueIndex(accounts.iterator(), new Function<Account, Long>() {
           @Override
           public Long apply(Account account){
               return account.getAccountId();
           }
       });
        //获取到map后就可以多map进行操作
        Account account1 =  null;
        for(Account account:accounts){
            //new DTO set操作
             account1 = accountMap.get(account.getAccountId());
        }
        System.out.println("*****"+account1);

    }


    public static void main(String[]args){

        //静态工厂方法创建集合
        List<String> list = Lists.newArrayList();
        Set<Integer> set = Sets.newHashSet();
        Map<String,Object> map = Maps.newHashMap();

        //创建真正的不可修改的集合
        //java标准库支持
        Set<String> stringSet = Sets.newHashSet();
        Set<String> unmodifiableSet = Collections.unmodifiableSet(stringSet);

        //guava方式创建
        ImmutableList<String> stringImmutableList = ImmutableList.of("t1","t2");
       // stringImmutableList.add("t3");

        //新集合类型，
        String[]strings = new String[]{"a","b","c"};
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("e");
        for(String str:strings){
            multiset.add(str);
        }

        String s = listToString();
        System.out.println("s="+s);

        String mapToString = mapToString();
        System.out.println("map to string="+mapToString);

        String list2 = listToString2();
        System.out.println("list2="+list2);

        String string = stringToMap();
        System.out.println("string="+string);

        List<Account> list1 = Lists.newArrayList();
        list1.add(new Account(1l,"张三"));
        uniqueIndex(list1);


    }
}
