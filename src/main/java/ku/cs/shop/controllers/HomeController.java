package ku.cs.shop.controllers;

import com.github.saacsos.FXRouter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import ku.cs.shop.models.Book;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
//    @FXML private GridPane grid;
    @FXML private HBox head;
    @FXML private AnchorPane pane;

//    public void initialize() {
//        System.out.println("initialize HomeController");
//        headController = (HeadController) FXRouter.getData();
////        pane.getChildren().add(headController.pagesHeader());
//        System.out.println(pane);
////        System.out.println(headController.pagesHeader());
//        System.out.println(headController);
//    }

    @FXML
    private GridPane grid, gridHead;

    @FXML
    private Label bookNameLabel;
    private Label bookPriceLabel;
    private Label bookShopLabel;

//    public static void main(String[] args) {
//    String filename = "src/main/java/ku/cs/shop/bookDetail.csv";
//    Book book;
//
//        try {
//            CSVReader reader = new CSVReader(new FileReader(filename));
//            reader.readNext();
//            String[] bookDetail;
//            while (bookDetail = reader.readNext() != null) {
//                bookDetail.;
//            }
//
//        } catch (FileNotFoundException e) {
//            System.out.println("File not found");
//        }
//        } catch (IOException e) {
//            System.out.println("Item not found");
//        }
//    }

    private BookDetailDataSource data = new BookDetailDataSource("src/main/java/ku/cs/shop/bookDetail.csv");
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Book> getData() {
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Book> bookList = new ArrayList<>();
        bookList = data.readData();
        Book book;

//            ArrayList<Book> books = new ArrayList<>();
//            ArrayList<Book> bookList = new ArrayList<>();
//            bookList = data.readData();
//            Book book;

            for (int i = 0; i < 3; i++) {
                book = new Book();
                book.setBookName(bookList.get(i).getBookName());
                book.setBookPrice(bookList.get(i).getBookPrice());
                book.setBookShop(bookList.get(i).getBookShop());
                books.add(book);
            }
            return books;
        }

        public void initialize (URL location, ResourceBundle resource){
            try {
                FXMLLoader fxmlLoaderHead = new FXMLLoader();
                fxmlLoaderHead.setLocation(getClass().getResource("/ku/cs/headWhenLogin.fxml"));
//                AnchorPane anchorPaneHead = fxmlLoaderHead.load();
                gridHead.add(fxmlLoaderHead.load(), 0, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            books.addAll(getData());

            int column = 1;
            int row = 1;
            try {
                for (int i = 0; i < 6; i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/ku/cs/item.fxml"));

//                    AnchorPane anchorPane = fxmlLoader.load();

//                    ItemController itemController = fxmlLoader.getController();
//                    itemController.setData(books.get(i));

                    grid.add(fxmlLoader.load(), column++, row); // child,col,row
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_COMPUTED_SIZE);

                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_COMPUTED_SIZE);
//                    GridPane.setMargin(fxmlLoader.load(), new Insets(200,200,30,0));

                }
            } catch (IOException e) {
                e.printStackTrace();
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
    //    private BookDetailDataSource data = new BookDetailDataSource("src/main/java/ku/cs/shop/bookDetail.csv");
//    List<Book> books = data.readdata();
//    List<Book> book = new ArrayList<>();
//
//    // แสดงข้อมูล ชื่อหนังสือ ชื่อร้านค้า ราคา จาก CSV
//    private List<Book> showData() {
//        for (Book book : books) {
//            book.getBookName();
//            book.getBookPrice();
//            book.getBookShop();
//            book.getBookImg();
//        } implements Initializable
//
//        return book;
//    }
//
//    CSVReader reader;
//
//    // นำข้อมูลแต่ละ index ใน csv มาแสดงยัง grid
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        book.addAll(showData());
//
//        for (int i = 0 ; i < book.size() ; i++) {
//            FXMLLoader fxmlloader = new FXMLLoader();
//        }
//    }
}
