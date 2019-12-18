package com.oliverdd.model;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class Student extends Person {
    private SimpleStringProperty sid; //学号
    private SimpleStringProperty major; //专业

    public Student(){}

    public Student(String name, String sex, int age, String sid, String major) {
        super(name, sex, age);
        this.sid = new SimpleStringProperty(sid);
        this.major = new SimpleStringProperty(major);
    }

    public void setSid(String sid) {
        this.sid = new SimpleStringProperty(sid);
    }

    public void setMajor(String major) {
        this.major = new SimpleStringProperty(major);
    }

    public String getSid() {
        return sid.get();
    }

    public String getMajor() {
        return major.get();
    }

    @Override
    public void display() {
        System.out.printf("Student:" +
                "\nName: " + this.name.get() +
                "\nSex: " + this.sex.get() +
                "\nAge: " + this.age.get() +
                "\nSid: " + this.sid.get() +
                "\nMajor" + this.major.get() + "\n");
    }
}
