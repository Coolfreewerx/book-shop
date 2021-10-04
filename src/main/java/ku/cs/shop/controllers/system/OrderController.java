package ku.cs.shop.controllers.system;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.shop.models.Book;

public class OrderController {
    @FXML private ImageView bookImageView;
    @FXML private Label bookNameLabel;
    @FXML private Label numBookLabel;
    @FXML private Label statusLabel;
    @FXML private Label notificationForStock;
    @FXML private Label nameCustomerLabel;
    @FXML private Label contactCustomerLabel;
    @FXML private Label numPriceLabel;
    private Book book;

    public void setData(Book book) {
        this.book = book;
    }

    public void changeData() {
        bookNameLabel.setText(book.getBookName());
        numPriceLabel.setText(book.getBookPrice() + "");
        bookImageView.setImage(new Image(book.getPicturePath()));
    }

}
