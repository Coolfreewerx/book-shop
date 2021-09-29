package ku.cs.shop.controllers.system;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.*;
import ku.cs.shop.models.*;
import ku.cs.shop.services.BookDetailDataSource;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML private FlowPane bookListFlowPane;

    private BookDetailDataSource data = new BookDetailDataSource("csv-data/bookDetail.csv");
    private BookList books = data.readData();
    private int bookAllType = books.getBookListCount();

    private ArrayList<Object> objectForPassing = new ArrayList<>();
    private AccountList accountList ;
    private Account account ;

    public void initialize (URL location, ResourceBundle resource){
        accountList = (AccountList) com.github.saacsos.FXRouter.getData();
        account = accountList.getCurrentAccount();
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
    }

    @FXML
    public void handleAllTypeBookButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้า หนังสือทั้งหมด (เพจหลัก)
        try {
            FXRouter.goTo("pageBookType", accountList);
        } catch (IOException e) {
            System.err.println("ไปที่หน้าเพจหลักไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }


    @FXML
    public void handleToInformationButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("accountDetail", accountList);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า detailUser ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
}
