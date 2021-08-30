package ku.cs.shop.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ku.cs.shop.models.Book;
import ku.cs.shop.controllers.BookDetailController;
import ku.cs.shop.models.BookList;

import java.io.IOException;
import java.util.ArrayList;

public class PageBookCartoon
{

//    BookDetailController infoOfBook = new BookDetailController();

    BookDetailDataSource data = new BookDetailDataSource("src/main/java/ku/cs/shop/bookDetail.csv");
    @FXML private Label bookNameLabel;
    @FXML private Label bookShopLabel;
    @FXML private Label bookPriceLabel;

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
    public void handleFullDetailButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้ารายละเอียดหนังสือ
        try {
            com.github.saacsos.FXRouter.goTo("bookDetail");
        } catch (IOException e) {
            System.err.println("ไปที่หน้ารายละเอียดหนังสือไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleFullDetailButton2(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้ารายละเอียดหนังสือ
        try {
            com.github.saacsos.FXRouter.goTo("bookDetail");
        } catch (IOException e) {
            System.err.println("ไปที่หน้ารายละเอียดหนังสือไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

//    private void handleFullDetailButton() {
//        BookList.getSelectionModel().selectedItemProperty().addListener(
//                new ChangeListener<MemberCard>() {
//                    @Override
//                    public void changed(ObservableValue<? extends Book> observable,
//                                        Book oldValue, Book newValue) {
//                        System.out.println(newValue + " is selected");
//                        showSelectedBookDetailCard(newValue);
//                    }
//                });
//    }
}
