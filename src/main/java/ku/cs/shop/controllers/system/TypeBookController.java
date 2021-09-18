package ku.cs.shop.controllers.system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import ku.cs.shop.models.Book;
import ku.cs.shop.services.BookDetailDataSource;

import java.io.IOException;
import java.net.URL;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TypeBookController<MenuItemCartoon, bookTypeLabel> implements Initializable {

//    @FXML private Label bookTypeLabel;
    @FXML private GridPane gridHead;
    @FXML private GridPane gridBook;

    private BookDetailDataSource data = new BookDetailDataSource("src/main/java/ku/cs/shop/bookDetail.csv");
    private ArrayList<Book> books = data.readData();

    public void initialize (URL location, ResourceBundle resource){
        int column = 1;
        int row = 1;
        try {
            for (int i = 0; i < 3; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/item.fxml"));

                gridBook.add(fxmlLoader.load(), column, row++); // child,col,row
                ItemController itemController = fxmlLoader.getController();
//                if(books.getBookType() == "Cartoon Book") {
                itemController.setData(books.get(i));
                itemController.changeData();

                gridBook.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridBook.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridBook.setMaxWidth(Region.USE_COMPUTED_SIZE);

                gridBook.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridBook.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridBook.setMaxHeight(Region.USE_COMPUTED_SIZE);
                gridBook.setHgap(10);
                gridBook.setVgap(10);
                gridBook.setPadding(new Insets(0, 10, 10, 10));

                }
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
=======


//    Text bookTypeLabel = new Text;
//    bookTypeLabel.setText("Cartoon Book");


>>>>>>> 57b7276c23ad965ae8ce5a44e878f6de6887811f
    public void handleTypeBookButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("pageBookType");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า basket ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleAllTypeBookButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("pageBookType");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า basket ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleToHomeButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้า home
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าเพจหลักไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
