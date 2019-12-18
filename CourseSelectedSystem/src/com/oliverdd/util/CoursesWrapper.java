package com.oliverdd.util;

import com.oliverdd.model.Course;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "courses")
public class CoursesWrapper {
    private List<Course> courses;
    @XmlElement(name = "course")
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
