package ku.cs.shop.controllers.system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ku.cs.shop.models.Book;
import ku.cs.shop.services.BookDetailDataSource;

import java.io.IOException;
import java.util.ArrayList;

public class BookDetailController
{
    BookDetailDataSource data = new BookDetailDataSource("src/main/java/ku/cs/shop/bookDetail.csv");
    @FXML private Label bookNameLabel;
    @FXML private Label bookShopLabel;
    @FXML private Label bookStatusLabel;
    @FXML private Label bookTypeLabel;
    @FXML private Label bookPageLabel;
    @FXML private Label bookISBNLabel;
    @FXML private Label bookPublisherLabel;
    @FXML private Label bookAuthorLabel;
    @FXML private Label bookDetailLabel;
    @FXML private Label bookPriceLabel;


    private ArrayList<Book> booksList = new ArrayList<>();

    @FXML
    public void initialize()
    {
        BookDetailDataSource bookDetaildataSource = new BookDetailDataSource("src/main/java/ku/cs/shop/bookDetail.csv");
        booksList = bookDetaildataSource.readData();

        bookNameLabel.setText(booksList.get(0).getBookName());
        bookShopLabel.setText(booksList.get(0).getBookShop());
        bookStatusLabel.setText(booksList.get(0).getBookStatus());
        bookTypeLabel.setText(booksList.get(0).getBookType());
        bookPageLabel.setText(booksList.get(0).getBookPage());
        bookISBNLabel.setText(booksList.get(0).getBookISBN());
        bookPublisherLabel.setText(booksList.get(0).getBookPublisher());
        bookAuthorLabel.setText(booksList.get(0).getBookAuthor());
        bookDetailLabel.setText(booksList.get(0).getBookDetail());
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
}
