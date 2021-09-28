package com.example.personaldiary.controllers;

import com.example.personaldiary.DBConn;
import com.example.personaldiary.Page;
import com.example.personaldiary.Router;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import model.global.User;

import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

public class EditorController implements Initializable {
    private User user;
    private Page page;
    @FXML private TextField title;
    @FXML private HTMLEditor textArea;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        page = new Page();
        System.out.println(page);
    }

    @FXML
    public void save(){
        String titleText = title.getText();
        String content = textArea.getHtmlText();
        int authorId = user.getId();
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());

        DBConn DB = new DBConn();
        Connection conn = DB.getConnection();

        page.setTitle(titleText);
        page.setContent(content);
        page.setAuthor(authorId);
        page.setDatetime(time);

        if(page.getId() == -1){ // Create new Page entry
            try{
                String query = String.format("INSERT INTO `auth`.`pages` (`title`, `content`,`author`,`datetime`) VALUES (\"%s\" , \"%s\", %d, \"%s\");", page.getTitle(), page.getContent(), page.getAuthor(), page.getDatetime());
                Statement st = conn.createStatement();
                st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

                ResultSet res = st.getGeneratedKeys();

                while(res.next()){
                    int generatedKey = res.getInt("GENERATED_KEY");
                    System.out.println(generatedKey);
                    page.setId(generatedKey);
                }
            } catch (SQLException e){
                System.out.println("Error saving the Page");
                e.printStackTrace();
            }
        }
        else{
//            Page will update
            System.out.println("Page Update");
            String query = String.format("UPDATE `auth`.`pages` SET `title` = \"%s\", `content` = \"%s\" WHERE `id` = %d;", page.getTitle(), page.getContent(), page.getId());
            try {
                Statement updateStatement = conn.createStatement();
                int affectedCols = updateStatement.executeUpdate(query);
                System.out.println(affectedCols);
            } catch (SQLException e) {
                System.out.println("Failed to update Page");
                e.printStackTrace();
            }
        }


    }

    @FXML
    public void delete(ActionEvent event){
        // Check if the page is available to delete
        if(page.getId() == -1) return;
        // delete from DB
        DBConn DB = new DBConn();
        Connection conn = DB.getConnection();
        String delQuery = String.format("delete from pages where id=%d;", page.getId());
        try {
            Statement deleteStatement = conn.createStatement();
            int colAffected = deleteStatement.executeUpdate(delQuery);
            System.out.println(colAffected);
        } catch (SQLException e) {
            System.out.println("Failed to delete Page");
            e.printStackTrace();
        }
        // set Page id to -1
        page.setId(-1);
        // Goto Dashboard
        Router router = new Router();
        router.switchToDashboardScene(event, user);
    }

    public void setUser(User user) {
        this.user = user;
    }
}
