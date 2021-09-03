package ku.cs.shop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import ku.cs.shop.models.Book;
import ku.cs.shop.controllers.HomeController;

import java.io.IOException;
import java.util.ArrayList;

public class ItemController {

    private Book book;

    @FXML private Label bookNameLabel;
    @FXML private Label bookPriceLabel;
    @FXML private Label bookShopLabel;
    @FXML private ImageView img;


    @FXML
    public void handleSeeFullDetailButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("bookDetail");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า bookDetail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void setData(Book book) {
    }
}
