package ku.cs.shop.controllers.system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import ku.cs.shop.models.*;
import ku.cs.shop.services.BookDetailDataSource;
import ku.cs.shop.services.BookLowPriceToMaxPriceComparator;
import ku.cs.shop.services.BookMaxPriceToLowPriceComparator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TypeBookController<MenuItemCartoon, bookTypeLabel> implements Initializable {

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

    private ArrayList<Object> objectForPassing = new ArrayList<>();
    private BookDetailDataSource data = new BookDetailDataSource("csv-data/bookDetail.csv");
    private BookList books = data.readData();


    public void initialize (URL location, ResourceBundle resource){
        System.out.println("Welcome to  Market Book Page");
        accountList = (AccountList) com.github.saacsos.FXRouter.getData();
        account = accountList.getCurrentAccount();
        pagesHeader();

        bookHeadLabel.setText("หนังสือทั้งหมด");
        changeBookType("ประเภททั้งหมด");
        addBookTypeToMenuItem();
    }

    public ArrayList<Object> castDataToObject() {
        objectForPassing.clear();
        objectForPassing.add(books);
        objectForPassing.add(account);
        objectForPassing.add(accountList);

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

    public void addBookTypeToMenuItem() {
        for (String type : books.getBookType()) {
            MenuItem subBookTypeMenuItem = new MenuItem(type);
            bookTypeMenuItem.getItems().add(subBookTypeMenuItem);
            subBookTypeMenuItem.setOnAction(this :: handleSubBookTypeMenuItem);
        }
    }

    public void handleSubBookTypeMenuItem(ActionEvent actionEvent) {
        bookHeadLabel.setText("ประเภทของหนังสือ");
        MenuItem menuItem = (MenuItem) actionEvent.getSource();
        changeBookType(menuItem.getText());
        System.out.println("Click to " + currentType);
    }

    public void changeBookType(String type) {
        currentType = type;
        bookType.setText(currentType);
        bookListFlowPane.getChildren().clear();
        ArrayList<Book> bookByType = books.getBookByType(type);

        try {
            for (Book book : bookByType) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/item.fxml"));

                bookListFlowPane.getChildren().add(fxmlLoader.load()); // child,col,row
                ItemController itemController = fxmlLoader.getController();
                itemController.setData(book);
                itemController.setController(this, "byType");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Object> getObjectForPassing() {
        castDataToObject();
        return objectForPassing;
    }

    public void handleLowPriceToMaxPrice(ActionEvent actionEvent) {
        System.out.println("Sort Low Price To Max Price");
        BookLowPriceToMaxPriceComparator comparator = new BookLowPriceToMaxPriceComparator();
        books.sort(comparator);
        changeBookType(currentType);
    }

    public void handleMaxPriceToLowPrice(ActionEvent actionEvent) {
        System.out.println("Sort Max Price To Low Price");
        BookMaxPriceToLowPriceComparator comparator = new BookMaxPriceToLowPriceComparator();
        books.sort(comparator);
        changeBookType(currentType);
    }

    public void handlePageAllTypeBookButton(ActionEvent actionEvent) {
        System.out.println("Click to " + currentType);
        bookHeadLabel.setText("หนังสือทั้งหมด");
        books.sort();
        changeBookType("ประเภททั้งหมด");
    }

    @FXML
    public void handleToInformationButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าข้อมูลส่วนตัว
        try {
            com.github.saacsos.FXRouter.goTo("accountDetail", accountList);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า accountDetail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
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
}
