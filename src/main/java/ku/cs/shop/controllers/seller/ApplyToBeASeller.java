package ku.cs.shop.controllers.seller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.shop.models.User;

import java.io.IOException;

public class ApplyToBeASeller{ //สมัครเป็นผู้ขายสินค้า
    private User user;
    private String shopName;
    private String password;
    private String passwordRecheck;
    @FXML private Label usernameLabel;
    @FXML private Button submitButton;
    @FXML private TextField nameShopTextField;
    @FXML private PasswordField passwordTextField1;
    @FXML private PasswordField passwordTextField2;
    @FXML private Label notificationShopName;
    @FXML private Label notificationPassword;


    @FXML
    public void handleKeyCheckShopName(){
        notificationShopName.setText(user.checkShopNameCondition(nameShopTextField.getText()));
    }
    @FXML
    public void handleKeyCheckPassword() {
        notificationPassword.setText(user.comparePassword(passwordTextField1.getText(),passwordTextField2.getText()));
    }

    @FXML
    public void handleAddSellerStockButton(){
        shopName = nameShopTextField.getText();
        password = passwordTextField1.getText();
        passwordRecheck = passwordTextField1.getText();
//        if ( !(user.checkShopNameHaveUsed(shopName)) && (user.comparePasswordBoolean(password,passwordRecheck))){
            try {
                com.github.saacsos.FXRouter.goTo("sellerStock");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า sellerStock ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
//        }
//        else { return;}
    }

    @FXML
    public void handleToHomeButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้า home
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าเพจหลักไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
