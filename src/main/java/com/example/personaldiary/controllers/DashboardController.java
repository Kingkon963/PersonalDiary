package com.example.personaldiary.controllers;

import com.example.personaldiary.Page;
import com.example.personaldiary.Router;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.global.User;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    private User user;

    @FXML private Button newBtn;
    @FXML public Label greetingLabel;
    @FXML private TableView<Page> myTable;
    @FXML private TableColumn<Page, String> titleColumn;
    @FXML private TableColumn<Page, Date> dateColumn;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        dateColumn.setCellValueFactory((new PropertyValueFactory<>("date")));

        myTable.getColumns().clear();
        myTable.getColumns().add(titleColumn);
        myTable.getColumns().add(dateColumn);
        myTable.setItems(getPages());
    }

    public void setUser(User user) {
        this.user = user;
        greetingLabel.setText("Hi, " + user.getName() + "!");
    }

    private ObservableList<Page> getPages() {
        ObservableList<Page> pages = FXCollections.observableArrayList();
//        pages.add(new Page("First Day at School"));
//        pages.add(new Page("First Day at College"));
//        pages.add(new Page("First Day at Uni"));

        return pages;
    }

    @FXML
    public void refresh() {
        greetingLabel.setText(user.getName());
    }
    @FXML
    public void newPage(ActionEvent event) throws IOException {
        Router router = new Router();
        router.switchToEditor(event, this.user);
    }
}


/*Image icn = new Image("file:Images/icon.jpg");
            stg.getIcons().add(icn); */
