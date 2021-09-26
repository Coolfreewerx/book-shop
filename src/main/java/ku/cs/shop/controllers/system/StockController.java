package ku.cs.shop.controllers.system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import ku.cs.shop.controllers.seller.EditBookController;
import ku.cs.shop.models.Book;
import ku.cs.shop.models.BookList;
import ku.cs.shop.services.BookDetailDataSource;
import ku.cs.shop.services.DataSource;

import java.io.IOException;

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
        bookImageView.setImage(new Image(book.getPicturePath()));
        bookStockLabel.setText(book.getBookStock() + "");
        if (book.getBookStock() <= book.getLeastStock()){
            notificationForStock.setText("** มีสินค้าจำนวนน้อยในคลัง ** ");
        }
        else{
            notificationForStock.setText("");
        }
        DataSource<BookList> dataSource;
        dataSource = new BookDetailDataSource("src/main/java/ku/cs/shop/bookDetail.csv");
        BookList bookList = dataSource.readData();
        dataSource.writeData(bookList);
    }

    @FXML public void handleIncreaseButton(ActionEvent actionEvent){
        book.increaseStock();
        changeData();
    }
    @FXML public void handleDecreaseButton(ActionEvent actionEvent){
        book.decreaseStock();
        changeData();
    }

    @FXML
    public void handleEditBookButton(ActionEvent actionEvent){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("src/main/resources/ku/cs/editStock.fxml"));
        EditBookController editBookController = new EditBookController(book);
//        editBookController.setData(book);
        //editBookController.changeData();
        try {
            com.github.saacsos.FXRouter.goTo("editStock");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า editStock ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
