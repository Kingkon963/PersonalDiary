package com.example.personaldiary.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.global.User;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController {
    private User user;

    @FXML private Button newBtn;

    @FXML
    public Label greetingLabel;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setUser(User user) {
        this.user = user;
        greetingLabel.setText("Hi, " + user.getName() + "!");
    }

    @FXML
    public void refresh() {
        greetingLabel.setText(user.getName());
    }
}


/*Image icn = new Image("file:Images/icon.jpg");
            stg.getIcons().add(icn); */
