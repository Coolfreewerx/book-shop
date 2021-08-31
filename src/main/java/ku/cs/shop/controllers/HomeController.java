package ku.cs.shop.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import ku.cs.shop.models.Book;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private GridPane grid;

    private BookDetailDataSource data = new BookDetailDataSource("src/main/java/ku/cs/shop/bookDetail.csv");
    List<Book> books = data.readData();
    List<Book> book = new ArrayList<>();

    // แสดงข้อมูล ชื่อหนังสือ ชื่อร้านค้า ราคา จาก CSV
    private List<Book> showData() {
        for (Book book : books) {
            book.getBookName();
            book.getBookPrice();
            book.getBookShop();
            book.getBookImg();
        }

        return book;
    }


    // นำข้อมูลแต่ละ index ใน csv มาแสดงยัง grid
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        book.addAll(showData());

        for (int i = 0 ; i < book.size() ; i++) {
            FXMLLoader fxmlloader = new FXMLLoader();
        }
    }


    @FXML
    public void handleLinkToBestSellerButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าหนังสือขายดี
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า profile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void handleLinkToRecommendButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าหนังสือแนะนำ
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า profile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void handleLinkToLoginButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าล็อกอิน
        try {
            FXRouter.goTo("login");
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

    @FXML
    public void handleCartoonBookButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าหนังสือการ์ตูน
        try {
            FXRouter.goTo("pageBookCartoon");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าเพจของหนังสือการ์ตูนไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleMagazineButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้า Magazine
        try {
            FXRouter.goTo("pageMagazine");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าเพจของ Magazine ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleAllTypeBookButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้า หนังสือทั้งหมด (เพจหลัก)
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าเพจหลักไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
