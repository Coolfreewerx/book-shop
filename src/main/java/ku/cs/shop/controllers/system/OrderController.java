package ku.cs.shop.controllers.system;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.shop.models.Book;

public class OrderController {
    @FXML private ImageView bookImageView;
    @FXML private Label bookNameLabel;
    @FXML private Label bookTypeLabel;
    @FXML private Label bookPriceLabel;
    @FXML private Label notificationForStock;
    private Book book;

    public void setData(Book book) {
        this.book = book;
    }

    public void changeData() {
        bookNameLabel.setText(book.getBookName());
        bookPriceLabel.setText(book.getBookPrice() + "");
        bookTypeLabel.setText(book.getBookType());
        bookImageView.setImage(new Image(book.getPicturePath()));
    }

}
