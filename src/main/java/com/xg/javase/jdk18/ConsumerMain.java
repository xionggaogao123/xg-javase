package com.xg.javase.jdk18;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by xionggao on 2017/6/20.
 */
public class ConsumerMain {

    @FunctionalInterface
    interface Message{
        void send (String msg);
    }

    static class PhoneMessage implements Message {
        private AtomicInteger num = new AtomicInteger(0);
        @Override
        public void send (String msg){
            System.out.println(msg);
        }
    }


    public static void testCousumer() {
        String name = "name";
        String password = "password";

        Consumer<String> messageConsumer = message -> System.out.println("-----messageConsumer-----"+message);
        Consumer<String> afterConsumer = message -> new PhoneMessage().send("afterConsumer----"+message);
        Consumer<String> exceptionConsumer = message -> {
            System.out.println("----exceptionConsumer----"+ message);
            throw new NullPointerException();
        };

        int i = 0;
        messageConsumer.accept(name + i++);
    }

    @Data
    static class Person {
        String firstName;
        String lastName;
        int age;

        public Person(String firstName,String lastName){
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Person(String firstName,String lastName,int age){
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        /**
         * Function 将输入类型转换为输出类型
         * @return
         */
        public String printPerson (Function<Person,String> function){
            return function.apply(this);
        }
    }

    //使用lamdba表达式来遍历
    public static void forEachPerson() {
        List<Person> peoples = Lists.newArrayList();
        for(int i=0;i<5;i++){
            peoples.add(new Person("XX"+i,"OO"+i));
        }
        peoples.forEach(person -> System.out.println(person.getFirstName()));
    }

    //使用Predicate 可以有效的实现集合过滤
    public static void predicatePersion() {
        List<Person> personList = Lists.newArrayList();
        Random random = new Random();
        for (int i=0;i<5;i++){
            personList.add(new Person("firstName"+i,"lastName"+i,random.nextInt(100)));//random.nextInt(n) 表示产生0-n内的随机数，random.nextInt() 表示产生没有范围的随机数
        }
        Predicate<Person> personPredicate = person -> person.getAge()>20;
        Predicate<Person> personPredicate1 = person -> person.getAge()>20 && person.getAge()<50;

        for (Person person:personList){
            System.out.println("person's firstName："+person.getFirstName()+"and LastName："+person.getLastName()+"age："+person.getAge());
            if(personPredicate.test(person)){
                System.out.println("this person age >20岁");
            }
        }
    }

    //先过滤，然后使用collection 获取过滤后的集合
    public static void predicateCollection() {
        List<Person> personList = Lists.newArrayList();
        Random random = new Random();
        for (int i=0;i<10;i++){
            personList.add(new Person("firstName"+i,"lastName"+i,random.nextInt(100)));
        }
        //过滤年龄大于18岁的person


    }



    public static void main (String []agrs) {
        Person person = new Person("张","三");

        //用Lamdba表达式做为参数传递
        String stringPersion = person.printPerson(person1 -> "FirstName："+person.getFirstName());
        System.out.println("stringPersion="+stringPersion);

        //用lamdba表达式构建Function 对象：返回String 类型
        Function<Person,String> function = p->{
            return "FirstName："+p.getFirstName() + "LastName："+p.getFirstName();
        };
        System.out.println("function = "+ person.printPerson(function));

        forEachPerson();

        System.out.println("--------------------------------");
        predicatePersion();


    }


}
