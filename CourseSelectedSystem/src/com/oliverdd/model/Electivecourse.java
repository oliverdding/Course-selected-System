package com.oliverdd.model;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class Electivecourse {
    private SimpleStringProperty elid; //选课号
    private SimpleStringProperty sid; //学号
    private SimpleStringProperty classid; //课程班号

    public Electivecourse(String elid, String sid, String classid) {
        this.elid = new SimpleStringProperty(elid);
        this.sid = new SimpleStringProperty(sid);
        this.classid = new SimpleStringProperty(classid);
    }

    public void setElid(String elid) {
        this.elid = new SimpleStringProperty(elid);
    }

    public void setSid(String sid) {
        this.sid = new SimpleStringProperty(sid);
    }

    public void setClassid(String classid) {
        this.classid = new SimpleStringProperty(classid);
    }

    public String getElid() {
        return elid.get();
    }

    public String getSid() {
        return sid.get();
    }

    public String getClassid() {
        return classid.get();
    }

    public void display(){
        System.out.printf("Electivecourse:" +
                "\nElid: " + elid.get() +
                "\nSid: " + sid.get() +
                "\nClassid: " + classid.get() + "\n");
    }
}
