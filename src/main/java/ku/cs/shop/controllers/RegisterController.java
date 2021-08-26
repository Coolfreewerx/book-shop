package ku.cs.shop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import ku.cs.shop.models.User ;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterController {

    // controller เชื่อมต่อกับ view เพื่อรับข้อมูล
    User user = new User();
    @FXML private TextField firstNameTextField ;
    @FXML private TextField lastNameTextField ;
    @FXML private TextField userNameTextField ;
    @FXML private PasswordField passwordField ;
    @FXML private PasswordField checkPasswordField ;
    @FXML private TextField birthDayTextField ;
    @FXML private TextField birthMonthTextField ;
    @FXML private TextField birthYearTextField ;
    @FXML private Label passwordCompareLabel ;
    @FXML private Label registerErrorLabel ;
    @FXML private Label passwordConditionCheckLabel ;

    @FXML //ทำงานเมื่อกรอกรหัส
    public void keyPassword() {
        if (user.checkPasswordCondition(passwordField.getText())){
            passwordConditionCheckLabel.setText("รหัสผ่านนี้สามารถใช้ได้") ;
            passwordConditionCheckLabel.setTextFill(Color.rgb(21, 117, 84));
        }
        else {
            passwordConditionCheckLabel.setText("รหัสผ่านไม่ตรงตามรูปแบบที่กำหนด");
            passwordConditionCheckLabel.setTextFill(Color.rgb(210, 39, 30));
        }
    }
    @FXML //ทำงานเมื่อกรอกยืนยันรหัส
    public void keyCheckPassword() {
        passwordCompareLabel.setText(user.comparePassword(passwordField.getText(), checkPasswordField.getText()));
    }

    //ปุ่ม register
    @FXML
    public void registerButton(ActionEvent actionEvent) {

        String firstNameStr = firstNameTextField.getText() ;
        String lastNameStr = lastNameTextField.getText() ;
        String userNameStr = userNameTextField.getText() ;
        String passwordStr = passwordField.getText() ;
        String birthDayStr = birthDayTextField.getText() ;
        String birthMonthStr = birthMonthTextField.getText() ;
        String birthYearStr = birthYearTextField.getText() ;
        user.setFirstName(firstNameStr);
        user.setLastName(lastNameStr);
        user.setUserName(userNameStr);
        user.setPassword(passwordStr);
        user.setBirthDay(birthDayStr);
        user.setBirthMonth(birthMonthStr);
        user.setBirthYear(birthYearStr);

        registerErrorLabel.setText(user.dataCheck());

        if (!(user.getDataCheck())) {
            return;
        }

        user.writeUserInfo();

        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    //ปุ่มกลับไปหน้า login
    @FXML
    public void handleToLoginButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
