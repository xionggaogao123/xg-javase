package com.xg.javase.bean;

/**
 * Created by xionggao on 2017/5/24.
 */
public class User {

    private Long id;
    private String name;
    private String sex;
    private double salary;

    public User(){}

    public User(Long id,String name,String sex,double salary){
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.salary = salary;
    }

    public User(String name,String sex,double salary){
        this.name = name;
        this.sex = sex;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", salary=" + salary +
                '}';
    }
}
