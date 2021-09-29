package ku.cs.shop.controllers.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import ku.cs.shop.models.Account;
import ku.cs.shop.models.AccountList;

import java.io.IOException;
import java.util.ArrayList;

public class AccountDetailController {
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
    @FXML private Label addressLabel;


    private ArrayList<Account> accountsList = new ArrayList<>();
    private AccountList accountList ;
    private Account account ;

    public void initialize(){
        accountList = (AccountList) FXRouter.getData() ;
        account = accountList.getCurrentAccount() ;
        showHead();
        showData();
    }

    public void showData(){ // โชว์ข้อมูลส่วนตัวของผู้ใช้ระบบ
        userImageView.setImage(new Image(account.getImagePath())) ;
        usernameLabel.setText(account.getUserName());
        firstnameLabel.setText(account.getFirstName());
        lastnameLabel.setText(account.getLastName());
        birthdayLabel.setText(account.getBirthDay());
        birthMonthLabel.setText(account.getBirthMonth());
        birthYearLabel.setText(account.getBirthYear());
        sexLabel.setText(account.getSex().replace("null", "ยังไม่ได้เพิ่มข้อมูลเพศ"));
        phoneLabel.setText(account.getPhone().replace("null", "ยังไม่ได้เพิ่มข้อมูลเบอร์โทร"));
        addressLabel.setText(account.getAddress().replace("null", "ยังไม่ได้เพิ่มข้อมูลที่อยู่"));
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
            com.github.saacsos.FXRouter.goTo("editInformation", accountList);
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

    @FXML
    public void handleToAccountDetailButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("accountDetail" ,accountList);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า accountDetail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleToSellerButton(ActionEvent actionEvent) {
        if (true) {
            try {
                com.github.saacsos.FXRouter.goTo("sellerHaventApply",accountList);
            } catch (IOException e) {
                System.err.println("ไปที่หน้า sellerHaventApply ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
    }
}
