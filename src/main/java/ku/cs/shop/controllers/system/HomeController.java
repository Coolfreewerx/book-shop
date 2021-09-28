package ku.cs.shop.controllers.system;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import ku.cs.shop.controllers.system.ItemController;
import ku.cs.shop.controllers.user.DetailUser;
import ku.cs.shop.models.*;
import ku.cs.shop.services.BookDetailDataSource;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML private GridPane grid;
    @FXML private GridPane gridPaneInHead;
    @FXML private HBox head;
    @FXML private FlowPane bookListFlowPane;
    @FXML private MenuButton bookTypeMenuItem;
    private HeadController headController;
    private AccountList accountList ;


    private BookDetailDataSource data = new BookDetailDataSource("csv-data/bookDetail.csv");
    private BookList books = data.readData();
    private int bookAllType = books.getBookListCount();

        public void initialize (URL location, ResourceBundle resource){
            accountList = (AccountList) FXRouter.getData() ;
            data.writeData(books);
            addItemToProgram();
        }
            public void addItemToProgram() {
                try {
                    for (int i = 0 ; i < bookAllType; i++) {
                        if (i > 3) {
                            break;
                        }
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/ku/cs/item.fxml"));

                        bookListFlowPane.getChildren().add(fxmlLoader.load()); // child,col,row
                        ItemController itemController = fxmlLoader.getController();
                        itemController.setData(books.getBook(i));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                showHead();
        }

//    public void addBookTypeToMenuItem() {
//        for (String type : books.getBookType()) {
//            MenuItem subBookTypeMenuItem = new MenuItem(type);
//            bookTypeMenuItem.getItems().add(subBookTypeMenuItem);
//            subBookTypeMenuItem.setOnAction(this :: handleSubBookTypeMenuItem);
//        }
//    }
//    public void handleSubBookTypeMenuItem(ActionEvent actionEvent) {
//        MenuItem menuItem = (MenuItem) actionEvent.getSource();
//    }



    @FXML
    public void handleLinkToBasketBookButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าตะกร้าสินค้า
        try {
            FXRouter.goTo("basket");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า basket ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleTypeBookButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าหนังสือการ์ตูน
        try {
            FXRouter.goTo("pageBookType");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าเพจ pageBookType ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleAllTypeBookButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้า หนังสือทั้งหมด (เพจหลัก)
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าเพจหลักไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleToInformationButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้า หนังสือทั้งหมด (เพจหลัก)
        try {
            FXRouter.goTo("accountDetail", accountList);
//            FXRouter.goTo("detailUser", accountList);

        } catch (IOException e) {
            System.err.println("ไปที่หน้า detailUser ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void showHead(){ //แสดงหัวเพจ
            try{
                FXMLLoader fxmlLoaderHead = new FXMLLoader();
                fxmlLoaderHead.setLocation(getClass().getResource("/ku/cs/headPage.fxml"));
                gridPaneInHead.add(fxmlLoaderHead.load(), 0,0);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
