package ku.cs.shop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import ku.cs.shop.models.Book;
import ku.cs.shop.models.Seller;
import ku.cs.shop.controllers.StockController;
import ku.cs.shop.controllers.HeadController;
import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SellerController implements Initializable {
    @FXML
    private ScrollPane scoll;
    @FXML
    private GridPane grid,gridHead;
    @FXML
    private HBox hBoxSellerStock;

    private BookDetailDataSource data = new BookDetailDataSource("src/main/java/ku/cs/shop/bookDetail.csv");
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Book> getData() {
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Book> bookList = new ArrayList<>();
        bookList = data.readData();
        Book book;

        for(int i = 0 ; i < 3; i++){
            book = new Book();
            book.setBookName(bookList.get(0).getBookName());
            book.setBookPrice(bookList.get(0).getBookPrice());
            book.setBookType(bookList.get(0).getBookType());
            book.setBookStock(bookList.get(0).getBookStock());
            books.add(book);
        }
        return books;
    }
    private HeadController headController = new HeadController();

    public void initialize(URL location, ResourceBundle resource){
        try {
            FXMLLoader fxmlLoaderHead = new FXMLLoader();
            fxmlLoaderHead.setLocation(getClass().getResource(headController.pagesHeader()));
//            AnchorPane anchorPaneHead = fxmlLoaderHead.load();
            gridHead.add(fxmlLoaderHead.load(),0,0);
        }catch(IOException e){
            e.printStackTrace();
        }

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
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_COMPUTED_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_COMPUTED_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));

            }
        }catch(IOException e){
                e.printStackTrace();
            }
    }


}
