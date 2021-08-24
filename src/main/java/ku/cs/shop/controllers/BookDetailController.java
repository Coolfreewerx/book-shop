package ku.cs.shop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ku.cs.shop.models.Book;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BookDetailController
{
    bookDetailDataSource data = new bookDetailDataSource("src/main/java/ku/cs/shop/bookDetail.txt");
    @FXML private Label bookNameLabel, bookShopLabel, bookStatusLabel;
    private ArrayList<Book> booksList = new ArrayList<>();

    @FXML
    public void initialize()
    {
        booksList = data.readData();
        bookNameLabel.setText(booksList.get(0).getBookName());
        bookShopLabel.setText(booksList.get(0).getBookShop());
        bookStatusLabel.setText(booksList.get(0).getBookStatus());

    }

}
