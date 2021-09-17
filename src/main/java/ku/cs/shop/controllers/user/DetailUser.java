package ku.cs.shop.controllers.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import ku.cs.shop.models.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class DetailUser extends User {
    @FXML private HBox hBoxSellerStock;
    @FXML private Label birthdayLabel;
    @FXML private Label birthMonthLabel;
    @FXML private Label birthYearLabel;
    @FXML private Label sexLabel;
    @FXML private Label phoneLabel;
    @FXML private Label LastnameLabel;
    @FXML private Label firstnameLabel;
    @FXML private Label passwordLabel;
    @FXML private Label usernameLabel;
    @FXML private Label birthdayInEditInformationLabel;
    @FXML private Label birthMonthInEditInformationLabel;
    @FXML private Label birthYearInEditInformationLabel;
    @FXML private Label usernameInEditInformationLabel;
    @FXML private TextField lastnameTextField;
    @FXML private TextField firstnameTextField;
    @FXML private TextField phoneNumberTextField;
    @FXML private PasswordField passwordTextField;
    @FXML private PasswordField recheckPasswordTextField;
    @FXML private MenuButton sexChoice;
    private ArrayList<User> usersList = new ArrayList<>();

    public String showData(){ // โชว์ข้อมูลส่วนตัวของผู้ใช้ระบบ
        User user = new User("src/main/java/ku/cs/shop/userData.csv");
        usersList = user.readData();

        for(int i = 0; i < usersList.size(); i++){
            System.out.println(usersList.get(i).getUserName() + " "+ getUserLogin());
            if(usersList.get(i).getUserName().equals(getUserLogin())){
                usernameLabel.setText(usersList.get(i).getUserName());
                birthdayLabel.setText(usersList.get(i).getBirthDay());
                birthMonthLabel.setText(usersList.get(i).getBirthMonth());
                birthYearLabel.setText(usersList.get(i).getBirthYear());
            }
        }
        return null;
    }

    public void initialize(){
        showData();
    }

    @FXML
    public void handleCartoonBookButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าหนังสือการ์ตูน
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า profile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleMagazineBookButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าหนังสือ Magazine
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า profile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleNovelBookButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าหนังสือนิยาย
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า profile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleEducationalBookButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าหนังสือเรียน
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า profile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleEBookButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าหนังสือ E-book
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า profile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleAllTypeBookButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าหนังสือทั้งหมด
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า profile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleToHomeButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้า home
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า profile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

//    public void setSexChoiceMenuButton(ActionEvent event) { // set ตัวเลือกเพศให้แสดงเพศที่เลือก
//        sexChoice.setValue("เดือน");
//        birthMonthChoice.getItems().removeAll(monthList) ;
//        birthDayChoice.getItems().removeAll(dayList) ;
//        monthList.removeAll(monthList) ;
//        lodeMonthData();
//    }

//    private void lodeSex() {
//        sexChoice.getItems().removeAll(monthList);
//        monthList.removeAll(monthList) ;
//        String sex = "หญิง,ชาย,ไม่ระบุ" ;
//        String[] arr = sex.split(",") ;
//        int i = 0 ;
//
//        while ( i < 12){
//            monthList.add(arr[i]) ;
//            i++ ;
//        }
//        birthMonthChoice.getItems().addAll(monthList) ;
//    }
}
