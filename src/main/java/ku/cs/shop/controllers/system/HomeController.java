package ku.cs.shop.controllers.system;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import ku.cs.shop.models.*;
import ku.cs.shop.services.BookDetailDataSource;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML private FlowPane bookListFlowPane;
    @FXML private Button status;
    @FXML private Label usernameInHead;
    @FXML private ImageView img;
    @FXML private ImageView logoJavaPai;

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
                itemController.setController(this,"default");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } pagesHeader();
    }

    public void castDataToObject() {
        objectForPassing.clear();
        objectForPassing.add(books);
        objectForPassing.add(account);
        objectForPassing.add(accountList);
    }

    public ArrayList<Object> getObjectForPassing() {
        castDataToObject();
        return objectForPassing;
    }

    public void pagesHeader() { // กำหนดและแสดงข้อมูลตรงส่วน head page
        usernameInHead.setText(account.getUserName());
        img.setImage(new Image(account.getImagePath()));
        if(account instanceof AdminAccount){
            status.setText("Admin");
        }else if(account.getShopName().equals("ยังไม่ได้สมัครเป็นผู้ขาย")){
            status.setText("User");
        }else {
            status.setText("Seller");
        }
    }

    @FXML
    public void handleAllTypeBookButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าหนังสือทั้งหมด
        try {
            FXRouter.goTo("pageBookType", accountList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleToAccountDetailButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("accountDetail", accountList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void mouseClickedInLogo(MouseEvent event){ // คลิกที่ logo แล้วจะไปหน้า home
        try {
            logoJavaPai.getOnMouseClicked();
            com.github.saacsos.FXRouter.goTo("home" ,accountList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
