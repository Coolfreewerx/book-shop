package ku.cs.shop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import ku.cs.shop.models.Book;
import ku.cs.shop.models.Seller;
import ku.cs.shop.controllers.StockController;
import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SellerController implements Initializable {
    @FXML
    private ScrollPane scoll;
    @FXML
    private GridPane grid;

    //BookDetailDataSource data = new BookDetailDataSource("src/main/java/ku/cs/shop/bookDetail.csv");
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Book> getData() {
        ArrayList<Book> books = new ArrayList<>();
        Book book;

        for(int i = 0 ; i < 5; i++){
            book = new Book();
            book.setBookName("โฮริมิยะ สาวมั่นกับนายมืดมน");
            book.setBookPrice(80.25);
            book.setBookType("หนังสือการ์ตูน");
            book.setBookStock(10);
            books.add(book);
        }
        return books;
    }

    public void initialize(URL location, ResourceBundle resource){
        books.addAll(getData());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < books.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/stock.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                StockController stockController = fxmlLoader.getController();
                stockController.setData(books.get(i));

                grid.add(anchorPane, column,row++); // child,col,row
                GridPane.setMargin(anchorPane, new Insets(10));

            }
        }catch(IOException e){
                e.printStackTrace();
            }
    }


}
