package ku.cs.shop.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import ku.cs.shop.models.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HeadController {
    private User user;
    @FXML
    private HBox head;
    @FXML
    private HBox head1;
    @FXML
    private Label userNameLabel;

//    @FXML
//    public void initialize() {
//        System.out.println("initialize HeadController");
//        user = (User) com.github.saacsos.FXRouter.getData();
//        showUsername();
//    }

    public void pagesHeader() {
        if (user.login(user.getUserName(), user.getPassword()) == true) {
            HBox.setHgrow(head1, Priority.ALWAYS);
        }
//        HBox.setMargin("INHERIT", new Insets(head));
    }

    private void showUsername() {
        userNameLabel.setText(user.getUserName());
    }

//    public void initialize(URL location, ResourceBundle resource) {
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("/ku/cs/head.fxml"));
//    }
//
//    public FXMLLoader getFxmlLoader() {
//        return fxmlLoader;
//    }
}
