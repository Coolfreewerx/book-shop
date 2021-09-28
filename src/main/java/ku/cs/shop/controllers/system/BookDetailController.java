package ku.cs.shop.controllers.system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import ku.cs.shop.models.Account;
import ku.cs.shop.models.AccountList;
import ku.cs.shop.models.Book;
import ku.cs.shop.models.BookList;
import ku.cs.shop.services.BookDetailDataSource;

import java.io.IOException;
import java.util.ArrayList;

public class BookDetailController
{
    BookDetailDataSource data = new BookDetailDataSource("csv-data/bookDetail.csv");

    @FXML private Label bookName;
    @FXML private Label bookShop;
    @FXML private Label bookStatus;
    @FXML private Label bookDetail;
    @FXML private Label bookType;
    @FXML private Label bookISBN;
    @FXML private Label bookPage;
    @FXML private Label bookPublisher;
    @FXML private Label bookAuthor;
    @FXML private Label bookPrice;
    @FXML private Label typeLabel;
    @FXML private ImageView bookImg;
    @FXML private MenuButton bookTypeMenuItem;
    @FXML private GridPane gridPaneInHead;
    @FXML private Hyperlink bookDetailByShop;


    private AccountList accountList;
    private Account account;
    private Book book;

    @FXML
    public void initialize()
    {
        book = (Book)com.github.saacsos.FXRouter.getData();
//        accountList = (AccountList) com.github.saacsos.FXRouter.getData() ;
        showData();
        showHead();
    }

    public void showData() {
        bookName.setText(book.getBookName());
        bookShop.setText(book.getBookShop());
        bookStatus.setText(book.getBookStatus());
        bookDetail.setText(book.getBookDetail());
        bookType.setText(book.getBookType());
        bookISBN.setText(book.getBookISBN());
        bookPage.setText(book.getBookPage());
        bookPublisher.setText(book.getBookPublisher());
        bookAuthor.setText(book.getBookAuthor());
        typeLabel.setText(book.getBookType());
        bookImg.setImage(new Image(book.getPicturePath()));
        bookPrice.setText(String.format("%.02f",book.getBookPrice()) + " Bahts.");
    }


    @FXML
    public void handleAllTypeBookButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้า หนังสือทั้งหมด (เพจหลัก)
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleTypeBookButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("pageBookType");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า pageBookType ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleNovelBookButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("pageBookType");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า pageBookType ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleToInformationButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("detailUser");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า pageBookType ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleBackToMarket() {
        try {
            com.github.saacsos.FXRouter.goTo("pageBookType");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า pageBookType ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleToBookSortFromShop(ActionEvent actionevent) {
        try {
            System.out.println("Click to pageBookShop");
            com.github.saacsos.FXRouter.goTo("pageBookShop", accountList);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า pageBookShop ไม่ได้");
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
