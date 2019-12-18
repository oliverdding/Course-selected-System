package com.oliverdd.util;

import com.oliverdd.MainApp;
import com.oliverdd.model.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.List;
import java.util.prefs.Preferences;

public class Myfile{
    private File couFile;
    private File teaFile;
    private File stuFile;
    private File schFile;

    private List<Course> courses;
    private List<Teacher> teachers;
    private List<Student> students;

    public File getCouFile() {
        return couFile;
    }

    public File getTeaFile() {
        return teaFile;
    }

    public File getStuFile() {
        return stuFile;
    }

    public File getSchFile() {
        return schFile;
    }

    private List<Schedule> schedules;

    public Myfile(List<Teacher> teachers, List<Student> students, List<Schedule> schedules, List<Course> courses) throws IOException {
        this.courses = courses;
        this.teachers = teachers;
        this.students = students;
        this.schedules = schedules;
        couFile = new File(this.getDataFilePath() + "courses.xml");
        teaFile = new File(this.getDataFilePath() + "teachers.xml");
        stuFile = new File(this.getDataFilePath() + "students.xml");
        schFile = new File(this.getDataFilePath() + "schedule.xml");
    }

    public Myfile() throws IOException{
        couFile = new File(this.getDataFilePath() + "courses.xml");
        teaFile = new File(this.getDataFilePath() + "teachers.xml");
        stuFile = new File(this.getDataFilePath() + "students.xml");
        schFile = new File(this.getDataFilePath() + "schedule.xml");
    }

    public String getDataFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return filePath;
        } else {
            return null;
        }
    }

    public boolean isExist(){
        if(couFile.exists() &&
           teaFile.exists() &&
           stuFile.exists() &&
           schFile.exists() ){
            return true;
        }
        return false;
    }

    private void setDataFilePath(String path) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (path != null) {
            prefs.put("filePath", path);
        } else {
            prefs.remove("filePath");
        }
    }

    public void changeDataPath(String path) {
        String os = System.getProperty("os.name");
        System.out.println("The data path has been changed to " + path);
        if(path != null) {
            if(os.toLowerCase().startsWith("win")) {
                couFile = new File(path + "\\" + "courses.xml");
                stuFile = new File(path + "\\" + "students.xml");
                teaFile = new File(path + "\\" + "teachers.xml");
                schFile = new File(path + "\\" + "schedule.xml");
            } else {
                couFile = new File(path + "/" + "courses.xml");
                stuFile = new File(path + "/" + "students.xml");
                teaFile = new File(path + "/" + "teachers.xml");
                schFile = new File(path + "/" + "schedule.xml");
            }
        } else {
            couFile = new File("courses.xml");
            stuFile = new File("students.xml");
            teaFile = new File("teachers.xml");
            schFile = new File("schedule.xml");
        }
        setDataFilePath(path);
    }

    public void saveData() {
        this.saveCoursesToFile();
        this.saveSchedulesToFile();
        this.saveStudentsToFile();
        this.saveTeachersToFile();
    }

    public void loadData() throws Exception {
        try {
            if(!couFile.exists())
                couFile.createNewFile();
            if(!stuFile.exists())
                stuFile.createNewFile();
            if(!teaFile.exists())
                teaFile.createNewFile();
            if(!schFile.exists())
                schFile.createNewFile();
//            System.out.println(couFile.getCanonicalPath());
//            System.out.println(teaFile.getCanonicalPath());
//            System.out.println(schFile.getCanonicalPath());
//            System.out.println(couFile.getCanonicalPath());
        } catch (IOException e){
            e.printStackTrace();
        }
        this.loadCoursesFromFile();
        this.loadSchedulesFromFile();
        this.loadStudentsFromFile();
        this.loadTeachersFromFile();
    }

    // Below is stored methods


    //Courses
    private void saveCoursesToFile() {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(CoursesWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            CoursesWrapper wrapper = new CoursesWrapper();
            wrapper.setCourses(courses);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, couFile);
        } catch (Exception e) { // catches ANY exception
            e.printStackTrace();
        }
    }

    private void loadCoursesFromFile() throws Exception{

            JAXBContext context = JAXBContext
                    .newInstance(CoursesWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            CoursesWrapper wrapper = (CoursesWrapper) um.unmarshal(couFile);

            courses.clear();
            courses.addAll(wrapper.getCourses());

    }

    //Students
    private void saveStudentsToFile() {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(StudentsWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            StudentsWrapper wrapper = new StudentsWrapper();
            wrapper.setStudents(students);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, stuFile);
        } catch (Exception e) { // catches ANY exception
            e.printStackTrace();
        }
    }

    private void loadStudentsFromFile() throws Exception{
            JAXBContext context = JAXBContext
                    .newInstance(StudentsWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            StudentsWrapper wrapper = (StudentsWrapper) um.unmarshal(stuFile);

            students.clear();
            students.addAll(wrapper.getStudents());
    }

    //Teachers
    private void saveTeachersToFile() {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(TeachersWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            TeachersWrapper wrapper = new TeachersWrapper();
            wrapper.setTeachers(teachers);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, teaFile);
        } catch (Exception e) { // catches ANY exception
            e.printStackTrace();
        }
    }

    private void loadTeachersFromFile() throws Exception{
            JAXBContext context = JAXBContext
                    .newInstance(TeachersWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            TeachersWrapper wrapper = (TeachersWrapper) um.unmarshal(teaFile);

            teachers.clear();
            teachers.addAll(wrapper.getTeachers());
    }

    //Schdules
    private void saveSchedulesToFile() {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(SchedulesWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            SchedulesWrapper wrapper = new SchedulesWrapper();
            wrapper.setSchedules(schedules);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, schFile);
        } catch (Exception e) { // catches ANY exception
            e.printStackTrace();
        }
    }

    private void loadSchedulesFromFile() throws Exception{
            JAXBContext context = JAXBContext
                    .newInstance(SchedulesWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            SchedulesWrapper wrapper = (SchedulesWrapper) um.unmarshal(schFile);

            schedules.clear();
            schedules.addAll(wrapper.getSchedules());
    }
}
