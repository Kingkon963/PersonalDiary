package com.example.personaldiary;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Router {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToLoginScene(ActionEvent event) throws IOException {
        Parent loginFxml = FXMLLoader.load(getClass().getResource("fxml/hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loginFxml);
        stage.setScene(scene);
    }
    public void switchToRegisterScene(ActionEvent event) throws IOException {
        Parent loginFxml = FXMLLoader.load(getClass().getResource("fxml/register.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loginFxml);
        stage.setScene(scene);
    }
}
