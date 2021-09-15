package com.example.personaldiary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainController {
    @FXML private Label title;
    @FXML private Label errorLabel;
    @FXML private TextField regUserId;
    @FXML private TextField regUserPass;
    @FXML private TextField regUserPassConfirm;

    @FXML private TextField logUserId;
    @FXML private TextField logUserPass;
    private Router router;

    Connection connect(){
        DBConn conn = new DBConn();
        Connection connectDB = conn.getConnection();
        return connectDB;
    }

    @FXML
    protected void toRegister (ActionEvent event) throws IOException {
        router = new Router();
        router.switchToRegisterScene(event);
    }
    @FXML
    protected void toLogin (ActionEvent event) throws IOException {
        router = new Router();
        router.switchToLoginScene(event);
    }

    @FXML
    protected void register (ActionEvent event) throws IOException {
        errorLabel.setVisible(false);
        router = new Router();
        if(regUserId.getText().isEmpty() || regUserPass.getText().isEmpty() || regUserPassConfirm.getText().isEmpty()){
            errorLabel.setText("Please fill up all the fields");
            errorLabel.setVisible(true);
        }
        else{
            if(!regUserPass.getText().equals(regUserPassConfirm.getText())){
                errorLabel.setText("Password doesn't match!");
                errorLabel.setVisible(true);
            }
            else{
                String query = String.format("INSERT INTO `auth`.`users`(`username`, `password`) VALUES ( \"%s\", \"%s\")", regUserId.getText(), regUserPass.getText());;
                try {
                    Statement st = connect().createStatement();
                    st.execute(query);
                    router.switchToLoginScene(event);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    protected void login (ActionEvent event) throws IOException {
        errorLabel.setVisible(false);
        router = new Router();
        if(logUserId.getText().isEmpty() || logUserPass.getText().isEmpty()){
            errorLabel.setText("Please fill up all the fields");
            errorLabel.setVisible(true);
        }
        else{
            String query = String.format("select password from users where username=\"%s\";", logUserId.getText());;
            try {
                Statement st = connect().createStatement();
                ResultSet res = st.executeQuery(query);

                while(res.next()){
                    if(!res.getString("password").equals(logUserPass.getText())){
                        errorLabel.setText("Wrong Password");
                        errorLabel.setVisible(true);
                    }else{
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setTitle("Login Successful");
                        a.setContentText("Login Successful");
                        a.show();
                    }
            }
            } catch (SQLException e) {
                // the reason for the exception
                String message = e.getMessage();

                // vendor-specific codes for the error
                int errorCode = e.getErrorCode();

                String sqlState = e.getSQLState();

                System.out.println(errorCode + ' ' + message + ' ' + sqlState);

            }
        }
    }

}