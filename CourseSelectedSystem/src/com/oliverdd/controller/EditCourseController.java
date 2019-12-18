package com.oliverdd.controller;

import com.oliverdd.MainApp;
import com.oliverdd.model.Course;
import com.oliverdd.model.Student;
import com.oliverdd.model.Teacher;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;

public class EditCourseController {
    List<Course> courses;
    List<Teacher> teachers;

    private Stage dialogStage;
    private Course course;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }

    @FXML
    private TextField NameField;
    @FXML
    private TextField IDField;
    @FXML
    private TextField TeacherField;
    @FXML
    private TextField ClassroomField;
    @FXML
    private TextField HourField;

    // Reference to the main application.
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        // Set the dialog icon.
        this.dialogStage.getIcons().add(new Image("/res/icons/course.png"));
    }

    public void setData(List<Course> courses, List<Teacher> teachers){
        this.courses = courses;
        this.teachers = teachers;
    }

    public void setCourse(Course course) {
        this.course = course;
        try{
            NameField.setText(course.getCname());
            IDField.setText(course.getCid());
            TeacherField.setText(course.getTeacher());
            ClassroomField.setText(course.getClassroom());
            HourField.setText(String.valueOf(course.getChour()));
        } catch (NullPointerException e){

        }
    }

    @FXML
    private void handleReturn(){
        dialogStage.close();
    }

    @FXML
    private void handleOkay(){
        int flag = 0; //0 - new; 1 - edit
        if (isInputValid()) {
            course.setChour(Integer.parseInt(HourField.getText()));
            course.setCid(IDField.getText());
            course.setClassroom(ClassroomField.getText());
            course.setCname(NameField.getText());
            course.setTeacher(TeacherField.getText());
            // add to List
            for(Course c:courses){
                if(c.getCid() == course.getCid()){
                    int temp = courses.indexOf(c);
                    courses.remove(temp);
                    courses.add(temp, course);
                    flag = 1;
                    break;
                }
            }
            if(flag == 0){
                courses.add(course);
            }
            okClicked = true;
            dialogStage.close();
        }

    }

    private boolean isInputValid() {
        String errorMessage = "";
        if(NameField.getText() == null || NameField.getText().length() == 0){
            errorMessage += "Invalid course name!\n";
        }
        if(IDField.getText() == null || IDField.getText().length() == 0){
            errorMessage += "Invalid course id!\n";
        }
        if(ClassroomField.getText() == null || ClassroomField.getText().length() == 0){
            errorMessage += "Invalid classroom!\n";
        }
        if(TeacherField.getText() == null || TeacherField.getText().length() == 0){
            errorMessage += "Invalid teacher!\n";
        }
        if(HourField.getText() == null || HourField.getText().length() == 0){
            errorMessage += "Invalid course hours!\n";
        }
        // have Filled in all the textfield

        for(Course c:courses){
            if(c.getCid() == IDField.getText()) {
                errorMessage += "The course ID exists!";
                break;
            }
        }
        for(Teacher teacher:teachers){
            if(teacher.getName() == TeacherField.getText()){
                errorMessage += "The teacher does not exists!";
            }
        }
        try{
            Integer.parseInt(HourField.getText());
        }catch(Exception e){
            errorMessage += "Invalid course hours";
        }
        if(errorMessage.length() == 0){
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    @FXML
    private void handleClear(){
        NameField.setText("");
        IDField.setText("");
        TeacherField.setText("");
        ClassroomField.setText("");
        HourField.setText("");
    }
}
