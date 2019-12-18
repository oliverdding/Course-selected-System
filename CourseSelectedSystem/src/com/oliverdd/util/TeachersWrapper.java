package com.oliverdd.util;

import com.oliverdd.model.Teacher;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "teachers")
public class TeachersWrapper {
    private List<Teacher> teachers;
    @XmlElement(name = "teacher")
    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
