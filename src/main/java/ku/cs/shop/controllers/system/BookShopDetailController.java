package ku.cs.shop.controllers.system;

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
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import ku.cs.shop.models.*;
import ku.cs.shop.services.BookDetailDataSource;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.github.saacsos.FXRouter;

public class BookShopDetailController<MenuItemCartoon, bookTypeLabel> implements Initializable {

    @FXML private Label bookType;
    @FXML private FlowPane bookListFlowPane;
    @FXML private MenuButton bookTypeMenuItem;
    @FXML private Text bookHeadLabel;
    @FXML private Button status;
    @FXML private Label usernameInHead;
    @FXML private ImageView img;
    @FXML private ImageView logoJavaPai;

    private String currentType;
    private Account account;
    private AccountList accountList;
    private BookList bookList;
    private Book book;

    private ArrayList<Object> objectForPassing = new ArrayList<>();

    private BookDetailDataSource data = new BookDetailDataSource("csv-data/bookDetail.csv");
    private BookList books = data.readData();

    public void initialize (URL location, ResourceBundle resource){
        System.out.println("Welcome to  Seller Book Page");

        objectForPassing = (ArrayList<Object>) FXRouter.getData();
        castObjectToData();

        bookHeadLabel.setText(book.getBookShop() + "");
        bookType.setText("ประเภททั้งหมด");
        changeBookByShop(book.getBookShop());
        pagesHeader();
    }

    public void castObjectToData() {
        book = (Book) objectForPassing.get(0);
        bookList = (BookList) objectForPassing.get(1);
        account = (Account) objectForPassing.get(2);
        accountList = (AccountList) objectForPassing.get(3);
        System.out.println(book.getBookShop());
        System.out.println(account.getUserName());
    }

    public ArrayList<Object> castDataToObject() {
        objectForPassing.clear();
        objectForPassing.add(books);
        objectForPassing.add(account);
        objectForPassing.add(accountList);

        return objectForPassing;
    }

    public void changeBookByShop(String nameShop) {
        nameShop = book.getBookShop();
        bookListFlowPane.getChildren().clear();
        ArrayList<Book> bookByShop = books.getBookByShop(nameShop);

        try {
            for (Book book : bookByShop) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/item.fxml"));

                bookListFlowPane.getChildren().add(fxmlLoader.load()); // child,col,row
                ItemController itemController = fxmlLoader.getController();
                itemController.setData(book);
                itemController.setController(this, "byShop");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public ArrayList<Object> getObjectForPassing() {
        castDataToObject();
        return objectForPassing;
    }

    public void handleAllTypeBookButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("pageBookType", accountList);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า pageBookType ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    @FXML
    public void handleToInformationButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("accountDetail", accountList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void mouseClickedInLogo(MouseEvent event){ // คลิกที่ logo แล้วจะไปหน้า home
        try{
            logoJavaPai.getOnMouseClicked();
            com.github.saacsos.FXRouter.goTo("home" ,accountList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleToPromotionPageButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("pagePromotionByBookShop" ,accountList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
