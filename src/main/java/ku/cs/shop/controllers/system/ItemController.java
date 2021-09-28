package ku.cs.shop.controllers.system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.shop.models.AccountList;
import ku.cs.shop.models.Book;

import java.io.IOException;

public class ItemController {

    private Book book;
    private AccountList accountList;

    @FXML private Label bookNameLabel;
    @FXML private Label bookPriceLabel;
    @FXML private ImageView img;

    @FXML
    public void handleSeeFullDetailButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("bookDetail", book);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า bookDetail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void setData(Book book) {

        this.book = book;
        changeData();
    }

    public void changeData() {
        bookNameLabel.setText(book.getBookName());
        bookPriceLabel.setText(String.format("%.02f",book.getBookPrice()) + " Bahts.");
        img.setImage(new Image(book.getPicturePath()));
    }
}
