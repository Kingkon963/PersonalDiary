package com.example.personaldiary;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML private Label title;

    @FXML
    protected void print () {
        System.out.println("Nishi");
        title.setText("Nishi");
    }
}