package com.oliverdd.controller;

import com.oliverdd.MainApp;
import com.oliverdd.model.Course;
import com.oliverdd.model.Schedule;
import com.oliverdd.model.Student;
import com.oliverdd.model.Teacher;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class CourseLayoutController {
    ObservableList<Course> courses;
    List<Student> students;
    List<Teacher> teachers;
    List<Schedule> schedules;
    Stage stage;
    // store some total number
    int hours;
    @FXML
    private Button newBut;
    @FXML
    private Button ediBut;
    @FXML
    private Button delBut;
    @FXML
    private TableView<Course> courseTableView;
    @FXML
    public TableColumn<Course, String> nameColumn;

    @FXML
    public TableColumn<Course, String> hourColumn;

    @FXML
    public TableColumn<Course, String> teacherColumn;

    @FXML
    private PieChart pieChart;

    @FXML
    private Label totalCourses;

    @FXML
    private Label totalHours;

    @FXML
    private Label totalTeachers;

    @FXML
    private Label totalStudents;

    public void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    private void handleNewBut(){
        try {
            Course course = new Course();
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/oliverdd/view/EditCourseLayout.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Course");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            EditCourseController controller = loader.getController();
            controller.setMainApp(mainApp);
            controller.setData(courses, teachers);
            controller.setDialogStage(dialogStage);
            controller.setCourse(course);

            // Set the dialog icon.
            dialogStage.getIcons().add(new Image("/res/icons/course.png"));

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEdiBut(){
        try {
            Course course = courseTableView.getSelectionModel().getSelectedItem();
            if(course != null) {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("/com/oliverdd/view/EditCourseLayout.fxml"));
                AnchorPane page = loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Edit Course");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(stage);
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                // Set the person into the controller.
                EditCourseController controller = loader.getController();
                controller.setMainApp(mainApp);
                controller.setData(courses, teachers);
                controller.setDialogStage(dialogStage);
                controller.setCourse(course);

                // Set the dialog icon.
                dialogStage.getIcons().add(new Image("/res/icons/course.png"));

                // Show the dialog and wait until the user closes it
                dialogStage.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("No Selection");
                alert.setHeaderText("No Course Selected");
                alert.setContentText("Please select a course in the table.");

                alert.showAndWait();
            }

            } catch(IOException e){
                e.printStackTrace();
            }

    }

    @FXML
    private void handleDelBut(){
        Course course = courseTableView.getSelectionModel().getSelectedItem();
        if(course != null) {
            courses.remove(course);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Course Selected");
            alert.setContentText("Please select a course in the table.");

            alert.showAndWait();
        }
    }

    // Reference to the main application.
    private MainApp mainApp;

    /*
     * @author: OliverDD
     * @description: The constructor.
     *               AFAIKThe constructor is called before the initialize() method.
     */
    public CourseLayoutController() {
    }
    private void loadChart(){
        PieChart.Data slice1, slice2, slice3;
        if(students != null)
            slice1 = new PieChart.Data("Students", students.size());
        else
            slice1 = new PieChart.Data("Students", 0);
        if(teachers != null)
            slice2 = new PieChart.Data("Teachers"  , teachers.size());
        else
            slice2 = new PieChart.Data("Teachers"  , 0);
        slice3 = new PieChart.Data("Hours" , hours);

        pieChart.getData().add(slice1);
        pieChart.getData().add(slice2);
        pieChart.getData().add(slice3);
    }
    private void loadTotalLabel(){
        totalHours.setText(String.valueOf(hours));
        if(courses != null)
            totalCourses.setText(String.valueOf(courses.size()));
        else
            totalCourses.setText(String.valueOf(0));
        if(students != null)
            totalStudents.setText(String.valueOf(students.size()));
        else
            totalStudents.setText(String.valueOf(0));
        if(teachers != null)
            totalTeachers.setText(String.valueOf(teachers.size()));
        else
            totalTeachers.setText(String.valueOf(0));
    }

    @FXML
    private void loadCourses(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("cname"));
        hourColumn.setCellValueFactory(new PropertyValueFactory<>("chour"));
        teacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        if(courses != null)
            courseTableView.setItems(courses);
    }
    @FXML
    private void initialize() {
        // Put some initialization construction here.

    }
    public void loadData(){
        loadTotalLabel();
        loadChart();
        loadCourses();
    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    public void setData(List<Course> courses, List<Student> students, List<Teacher> teachers, List<Schedule> schedules){
        this.courses = FXCollections.observableArrayList(courses);
        this.teachers = teachers;
        this.students = students;
        this.schedules = schedules;
        // 计算总时长
        hours = 0;
        for(Course course:courses){
            hours += course.getChour();
        }
    }
}
