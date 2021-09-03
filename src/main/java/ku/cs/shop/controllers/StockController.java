package ku.cs.shop.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import ku.cs.shop.models.Book;

public class StockController {
    @FXML
    private Label bookNameLabel, bookTypeLabel, bookPriceLabel, bookStockLabel;
    @FXML
    private ImageView bookImageView;
    @FXML
    private Button increaseButton, decreaseButton;

    private Book book;

    public void setData(Book book){
        this.book = book;
        bookNameLabel.setText(book.getBookName());
        bookTypeLabel.setText(book.getBookType());
        bookPriceLabel.setText(Double.toString(book.getBookPrice()));
        bookStockLabel.setText(Integer.toString(book.getBookStock()));
//        bookImageView.setImage();
    }

}
