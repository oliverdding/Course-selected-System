package com.oliverdd.model;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class Schedule {
    private SimpleStringProperty classid; //课程班号
    private SimpleStringProperty tid; //教师编号
    private SimpleStringProperty cid; //课程编号

    public Schedule(){}

    public Schedule(String classid, String tid, String cid, String classroom) {
        this.classid = new SimpleStringProperty(classid);
        this.tid = new SimpleStringProperty(tid);
        this.cid = new SimpleStringProperty(cid);
    }

    public void setClassid(String classid) {
        this.classid = new SimpleStringProperty(classid);
    }

    public void setTid(String tid) {
        this.tid = new SimpleStringProperty(tid);
    }

    public void setCid(String cid) {
        this.cid = new SimpleStringProperty(cid);
    }

    public String getClassid() {
        return classid.get();
    }

    public String getTid() {
        return tid.get();
    }

    public String getCid() {
        return cid.get();
    }

    public void display(){
        System.out.printf("Schedule:" +
                "\nClassid: " + classid.get() +
                "\nCid: " + cid.get() +
                "\nTid: " + tid.get() + "\n");
    }
}
