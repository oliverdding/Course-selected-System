package com.oliverdd.controller;

import com.oliverdd.MainApp;
import com.oliverdd.model.Course;
import com.oliverdd.model.Schedule;
import com.oliverdd.model.Student;
import com.oliverdd.model.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HomeLayoutController {
    List<Course> courses;
    List<Student> students;
    List<Teacher> teachers;
    List<Schedule> schedules;
    @FXML
    private Button retBut;
    @FXML
    private BorderPane rootLayout;
    @FXML
    private Button couBut;
    @FXML
    private Button stuBut;
    @FXML
    private Button teaBut;
    @FXML
    private Button eleBut;
    @FXML
    private Button schBut;
    @FXML
    private Button aboBut;
    //Data chart
//    @FXML
//    private PieChart pieChart;
//    @FXML
//    private BarChart<String, Integer> barChart;
//    @FXML
//    private LineChart<String, Integer> lineChart;

    @FXML
    private void handleRetBut(){
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/HomeLayout.fxml"));
            AnchorPane homeOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(homeOverview);

            // Give the controller access to the main app.
            HomeLayoutController controller = loader.getController();
            controller.setMainApp(mainApp, rootLayout);
            controller.setData(courses, students, teachers, schedules);
            //controller.loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleCouBut(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oliverdd/view/CourseLayout.fxml"));
            AnchorPane root = (AnchorPane)loader.load();

            CourseLayoutController controller = loader.getController();
            // Give the controller access to the main app.
            controller.setMainApp(mainApp);
            controller.setData(courses, students, teachers, schedules);
            controller.loadData();

            Stage stage = new Stage();
            stage.setTitle("Courses");
            stage.setScene(new Scene(root, 900, 620));
            stage.getIcons().add(new Image("/res/icons/course.png"));

            controller.setStage(stage);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleStuBut(){

    }

    @FXML
    private void handleTeaBut(){

    }

    @FXML
    private void handleEleBut(){

    }

    @FXML
    private void handleAboBut(){
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AboutLayout.fxml"));
            AnchorPane homeOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(homeOverview);

            // Give the controller access to the main app.
            HomeLayoutController controller = loader.getController();
            controller.setMainApp(mainApp, rootLayout);
            controller.setData(courses, students, teachers, schedules);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSchBut(){

    }

//    private void loadChart() {
//        loadPieChart();
//        loadLineChart();
//        loadBarChart();
//    }
//    private void loadPieChart() {
//        PieChart.Data slice1, slice2, slice3;
//        if(students != null)
//            slice1 = new PieChart.Data("Students", students.size());
//        else
//            slice1 = new PieChart.Data("Students", 0);
//        if(teachers != null)
//            slice2 = new PieChart.Data("Teachers"  , teachers.size());
//        else
//            slice2 = new PieChart.Data("Teachers"  , 0);
//        if(courses != null)
//            slice3 = new PieChart.Data("Courses" , courses.size());
//        else
//            slice3 = new PieChart.Data("Courses" , 0);
//        students.get(0).display();
//        pieChart.getData().add(slice1);
//        pieChart.getData().add(slice2);
//        pieChart.getData().add(slice3);
//    }
//    private void loadLineChart() {
//    }
//    private void loadBarChart() {
//
//    }

    // Reference to the main application.
    private MainApp mainApp;

    /*
     * @author: OliverDD
     * @description: The constructor.
     *               AFAIKThe constructor is called before the initialize() method.
     */
    public HomeLayoutController() {}
    @FXML
    private void initialize() {
        // Put some initialization construction here.
    }
    public void setMainApp(MainApp mainApp, BorderPane rootLayout) {
        this.mainApp = mainApp;
        this.rootLayout = rootLayout;
    }

    public void setData(List<Course> courses, List<Student> students, List<Teacher> teachers, List<Schedule> schedules){
        this.courses = courses;
        this.teachers = teachers;
        this.students = students;
        this.schedules = schedules;
    }
//    public void loadData(){
//        loadChart();
//    }

}
