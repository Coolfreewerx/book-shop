package ku.cs.shop.controllers.system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import ku.cs.shop.models.User;
import com.github.saacsos.FXRouter;
import java.io.IOException;
import ku.cs.shop.models.UserList;

public class HeadController {
    private User user;
    private Pane view;
    private UserList userList ;
    @FXML private HBox hBoxHeadPage;
    @FXML private Button status;
    @FXML private Label usernameInHead;
    @FXML private ImageView img;

    @FXML
    void handleAllTypeBookButton(ActionEvent event) {

    }
    @FXML
    public void initialize() {
        System.out.println("initialize HeadController");
        userList = (UserList)FXRouter.getData() ;
        user = userList.getCurrentUser() ;
        pagesHeader();
    }

    public void pagesHeader() { // กำหนดข้อมูลตรงส่วน head page
        usernameInHead.setText(user.getUserName());
        img.setImage(new Image(user.getImagePath()));
        if(user.getShopName().equals("ยังไม่ได้สมัครเป็นผู้ขาย")){
            status.setText("User");
        }else{
            status.setText("Seller");
        }
    }

    @FXML
    public void handleToHomeButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้า home
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleToInformationButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้า หนังสือทั้งหมด (เพจหลัก)
        try {

            FXRouter.goTo("detailUser", userList);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า detailUser ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
}
