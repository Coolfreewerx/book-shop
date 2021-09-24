package ku.cs.shop.controllers.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import ku.cs.shop.models.User;
import ku.cs.shop.models.UserList;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class DetailUser {
    @FXML private GridPane gridPaneInHead;
    @FXML private Label birthdayLabel;
    @FXML private Label birthMonthLabel;
    @FXML private Label birthYearLabel;
    @FXML private Label sexLabel;
    @FXML private Label phoneLabel;
    @FXML private Label lastnameLabel;
    @FXML private Label firstnameLabel;
    @FXML private Label passwordLabel;
    @FXML private Label usernameLabel;
    @FXML private ImageView userImageView ;


    private ArrayList<User> usersList = new ArrayList<>();

    private UserList userList ;
    private User user ;

    public void initialize(){
        userList = (UserList)FXRouter.getData() ;
        user = userList.getCurrentUser() ;
        showHead();
        showData();
    }

    public void showData(){ // โชว์ข้อมูลส่วนตัวของผู้ใช้ระบบ
        usernameLabel.setText(user.getUserName());
        firstnameLabel.setText(user.getFirstName());
        lastnameLabel.setText(user.getLastName());
        birthdayLabel.setText(user.getBirthDay());
        birthMonthLabel.setText(user.getBirthMonth());
        birthYearLabel.setText(user.getBirthYear());
        sexLabel.setText(user.getSex());
        phoneLabel.setText(user.getPhone());
        userImageView.setImage(new Image(user.getImagePath())) ;
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
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleEBookButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าหนังสือ E-book
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleAllTypeBookButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าหนังสือทั้งหมด
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleToEditInformationPageButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าแก้ไขข้อมูลส่วนตัว
        try {
            com.github.saacsos.FXRouter.goTo("editInformation");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า editInformation ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void showHead(){ //แสดงหัวเพจ
        try{
            FXMLLoader fxmlLoaderHead = new FXMLLoader();
            fxmlLoaderHead.setLocation(getClass().getResource("/ku/cs/headPage.fxml"));
            gridPaneInHead.add(fxmlLoaderHead.load(), 0,0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
