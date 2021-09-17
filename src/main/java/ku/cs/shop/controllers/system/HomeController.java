package ku.cs.shop.controllers.system;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import ku.cs.shop.controllers.system.ItemController;
import ku.cs.shop.controllers.user.DetailUser;
import ku.cs.shop.models.Book;
import ku.cs.shop.models.User;
import ku.cs.shop.services.BookDetailDataSource;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML private GridPane grid;
    @FXML private Label usernameLabel ;




    private BookDetailDataSource data = new BookDetailDataSource("src/main/java/ku/cs/shop/bookDetail.csv");
    private ArrayList<Book> books = data.readData();

        public void initialize (URL location, ResourceBundle resource){
            int column = 1;
            int row = 1;
            try {
                for (int i = 0; i < 3; i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/ku/cs/item.fxml"));

                    grid.add(fxmlLoader.load(), column++, row); // child,col,row
                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(books.get(i));
                    itemController.changeData();

                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_COMPUTED_SIZE);

                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_COMPUTED_SIZE);
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(0, 10, 10, 10));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


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
            System.err.println("ไปที่หน้าเพจของ  ไม่ได้");
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

            FXRouter.goTo("detailUser");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า detailUser ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
}
