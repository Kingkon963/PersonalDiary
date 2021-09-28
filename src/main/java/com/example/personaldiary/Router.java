package com.example.personaldiary;

import com.example.personaldiary.controllers.DashboardController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.global.User;

import java.io.IOException;
import java.util.Objects;

public class Router {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToLoginScene(ActionEvent event) throws IOException {
        Parent loginFxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/login.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loginFxml);
        stage.setScene(scene);
    }
    public void switchToRegisterScene(ActionEvent event) throws IOException {
        Parent loginFxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/register.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loginFxml);
        stage.setScene(scene);
    }

    public void switchToDashboardScene(ActionEvent event, User user) throws IOException {
        // Parent loginFxml = FXMLLoader.load(getClass().getResource("fxml/dashboard.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/dashboard.fxml"));
        Parent dashboardFxml = loader.load();

        DashboardController dashboardController = loader.getController();
        dashboardController.setUser(user);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(dashboardFxml);
        stage.setScene(scene);
    }

}
