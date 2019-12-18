package com.oliverdd.model;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person {
    protected SimpleStringProperty name; //姓名
    protected SimpleStringProperty sex; //性别
    protected SimpleIntegerProperty age; //年龄

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public void setSex(String sex) {
        this.sex = new SimpleStringProperty(sex);
    }

    public void setAge(int age) {
        this.age = new SimpleIntegerProperty(age);
    }

    public String getName() {
        return name.get();
    }

    public String getSex() {
        return sex.get();
    }

    public int getAge() {
        return age.get();
    }

    public Person() {

    }

    public Person(String name, String sex, int age) {
        this.name = new SimpleStringProperty(name);
        this.sex = new SimpleStringProperty(sex);
        this.age = new SimpleIntegerProperty(age);
    }
    public void display(){
        System.out.printf("Person:" +
                "\nName: " + this.name.get() +
                "\nSex: " + this.sex.get() +
                "\nAge: " + this.age.get() + "\n");
    }
}
