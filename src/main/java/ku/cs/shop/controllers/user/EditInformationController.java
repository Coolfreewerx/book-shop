package ku.cs.shop.controllers.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import ku.cs.shop.models.User;
import ku.cs.shop.models.UserList;
import ku.cs.shop.services.UserDataSource;
import com.github.saacsos.FXRouter;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EditInformationController {
    private User user;
    private UserList userList ;
    private UserDataSource userDataSource ;

    @FXML private TextField lastnameTextField;
    @FXML private TextField firstnameTextField;
    @FXML private TextField phoneNumberTextField;
    @FXML private ComboBox<String> sexChoice;
    @FXML private PasswordField passwordField;
    @FXML private Label birthdayInEditInformationLabel;
    @FXML private Label birthMonthInEditInformationLabel;
    @FXML private Label birthYearInEditInformationLabel;
    @FXML private Label usernameInEditInformationLabel;
    @FXML private PasswordField recheckPasswordField;
    @FXML private Label passwordConditionCheckLabel;
    @FXML private Label passwordCompareLabel;

    private File selectedImage ;
    private String imageName;

    private ObservableList sexList = FXCollections.observableArrayList() ;

    @FXML
    public void initialize(){
        userList = (UserList) com.github.saacsos.FXRouter.getData() ;
        user = userList.getCurrentUser() ;
        showData();
    }
    public void showData(){
        usernameInEditInformationLabel.setText(user.getUserName());
        birthdayInEditInformationLabel.setText(user.getBirthDay());
        birthMonthInEditInformationLabel.setText(user.getBirthMonth());
        birthYearInEditInformationLabel.setText(user.getBirthYear());
    }

    @FXML //ทำงานเมื่อกรอกรหัส
    public void handleKeyPassword() {
        if (User.checkPasswordCondition(passwordField.getText())){
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
        passwordCompareLabel.setText(User.comparePassword(passwordField.getText(), recheckPasswordField.getText()));
    }

    public void setSexChoice(ActionEvent event) {
        sexChoice.setValue("เพศ");
        sexChoice.getItems().removeAll(sexList) ;
        sexList.removeAll(sexList);
        lodeSexData();
    }

    private void lodeSexData() {
        sexChoice.getItems().removeAll(sexList);
        sexList.removeAll(sexList) ;
        String sex = "หญิง,ชาย,ไม่ระบุ" ;
        String[] arr = sex.split(",") ;
        int i = 0 ;

        while ( i < 3){
            sexList.add(arr[i]) ;
            i++ ;
        }
        sexChoice.getItems().addAll(sexList) ;
    }

    @FXML
    public void handleGoToInformationPageWhenEditInformation(){ //ปุ่มกลับไปหน้าข้อมูลส่วนตัวหลังแก้ไขข้อมูลเสร็จ
        sendDataToWrite();
        try{
            FXRouter.goTo("detailUser");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า detailUser ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleGotoInformationPageWhenCancel(){ //ปุ่มกลับไปหน้าข้อมูลส่วนตัวเมื่อไม่ต้องการแก้ไขข้อมูลแล้ว
        try {
            FXRouter.goTo("detailUser");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า detailUser ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void sendDataToWrite() {
        //UserDataSource
        User user = new User(
                firstnameTextField.getText(),
                lastnameTextField.getText(),
                usernameInEditInformationLabel.getText(),
                passwordField.getText(),
                birthdayInEditInformationLabel.getText(),
                birthMonthInEditInformationLabel.getText(),
                birthYearInEditInformationLabel.getText(),
                imageName,
                phoneNumberTextField.getText(),
                sexChoice.getValue(),
                " ") ;

        userList.addUser(user);
        userDataSource.writeData(userList) ;
    }
}
