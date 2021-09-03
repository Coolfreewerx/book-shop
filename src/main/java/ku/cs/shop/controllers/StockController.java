package ku.cs.shop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.shop.models.Book;
import ku.cs.shop.controllers.SellerController;

import java.util.ArrayList;

public class StockController {
    @FXML
    private Label bookNameLabel, bookTypeLabel, bookPriceLabel, bookStockLabel;
    @FXML
    private ImageView bookImageView;
    @FXML
    private Button increaseButton, decreaseButton;

    private Book book;

    public void setData(Book book){
//        this.book = book;
//        bookNameLabel.setText(book.getBookName());
//        bookTypeLabel.setText(book.getBookType());
//        bookPriceLabel.setText(Double.toString(book.getBookPrice()));
//        bookStockLabel.setText(Integer.toString(book.getBookStock()));

//        bookImageView.setImage();
//        bookNameLabel.setText(book.getBookName());
//        bookTypeLabel.setText(book.getBookType());
//        bookPriceLabel.setText(Double.toString(book.getBookPrice()));
//        bookStockLabel.setText(Integer.toString(book.getBookStock()));
//        Image image = new Image(getClass().getResourceAsStream(book.getBookImg()));
//        bookImageView.setImage(image);
    }
//    public void handleDecreaseStockButton(ActionEvent actionEvent) {
//        booksList.get(1).decreaseStock(1);
//    }
//    public void handleIncreaseStockButton(ActionEvent actionEvent) { booksList.get(1).increaseStock(1); }

}
