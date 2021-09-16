package ku.cs.shop.controllers.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ku.cs.shop.models.User ;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class RegisterController {

    // controller เชื่อมต่อกับ view เพื่อรับข้อมูล
    User user = new User();
    @FXML private TextField firstNameTextField ;
    @FXML private TextField lastNameTextField ;
    @FXML private TextField userNameTextField ;
    @FXML private PasswordField passwordField ;
    @FXML private PasswordField checkPasswordField ;
    @FXML private ComboBox<String> birthDayChoice ;
    @FXML private ComboBox<String> birthMonthChoice ;
    @FXML private ComboBox<String> birthYearChoice ;
    @FXML private Label passwordCompareLabel ;
    @FXML private Label registerErrorLabel ;
    @FXML private Label passwordConditionCheckLabel ;
    @FXML private Label userNameCheckLabel ;
    @FXML private ImageView imageView ;
    @FXML private AnchorPane registerAnchorPane ;

    private File selectedImage ;
    private String imageName ;

    private ObservableList dayList = FXCollections.observableArrayList() ;
    private ObservableList monthList = FXCollections.observableArrayList() ;
    private ObservableList yearList = FXCollections.observableArrayList() ;

    @FXML
    public void initialize () {
        lodeYearData();
    }

    @FXML
    public void handleAddImageButton (ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Picture");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*jpg"));
        selectedImage = fileChooser.showOpenDialog(null);

        if (selectedImage != null) {
            Image image = new Image(selectedImage.toURI().toString());
            imageView.setImage(image);
        }
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

    private void lodeYearData() {
        yearList.removeAll(yearList) ;
        int i = 2009;
        while ( i >= 1940){
            yearList.add(""+i) ;
            i-- ;
        }
        birthYearChoice.getItems().addAll(yearList) ;
    }

    public void setMonthChoice(ActionEvent event) {
        birthMonthChoice.setValue("เดือน");
        birthMonthChoice.getItems().removeAll(monthList) ;
        birthDayChoice.getItems().removeAll(dayList) ;
        monthList.removeAll(monthList) ;
        lodeMonthData();
    }
    public void setDayChoice(ActionEvent event) {
        birthDayChoice.setValue("วัน");
        birthDayChoice.getItems().removeAll(dayList) ;
        dayList.removeAll(dayList) ;
        lodeDayData();
    }

    private void lodeMonthData() {
        birthMonthChoice.getItems().removeAll(monthList);
        monthList.removeAll(monthList) ;
        String month = "มกราคม,กุมภาพันธ์,มีนาคม,เมษายน,พฤษภาคม,มิถุนายน,กรกฎาคม,สิงหาคม,กันยายน,ตุลาคม,พฤศจิกายน,ธันวาคม" ;
        String[] arr = month.split(",") ;
        int i = 0 ;

        while ( i < 12){
            monthList.add(arr[i]) ;
            i++ ;
        }
        birthMonthChoice.getItems().addAll(monthList) ;
    }

    private void lodeDayData() {
        birthDayChoice.getItems().removeAll(dayList) ;
        dayList.removeAll(dayList);
        int i = 1 , maxDay, year = Integer.parseInt(birthYearChoice.getValue()) ;
        String month = birthMonthChoice.getValue() ;

        if(month.contains("คม")) { maxDay = 31 ; }
        else if(month.contains("ยน")) { maxDay = 30 ; }
        else if(year%4 == 0) { maxDay = 29 ; }
        else { maxDay = 28 ; }

        while ( i <= maxDay){
            dayList.add(""+i) ;
            i++ ;
        }
        birthDayChoice.getItems().addAll(dayList) ;
    }

    //ปุ่ม register
    @FXML
    public void handleRegisterButton(ActionEvent actionEvent) {

        String firstNameStr = firstNameTextField.getText() ;
        String lastNameStr = lastNameTextField.getText() ;
        String userNameStr = userNameTextField.getText() ;
        String passwordStr = passwordField.getText() ;
        String birthDayStr = birthDayChoice.getValue() ;
        String birthMonthStr = birthMonthChoice.getValue() ;
        String birthYearStr = birthYearChoice.getValue() ;
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

        if (selectedImage != null) {
            imageName =  userNameTextField.getText() + "-"
                    + LocalDate.now().getYear() + "-"
                    + LocalDate.now().getMonth() + "-"
                    + LocalDate.now().getDayOfMonth() + "-"
                    + LocalDateTime.now().getHour() + LocalDateTime.now().getMinute() + LocalDateTime.now().getSecond() + ".png" ;

            user.copyImageToPackage(selectedImage , imageName) ;
            user.setImageName(imageName);
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
