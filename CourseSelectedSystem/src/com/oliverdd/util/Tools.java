package com.oliverdd.util;

import javafx.scene.control.Alert;
import javafx.stage.Window;


public class Tools {
    public void showAlert(Alert.AlertType alertType, Window window, String title, String header, String content){
        Alert alert = new Alert(alertType);
        alert.initOwner(window);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
