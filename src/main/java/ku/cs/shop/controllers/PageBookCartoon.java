package ku.cs.shop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ku.cs.shop.models.Book;
import ku.cs.shop.controllers.BookDetailController;

import java.io.IOException;
import java.util.ArrayList;

public class PageBookCartoon {

    BookDetailController infoOfBook = new BookDetailController();

    BookDetailDataSource data = new BookDetailDataSource("src/main/java/ku/cs/shop/bookDetail.csv");
    @FXML private Label bookNameLabel, bookShopLabel, bookPriceLabel ;
    private ArrayList<Book> booksList = new ArrayList<>();

    @FXML // เริ่มต้นกับ indexนั้นๆ
    public void setDataOverview() {
        booksList = data.readData();
        bookNameLabel.setText(booksList.get(0).getBookName());
        bookShopLabel.setText(booksList.get(0).getBookShop());
        bookPriceLabel.setText(Double.toString(booksList.get(0).getBookPrice()) + " Baht.");
    }


    @FXML
    public void handleCartoonBookButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าหนังสือการ์ตูน
        try {
            com.github.saacsos.FXRouter.goTo("pageBookCartoon");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าเพจของหนังสือการ์ตูนไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleMagazineButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้า Magazine
        try {
            com.github.saacsos.FXRouter.goTo("pageMagazine");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าเพจของ Magazine ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleAllTypeBookButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้า หนังสือทั้งหมด (เพจหลัก)
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าเพจหลักไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
<<<<<<< HEAD
    public void handleFullDetailButton(ActionEvent actionEvent) {
        //ปุ่มสำหรับกดไปหน้ารยาละเอียดหนังสือ
=======
    public void handleFullDetailButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้ารายละเอียดหนังสือ
>>>>>>> e8ea2377439949380a47c2786a3592068bfb5755
        try {
            com.github.saacsos.FXRouter.goTo("bookDetail");
        } catch (IOException e) {
            System.err.println("ไปที่หน้ารายละเอียดหนังสือไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
<<<<<<< HEAD
    public void handleFullDetailButton2(ActionEvent actionEvent) {
        //ปุ่มสำหรับกดไปหน้ารยาละเอียดหนังสือ
=======
    public void handleFullDetailButton2(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้ารายละเอียดหนังสือ
>>>>>>> e8ea2377439949380a47c2786a3592068bfb5755
        try {
            com.github.saacsos.FXRouter.goTo("bookDetail");
        } catch (IOException e) {
            System.err.println("ไปที่หน้ารายละเอียดหนังสือไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }


}
