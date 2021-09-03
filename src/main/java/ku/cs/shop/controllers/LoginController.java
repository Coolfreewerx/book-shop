package ku.cs.shop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import ku.cs.shop.models.User;

import java.io.IOException;

public class LoginController {

    User user = new User();
    @FXML private TextField usernameTextField ;
    @FXML private TextField passwordField ;
    @FXML private Label errorLabel ;

    @FXML
    public void handleToRegisterButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้า หนังสือทั้งหมด (เพจหลัก)
        try {
            com.github.saacsos.FXRouter.goTo("register");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า register ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleLoginButton(ActionEvent actionEvent) {
        if (user.login(usernameTextField.getText(), passwordField.getText())) {
            errorLabel.setTextFill(Color.rgb(255, 255, 255));
            goToHome();
        }
        else {
            errorLabel.setTextFill(Color.rgb(210, 39, 30));
        }
    }

    @FXML
    public void goToHome() { //ไปหน้า หนังสือทั้งหมด (เพจหลัก)
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
