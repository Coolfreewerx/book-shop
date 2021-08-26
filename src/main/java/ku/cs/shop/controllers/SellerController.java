package ku.cs.shop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ku.cs.shop.models.Book;
import ku.cs.shop.models.Seller;
import java.io.IOException;

import java.util.ArrayList;

public class SellerController {
    BookDetailDataSource data = new BookDetailDataSource("src/main/java/ku/cs/shop/bookDetail.csv");
    @FXML private Label bookStockLabel0,bookNameLabel0,bookPriceLabel0,bookStockLabel1,bookNameLabel1,bookPriceLabel1,bookStockLabel2,bookNameLabel2,bookPriceLabel2 ;
    private ArrayList<Book> booksList = new ArrayList<>();

    @FXML
    public void initialize()
    {
        booksList = data.readData();
        bookNameLabel0.setText(booksList.get(0).getBookName());
        bookPriceLabel0.setText(Double.toString(booksList.get(0).getBookPrice()));
        bookStockLabel0.setText(Double.toString(booksList.get(0).getBookStock()));

        bookNameLabel1.setText(booksList.get(1).getBookName());
        bookPriceLabel1.setText(Double.toString(booksList.get(1).getBookPrice()));
        bookStockLabel1.setText(Double.toString(booksList.get(1).getBookStock()));

        bookNameLabel2.setText(booksList.get(2).getBookName());
        bookPriceLabel2.setText(Double.toString(booksList.get(2).getBookPrice()));
        bookStockLabel2.setText(Double.toString(booksList.get(2).getBookStock()));

    }
    @FXML
    private TextField editStockTextField;
    @FXML
    private Button increaseBookButton,decreaseBookButton;

    public void handleEditStockButton(ActionEvent actionEvent) {
        String editStock = editStockTextField.getText();
        int edit = Integer.parseInt(editStock);
        booksList.get(0).editStock(edit);
    }
    public void handleDecreaseStockButton(ActionEvent actionEvent) {
        booksList.get(1).decreaseStock(1);
    }

    public void handleIncreaseStockButton(ActionEvent actionEvent) {

        booksList.get(1).increaseStock(1);
    }
//
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
