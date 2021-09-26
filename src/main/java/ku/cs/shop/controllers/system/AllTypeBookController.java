package ku.cs.shop.controllers.system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import ku.cs.shop.models.Book;
import ku.cs.shop.models.BookList;
import ku.cs.shop.services.BookDetailDataSource;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AllTypeBookController<books> implements Initializable{
    @FXML private FlowPane bookListFlowPane;
    private BookDetailDataSource data = new BookDetailDataSource("csv-data/bookDetail.csv");
    private BookList books = data.readData();
    private int bookAllType = books.getBookListCount();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addItemToProgram();
    }

    public void addItemToProgram() {
        try {
            for (int i = 0 ; i < bookAllType ; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/item.fxml"));

                bookListFlowPane.getChildren().add(fxmlLoader.load()); // child,col,row
                ItemController itemController = fxmlLoader.getController();
                itemController.setData(books.getBook(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
            com.github.saacsos.FXRouter.goTo("allBookInProgram");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า basket ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleToInformationButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("pageBookType");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า basket ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
