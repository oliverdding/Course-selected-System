package com.oliverdd.controller;

import com.oliverdd.MainApp;
import com.oliverdd.util.Tools;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class RootLayoutController {

    Tools tools;
    // Reference to the main application
    private MainApp mainApp;
    /*
     * called by the main application to give a reference back to itself.
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    @FXML
    private void initialize() {
        tools = new Tools();
    }
    @FXML
    private void handleNew() {
        mainApp.clearData();
        tools.showAlert(Alert.AlertType.INFORMATION, mainApp.getPrimaryStage().getOwner(), "Success", "All the data has been gave up.", "You can edit a new file now.");
    }

    @FXML
    private void handleSave(){
        mainApp.getDataStore().saveData();
        tools.showAlert(Alert.AlertType.INFORMATION, mainApp.getPrimaryStage().getOwner(), "Success", "Program data has been saved!", "Find it on " + mainApp.getDataStore().getDataFilePath());
    }

    @FXML
    private void handleSaveAs(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File directory = directoryChooser.showDialog(mainApp.getPrimaryStage());
        try {
            if(directory.exists()) {
                mainApp.getDataStore().changeDataPath(directory.getCanonicalPath());
                mainApp.getDataStore().saveData();
                tools.showAlert(Alert.AlertType.INFORMATION, mainApp.getPrimaryStage().getOwner(), "Success", "Program data has been saved!", "Find it on " + directory.getCanonicalPath());
            } else {
                directory.mkdir();
                mainApp.getDataStore().changeDataPath(directory.getCanonicalPath());
                mainApp.getDataStore().saveData();
                tools.showAlert(Alert.AlertType.INFORMATION, mainApp.getPrimaryStage().getOwner(), "Path not exist", "The path you choose does not exist!", "The system have make the path!");

            }
        } catch (IOException e){
            // TODO: Alert
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRevert(){
        String tempPath = mainApp.getDataStore().getDataFilePath();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File directory = directoryChooser.showDialog(mainApp.getPrimaryStage());
        try {
            if(directory.exists()) {
                // change the directory
                mainApp.getDataStore().changeDataPath(directory.getCanonicalPath());
                if(mainApp.getDataStore().isExist()) {
                    mainApp.getDataStore().loadData();
                    tools.showAlert(Alert.AlertType.INFORMATION, mainApp.getPrimaryStage().getOwner(), "Success", "The data has been reverted.", "You can edit them now.");
                } else { // can't find the data file
                    tools.showAlert(Alert.AlertType.INFORMATION, mainApp.getPrimaryStage().getOwner(), "Failure", "Can't find data at that directory", "Make sure you choose the correct directory. And DO NOT CHANGE the file name.");
                    // recovery the path
                    mainApp.getDataStore().changeDataPath(tempPath);
                }
            } else {
                tools.showAlert(Alert.AlertType.INFORMATION, mainApp.getPrimaryStage().getOwner(), "Failure", "Can't open that directory", "Make sure you choose the correct directory.");
            }
        } catch (IOException e){
            // TODO: Alert
            e.printStackTrace();
        } catch (Exception e) {

        }
    }

    @FXML
    private void handleExit(){
        Alert _alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to quit?",new ButtonType("no", ButtonBar.ButtonData.NO),
                new ButtonType("yes", ButtonBar.ButtonData.YES));
        _alert.setTitle("Attention!");
        _alert.setHeaderText("You click the quick button");
        _alert.initOwner(mainApp.getPrimaryStage().getOwner());
        Optional<ButtonType> _buttonType = _alert.showAndWait();
        if(_buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)){
            mainApp.getDataStore().saveData();
            System.exit(0);
        } else {
            tools.showAlert(Alert.AlertType.INFORMATION, mainApp.getPrimaryStage(), "What's up man?", "Don't play with me!", "Give you one more chance");
        }

    }

    @FXML
    private void handleAbout(){
        tools.showAlert(Alert.AlertType.INFORMATION, mainApp.getPrimaryStage(), "About", "Author: OliverDD\nEmail: oliverdd@std.uestc.edu.cn", "Copyright © 2019 OliverDD oliverdd@std.uestc.edu.cn");
    }
}
