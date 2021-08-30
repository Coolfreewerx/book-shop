package ku.cs.shop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import ku.cs.shop.models.Book;
import ku.cs.shop.models.Seller;
import java.io.IOException;

import java.util.ArrayList;

public class SellerController {
    @FXML
    private ScrollPane scoll;

    @FXML
    private GridPane grid;
    BookDetailDataSource data = new BookDetailDataSource("src/main/java/ku/cs/shop/bookDetail.csv");
    @FXML private Label bookStockLabel0,bookNameLabel0,bookPriceLabel0,bookStockLabel1,bookNameLabel1,bookPriceLabel1,bookStockLabel2,bookNameLabel2,bookPriceLabel2 ;
    private ArrayList<Book> booksList = new ArrayList<>();

    @FXML
    public void initialize() {
        for(int i = 0; i < booksList.size(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/resource/ku.cs/stock"));

            StockController stockController = fxmlLoader.getController();
            //stockController.setData();
        }


    }


    public void handleDecreaseStockButton(ActionEvent actionEvent) {
        booksList.get(1).decreaseStock(1);
    }

    public void handleIncreaseStockButton(ActionEvent actionEvent) {

        booksList.get(1).increaseStock(1);
    }

//    public void handlSetBookDetailButton(ActionEvent actionEvent) {
//        String setBookDetail = setBookDetailTextField.getText();
//        book.setBookDetail(setBookDetail);
//    }
//
//    public void handleSetBookPriceButton(ActionEvent actionEvent) {
//        String setBookPrice = setBookPriceTextField.getText();
//        book.setBookPrice(setBookPrice);
//    }
//
//    public void handleSetBookTypeButton(ActionEvent actionEvent) {
//        String setBookType = setBookTypeTextField.getText();
//        book.setBookType(setBookType);
//    }
//
//    public void handleSetBookNameButton(ActionEvent actionEvent) {
//        String setBookName = setBookNameTextField.getText();
//        book.setBookName(setBookName);
//    }
}
