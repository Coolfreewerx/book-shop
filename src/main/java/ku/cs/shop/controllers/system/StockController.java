package ku.cs.shop.controllers.system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.shop.models.Book;

public class StockController {
    @FXML private ImageView bookImageView;
    @FXML private Label bookNameLabel;
    @FXML private Label bookTypeLabel;
    @FXML private Label bookPriceLabel;
    @FXML private Button increaseButton;
    @FXML private Button decreaseButton;
    @FXML private Label bookStockLabel;
    @FXML private Label notificationForStock;

    private Book book;

    public void setData(Book book) {
        this.book = book;
    }

    public void changeData() {
        bookNameLabel.setText(book.getBookName());
        bookPriceLabel.setText(book.getBookPrice() + "");
        bookTypeLabel.setText(book.getBookType());
        bookStockLabel.setText(book.getBookStock() + "");
        if (book.getBookStock() <= book.getLeastStock()){
            notificationForStock.setText("** มีสินค้าจำนวนน้อยในคลัง ** ");
        }
        else{
            notificationForStock.setText("");
        }
        bookImageView.setImage(new Image(book.getPicturePath()));
    }

    @FXML public void handleIncreaseButton(ActionEvent actionEvent){
        book.increaseStock();
        changeData();
    }
    @FXML public void handleDecreaseButton(ActionEvent actionEvent){
        book.decreaseStock();
        changeData();
    }

}
