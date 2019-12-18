package com.oliverdd.model;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class Teacher extends Person {
    private SimpleStringProperty tid; //职工号
    private SimpleStringProperty title; //职称

    public Teacher(){}

    public Teacher(String name, String sex, int age,
                   String tid, String title) {
        super(name, sex, age);
        this.tid = new SimpleStringProperty(tid);
        this.title = new SimpleStringProperty(title);
    }

    @Override
    public void display() {
        System.out.printf("Teacher:" +
                "\nName: " + this.name.get() +
                "\nSex: " + this.sex.get() +
                "\nAge: " + this.age.get() +
                "\nTid: " + this.tid.get() +
                "\nTitle" + this.title.get() + "\n");
    }

    public void setTid(String tid) {
        this.tid = new SimpleStringProperty(tid);
    }

    public void setTitle(String title) {
        this.title = new SimpleStringProperty(title);
    }

    public String getTid() {
        return tid.get();
    }

    public String getTitle() {
        return title.get();
    }
}
