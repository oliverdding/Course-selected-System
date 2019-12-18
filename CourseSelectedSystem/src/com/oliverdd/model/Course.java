package com.oliverdd.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Course {
    private SimpleStringProperty cname; //课程名称
    private SimpleStringProperty cid; //课程编号
    private SimpleIntegerProperty chour; //课时
    private SimpleStringProperty classroom; //上课地点
    private SimpleStringProperty teacher; // 上课老师

    public void setClassroom(String classroom) {
        this.classroom = new SimpleStringProperty(classroom);
    }

    public void setTeacher(String teacher) {
        this.teacher = new SimpleStringProperty(teacher);
    }

    public String getClassroom() {
        return classroom.get();
    }

    public String getTeacher() {
        return teacher.get();
    }

    public Course(){}

    public Course(String cname, String cid, int chour, String classroom, String teacher) {
        this.cname = new SimpleStringProperty(cname);
        this.cid = new SimpleStringProperty(cid);
        this.chour = new SimpleIntegerProperty(chour);
        this.classroom = new SimpleStringProperty(classroom);
        this.teacher = new SimpleStringProperty(teacher);
    }

    public void setCname(String cname) {
        this.cname = new SimpleStringProperty(cname);
    }

    public void setCid(String cid) {
        this.cid = new SimpleStringProperty(cid);
    }

    public void setChour(int chour) {
        this.chour = new SimpleIntegerProperty(chour);
    }

    public String getCname() {
        return cname.get();
    }

    public String getCid() {
        return cid.get();
    }

    public int getChour() {
        return chour.get();
    }
    public void display(){
        System.out.printf("Course:" +
                "\nCname: " + cname.get() +
                "\nCid: " + cid.get() +
                "\nChour: " + chour.get() + "\n");
    }
}
