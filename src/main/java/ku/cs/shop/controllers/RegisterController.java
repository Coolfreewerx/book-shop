package ku.cs.shop.controllers;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import ku.cs.shop.models.User ;

import java.io.*;
import java.util.ArrayList;

public class RegisterController {

    // controller เชื่อมต่อกับ view เพื่อรับข้อมูล
    User user = new User();
    @FXML private TextField firstNameTextField ;
    @FXML private TextField lastNameTextField ;
    @FXML private TextField userNameTextField ;
    @FXML private PasswordField passwordField ;
    @FXML private PasswordField checkPasswordField ;
    @FXML private ChoiceBox<String> birthDayChoice ;
    @FXML private ChoiceBox<String> birthMonthChoice ;
    @FXML private ChoiceBox<String> birthYearChoice ;
    @FXML private Label passwordCompareLabel ;
    @FXML private Label registerErrorLabel ;
    @FXML private Label passwordConditionCheckLabel ;
    @FXML private Label userNameCheckLabel ;

    private ObservableList dayList = FXCollections.observableArrayList() ;
    private ObservableList monthList = FXCollections.observableArrayList() ;
    private ObservableList yearList = FXCollections.observableArrayList() ;

    @FXML
    public void initialize () {
        lodeData();
    }
    @FXML //ทำงานเมื่อกรอก username
    public void handleKeyUserName() {
        userNameCheckLabel.setText(user.checkUserNameCondition(userNameTextField.getText()));
        if (user.getUserNameCheck()){
            userNameCheckLabel.setTextFill(Color.rgb(21, 117, 84));
        }
        else {
            userNameCheckLabel.setTextFill(Color.rgb(210, 39, 30));
        }
    }
    @FXML //ทำงานเมื่อกรอกรหัส
    public void handleKeyPassword() {
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
    public void handleKeyCheckPassword() {
        passwordCompareLabel.setText(user.comparePassword(passwordField.getText(), checkPasswordField.getText()));
    }

    private void lodeData() {
        yearList.removeAll(yearList) ;
        int i = 2021;
        String year = "ปี" ;
        yearList.add(year) ;
        while ( i >= 1940){
            year = "" + i ;
            yearList.add(year) ;
            i-- ;
        }
        birthYearChoice.getItems().addAll(yearList) ;
    }

    //ปุ่ม register
    @FXML
    public void handleRegisterButton(ActionEvent actionEvent) {

        String firstNameStr = firstNameTextField.getText() ;
        String lastNameStr = lastNameTextField.getText() ;
        String userNameStr = userNameTextField.getText() ;
        String passwordStr = passwordField.getText() ;
//        String birthDayStr = birthDayChoice.getText() ;
//        String birthMonthStr = birthMonthTextField.getText() ;
//        String birthYearStr = birthYearTextField.getText() ;
        user.setFirstName(firstNameStr);
        user.setLastName(lastNameStr);
        user.setUserName(userNameStr);
        user.setPassword(passwordStr);
//        user.setBirthDay(birthDayStr);
//        user.setBirthMonth(birthMonthStr);
//        user.setBirthYear(birthYearStr);

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
