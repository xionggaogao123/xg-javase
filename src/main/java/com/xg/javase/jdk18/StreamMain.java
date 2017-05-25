package com.xg.javase.jdk18;

/**
 * Created by xionggao on 2017/5/24.
 */

import com.xg.javase.bean.User;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream API
 */
public class StreamMain {

    List<User> userList = Arrays.asList(
            new User("张三","男",10000.0),
            new User("王二","男",70000.0),
            new User("丽丽","女",80000.0),
            new User("Alice","女",9000.0)
    );

    /** 创建Stream
     *  四种创建方式
     *  1  通过Collection提供的steeam() 或 parallelStream()
     */
    @Test
   public void createStream(){
       //1 通过Collection 系列集合 提供的 stream()或parallelStream()

       List<String> list = new ArrayList<String>();
       Stream<String> stringStream = list.stream();//获取流的方式
       //2 通过Arrays 中的静态方法stream()获取数组流
       User[] users = new User[10];
       Stream<User>stream = Arrays.stream(users);
       //3 通过Stream 类中的静态方法of()
       Stream<String> stream1 = Stream.of("aa");
       //创建无限流
       Stream<Integer> integerStream = Stream.iterate(0,(x)->x+2);
       integerStream.limit(10);
       integerStream.forEach(System.out::println);
   }

    /**
     *  Stream 的中间操作
     *  多个中间操作可以连接起来形成一个流水线，除非流水线上触发终止操作，否则中间操作不会执行任何的处理，而在终止操作时一次性全部处理，称为'惰性操作'
     *
     *  filter(Predicate p)  接受Lambda ，从流中排除某些元素
     *  distinct()  帅选，通过流所生成元素的hashCode() 和equals() 去除重复元素
     *  limit(long maxSize)  使元素不超过给定数量值
     *  skip(long n)  跳过元素，返回一个丢掉了前n个元素的流，如果流中的元素不足n个，则返回一个空流，与limt(n)互补
     */
   @Test
   public void test02(){
       Stream<User> stream = userList.stream().filter((e)->e.getSalary()>7000);//中间操作
       stream.forEach(System.out::println); //终止操作

       userList.stream().filter((e)->{
           System.out.println();
           return e.getSalary()>5000;
       }).limit(2).forEach(System.out::println);
   }

    /**
     * 映射（重点）
     * map --接受 Lambda ，将元素转换成其他形式或获取信息，接受一个函数作为参数，该函数会应用到每一个元素上，并将其映射成一个新的元素。
     * flatMap  --接受一个函数作为参数，将流中的每一个值换成另一个流，然后把所有流连接一个流
     *
     */
   @Test
   public void test03(){
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");
        list.stream().map((str)->str.toUpperCase()).forEach(System.out::println);
        userList.stream().map(User::getSex).forEach(System.out::println);
   }

   @Test
    public void test04() {
       List<Integer> nums = Arrays.asList(1,2,3,4,5);
       List<Integer> newNums = nums.stream().map(n->n+1).collect(Collectors.toList());
       newNums.forEach((x)->System.out.println(x));
    }

    //一对多
    @Test
    public void test05() {
       Stream<List<Integer>> inputStream = Stream.of(Arrays.asList(1),Arrays.asList(2,3),Arrays.asList(5,6,7));
       Stream<Integer> outputStream = inputStream.flatMap((x)->x.stream()); //将多段流转换成一个总流
       outputStream.forEach(x->System.out.println(x));
    }

}
