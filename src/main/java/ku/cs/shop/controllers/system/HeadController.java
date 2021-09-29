package ku.cs.shop.controllers.system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import com.github.saacsos.FXRouter;
import ku.cs.shop.models.Account;
import ku.cs.shop.models.AccountList;

import java.io.IOException;

public class HeadController {
    private Account account;
    private Pane view;
    private AccountList accountList ;
    @FXML private HBox hBoxHeadPage;
    @FXML private Button status;
    @FXML private Label usernameInHead;
    @FXML private ImageView img;

    @FXML
    void handleAllTypeBookButton(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("pageBookType");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า pageBookType ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void handlePageBookTypeButton(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("seller");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า pageBookType ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    void handleSubTypeBookButton(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("pageBookType");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า pageBookType ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void initialize() {
        accountList = (AccountList) FXRouter.getData() ;
        account = accountList.getCurrentAccount() ;
        pagesHeader();
    }

    public void pagesHeader() { // กำหนดข้อมูลตรงส่วน head page
        usernameInHead.setText(account.getUserName());
        img.setImage(new Image(account.getImagePath()));
        if(account.getShopName().equals("ยังไม่ได้สมัครเป็นผู้ขาย")){
            status.setText("User");
        }else{
            status.setText("Seller");
        }
    }

    @FXML
    public void handleToAllBookPageButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้า home ที่ส่วนหัวของเพจ
        try {
            com.github.saacsos.FXRouter.goTo("home", accountList);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleToInformationButton(ActionEvent actionEvent) {
        try {

            FXRouter.goTo("accountDetail", accountList);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า detailUser ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
}
