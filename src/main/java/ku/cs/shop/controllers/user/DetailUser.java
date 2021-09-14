package ku.cs.shop.controllers.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import ku.cs.shop.models.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DetailUser extends User {
    @FXML private HBox hBoxSellerStock;
    @FXML private Label usernameInHeadLabel;
    @FXML private Label birthdayLabel;
    @FXML private Label birthMonthLabel;
    @FXML private Label birthYearLabel;
    @FXML private Label sexLabel;
    @FXML private Label phoneLabel;
    @FXML private Label LastnameLabel;
    @FXML private Label firstnameLabel;
    @FXML private Label passwordLabel;
    @FXML private Label usernameLabel;


    public String showData(){
        if(super.getUserName().equals(readData())){
           usernameInHeadLabel.setText(getUserName());
        }
        return null;
    }

    public void initialize(){
        showData();
    }

    @FXML
    public void handleCartoonBookButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าหนังสือขายดี
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า profile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleMagazineButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าหนังสือขายดี
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า profile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleAllTypeBookButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าหนังสือขายดี
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า profile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
