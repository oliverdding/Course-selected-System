package com.oliverdd;

import com.oliverdd.controller.HomeLayoutController;
import com.oliverdd.controller.RootLayoutController;
import com.oliverdd.model.Course;
import com.oliverdd.model.Schedule;
import com.oliverdd.model.Student;
import com.oliverdd.model.Teacher;
import com.oliverdd.util.CoursesWrapper;
import com.oliverdd.util.Myfile;
import com.oliverdd.util.Tools;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class MainApp extends Application {
    List<Student> students;
    List<Teacher> teachers;
    List<Course> courses;
    List<Schedule> schedules;
    private Tools tools;
    private Myfile dataStore;

    public Myfile getDataStore() {
        return dataStore;
    }

    private Stage primaryStage;
    private BorderPane rootLayout;
    public MainApp(){

    }

    public void clearData() {
        students.clear();
        teachers.clear();
        courses.clear();
        schedules.clear();
        dataStore.changeDataPath(null);
    }

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        tools = new Tools();
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Course Selected System");
        initRootLayout();

        students = new ArrayList<>();
        teachers = new ArrayList<>();
        courses = new ArrayList<>();
        schedules = new ArrayList<>();
        // Below function will set some data to the file
        setOriginData();

        showHomeOverview();
    }

    private void setOriginData(){
        /*
        Student student1 = new Student("D.J jack", "Male", 19, "2077091006001", "English");
        Student student2 = new Student("R You OKAY", "Female", 20, "2077091608028", "Chinese");
        Student student3 = new Student("Super cat", "Female", 19, "2077091608128", "Physics");
        Student student4 = new Student("Holy Jason", "Male", 19, "2067091624025", "Music");
        Student student5 = new Student("Mark Dan", "Male", 19, "2078091604312", "Sport");
        Student student6 = new Student("Lays D", "Female", 19, "2074011508011", "Geography");
        Student student7 = new Student("Cheng yi", "Female", 19, "2075121608062", "Math");
        Student student8 = new Student("BalaBala", "Male", 19, "2072411616025", "Math");
        Student student9 = new Student("BaBaLa", "Female", 19, "2071521243024", "English");
        Student student10 = new Student("Diu De", "Male", 19, "2071231601254", "Music");
        Student student11 = new Student("Oh my god", "Female", 19, "2071234608012", "Sport");
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        students.add(student6);
        students.add(student7);
        students.add(student8);
        students.add(student9);
        students.add(student10);
        students.add(student11);

        Teacher teacher1 = new Teacher("what", "Female", 56, "199882341", "Doctor");
        Teacher teacher2 = new Teacher("what why how", "Female", 56, "198882121", "Teacher");
        Teacher teacher3 = new Teacher("why", "Female", 56, "201199121", "vice-Doctor");
        Teacher teacher4 = new Teacher("how", "Female", 56, "189911291", "vice-Doctor");
        teachers.add(teacher1);
        teachers.add(teacher2);
        teachers.add(teacher3);
        teachers.add(teacher4);

        Course course1 = new Course("Chines", "10244201", 1, "pingC 1301", "what");
        Course course2 = new Course("English", "10223124", 13, "pingA 311", "why");
        Course course3 = new Course("Physics", "22222222", 10, "pingB 111", "what why how");
        Course course4 = new Course("Sport", "33333333", 22, "pingA 30151", "how");
        Course course5 = new Course("Geography", "44444444", 7, "pingB 31501", "what why how");
        Course course6 = new Course("Music", "15356371", 8, "pingD 301511", "how");
        Course course7 = new Course("English", "12167281", 1, "pingA 30151", "why");
        Course course8 = new Course("Chines", "18233251", 2, "pingD 3101", "what");
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        courses.add(course4);
        courses.add(course5);
        courses.add(course6);
        courses.add(course7);
        courses.add(course8);

        Schedule schedule1 = new Schedule("10101101", "199882341", "10244201", "pingC 1301");
        Schedule schedule2 = new Schedule("10252151", "199882341", "18233251", "pingD 3101");
        Schedule schedule3 = new Schedule("11515301", "198882121", "22222222", "pingB 111");
        Schedule schedule4 = new Schedule("22525262", "198882121", "44444444", "pingB 31501");
        Schedule schedule5 = new Schedule("26423215", "201199121", "10223124", "pingA 311");
        Schedule schedule6 = new Schedule("15215215", "201199121", "12167281", "pingA 30151");
        Schedule schedule7 = new Schedule("24626262", "189911291", "33333333", "pingA 30151");
        Schedule schedule8 = new Schedule("72432522", "189911291", "15356371", "pingD 301511");
        schedules.add(schedule1);
        schedules.add(schedule2);
        schedules.add(schedule3);
        schedules.add(schedule4);
        schedules.add(schedule5);
        schedules.add(schedule6);
        schedules.add(schedule7);
        schedules.add(schedule8);
        */
        try {
            dataStore = new Myfile(teachers, students, schedules, courses);
            dataStore.changeDataPath(null);
            dataStore.loadData();
            tools.showAlert(Alert.AlertType.INFORMATION, this.getPrimaryStage().getOwner(), "Success",
                    "The data has been reverted!",
                    "Got " + courses.size() + " courses, " + schedules.size() + " schedules, " + teachers.size() + " teachers and " + students.size() + " students.");
        } catch (IOException e){
            e.printStackTrace();
        } catch (Exception e){
            tools.showAlert(Alert.AlertType.INFORMATION, this.getPrimaryStage().getOwner(), "No data found",
                    "It seem that it is the first time you run this application",
                    "You can revert the data from file!");
        }
    }


    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout, 600, 460);
            primaryStage.setScene(scene);

            // Set Icons
            primaryStage.getIcons().add(new Image("/res/icons/home.png"));

            // (For Alert dialog)Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.setResizable(false);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showHomeOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/HomeLayout.fxml"));
            AnchorPane homeOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(homeOverview);

            // Give the controller access to the main app.
            HomeLayoutController controller = loader.getController();
            controller.setMainApp(this, rootLayout);
            controller.setData(courses, students, teachers, schedules);
            //controller.loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Used for alert dialog
    public Stage getPrimaryStage() {
        return primaryStage;
    }

}
