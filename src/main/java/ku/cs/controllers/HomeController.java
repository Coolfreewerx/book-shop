package ku.cs.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class HomeController {
    @FXML
    public void handleLinkToTypeButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าประเภทของหนังสือ
        try {
            FXRouter.goTo("profile");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า profile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void handleLinkToBestSellerButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าหนังสือขายดี
        try {
            FXRouter.goTo("profile");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า profile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void handleLinkToRecommendButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าหนังสือแนะนำ
        try {
            FXRouter.goTo("profile");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า profile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void handleLinkToLoginButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าล็อกอิน
        try {
            FXRouter.goTo("profile");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า profile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void handleLinkToBasketBookButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าตะกร้าสินค้า
        try {
            FXRouter.goTo("basket");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า basket ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
