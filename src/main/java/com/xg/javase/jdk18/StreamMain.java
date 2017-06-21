package com.xg.javase.jdk18;

/**
 * Created by xionggao on 2017/5/24.
 */

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xg.javase.bean.User;
import com.xg.javase.jdk18.bean.Account;
import org.junit.Test;

import java.math.BigInteger;
import java.util.*;
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
        list.stream().map(String::toUpperCase).forEach(System.out::println);
        userList.stream().map(User::getSex).forEach(System.out::println);
   }

   @Test
    public void test04() {
       List<Integer> nums = Arrays.asList(1,2,3,4,5);
       List<Integer> newNums = nums.stream().map(n->n+1).collect(Collectors.toList());
       newNums.forEach(System.out::println);
    }

    //一对多
    @Test
    public void test05() {
       Stream<List<Integer>> inputStream = Stream.of(Collections.singletonList(1),Arrays.asList(2,3),Arrays.asList(5,6,7));
       Stream<Integer> outputStream = inputStream.flatMap(Collection::stream); //将多段流转换成一个总流
       outputStream.forEach(System.out::println);
    }

    /**
     * 将一个集合中的数据添加到另一个集合中去
     * Lamdba表达式提供了很好的支持，不需要一个一个区遍历了
     */


    @Test
   public static void add(){
       List<Account> accountList = Lists.newArrayList();
       accountList.add(new Account(1001L,"ss"));
       accountList.add(new Account(1002L,"sa"));
       accountList.add(new Account(1003L,"sc"));
       accountList.add(new Account(1004L,"sd"));

       List<Long> longAccountId = Lists.newArrayList();

       accountList.forEach(account -> {
           if (account.getAccountId() != null) {
               longAccountId.add(account.getAccountId());
                }
           });
       //longAccountId 有了数据，再拿去操作
       longAccountId.forEach(System.out::println);

   }

    /**
     * 吧 List<Account> 转Map<Long,Account>
     * map 和map1 都是一样的，map1 是使用了方法的引用
     */
    @Test
   public static void listToMap (){
        List<Account> users = Arrays.asList(new Account(1001L,"张三"),new Account(1002l,"历史"));
        Map<Long,Account>map = users.stream().collect(Collectors.toMap(obj -> {
            return obj.getAccountId();
        }, obj -> obj));
        Map<Long,Account>map1 = users.stream().collect(Collectors.toMap(Account::getAccountId,obj -> obj));
        System.out.println("****map1****"+map);
   }

    /**
     * 把List中的每个map中的id取出来，转换成List<Long>
     */
    @Test
   public static void listGetMapId(){
        List<Map<String,String>> list = Lists.newArrayList();
        Map<String,String> map1 = Maps.newHashMap();
        map1.put("id","1000001");
        map1.put("name","zhsdfds");
        Map<String,String> map2 = Maps.newHashMap();
        map2.put("id","1000002");
        map2.put("name","qqq");
        Map<String,String> map3 = Maps.newHashMap();
        map3.put("id","1000003");
        map3.put("name","www");

        list.add(map1);
        list.add(map2);
        list.add(map3);

        List<String>ids = list.stream().map(obj -> obj.get("id")).collect(Collectors.toList());
        System.out.println("*** ids ***"+ids);
   }

   //创建Stream 流的几种方式
    public static void arrayStream(){
       String[]arr = {"hello","world"};
       Stream<String> stream = Stream.of(arr);
       System.out.println(Arrays.toString(arr));
    }

    public static void collectionStream() {
        List<String> list = Lists.newArrayList();
        list.add("java");
        list.add("csds");
        Stream<String> stringStream = list.stream().filter(p->p.length()>1);
        String[]arr = stringStream.toArray(String[]::new);
        String list1 = Arrays.toString(arr);
        System.out.println(list1);
    }


    public static void generate() {
        Stream<String> stream = Stream.generate(()->"test").limit(1);
        String []strArr = stream.toArray(String[]::new);
        System.out.println(Arrays.toString(strArr));
    }

    /**
     *
     */
    public static void iterateStreaam (){
        Stream<BigInteger> bigIntegerStream = Stream.iterate(BigInteger.ONE,n->n.add(BigInteger.TEN)).limit(10);
        BigInteger []bigIntegers = bigIntegerStream.toArray(BigInteger[]::new);
        System.out.println(Arrays.toString(bigIntegers));
    }

    public static void main(String[]args){
       //listToMap();
       //add();
        //listGetMapId();
       // collectionStream();
        generate();
    }

}
