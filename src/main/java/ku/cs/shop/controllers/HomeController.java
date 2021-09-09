package ku.cs.shop.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import ku.cs.shop.models.Book;
import ku.cs.shop.services.BookDetailDataSource;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML private HBox head;
    @FXML private GridPane gridHead;
    @FXML private GridPane grid;
    @FXML private AnchorPane pane;
    @FXML private Label bookNameLabel;
    @FXML private Label bookPriceLabel;
    @FXML private Label bookShopLabel;

    private BookDetailDataSource data = new BookDetailDataSource("src/main/java/ku/cs/shop/bookDetail.csv");
    private ArrayList<Book> books = data.readData();

        public void initialize (URL location, ResourceBundle resource){
            try {
                FXMLLoader fxmlLoaderHead = new FXMLLoader();
                fxmlLoaderHead.setLocation(getClass().getResource("/ku/cs/headWhenLogin.fxml"));
                gridHead.add(fxmlLoaderHead.load(), 0, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int column = 1;
            int row = 1;
            try {
                for (int i = 0; i < 3; i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/ku/cs/item.fxml"));

                    grid.add(fxmlLoader.load(), column++, row); // child,col,row
                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(books.get(i));
                    itemController.changeData();

                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_COMPUTED_SIZE);

                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_COMPUTED_SIZE);
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
}
