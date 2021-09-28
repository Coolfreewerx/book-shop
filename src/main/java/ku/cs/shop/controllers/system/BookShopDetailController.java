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
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import ku.cs.shop.models.Account;
import ku.cs.shop.models.AccountList;
import ku.cs.shop.models.Book;
import ku.cs.shop.models.BookList;
import ku.cs.shop.services.BookDetailDataSource;
import ku.cs.shop.services.BookLowPriceToMaxPriceComparator;
import ku.cs.shop.services.BookMaxPriceToLowPriceComparator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookShopDetailController<MenuItemCartoon, bookTypeLabel> implements Initializable {

    @FXML private Label bookType;
    @FXML private FlowPane bookListFlowPane;
    @FXML private MenuButton bookTypeMenuItem;
    @FXML private Text bookHeadLabel;
    @FXML private Button status;
    @FXML private Label usernameInHead;
    @FXML private ImageView img;

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

        objectForPassing = (ArrayList<Object>) com.github.saacsos.FXRouter.getData();
//        pagesHeader();

        castObjectToData();

        bookHeadLabel.setText(book.getBookShop() + "");
        changeBookType("ประเภททั้งหมด");
        changeBookByShop(book.getBookShop());
        addBookTypeToMenuItem();
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

    public void pagesHeader() { // กำหนดข้อมูลตรงส่วน head page
        usernameInHead.setText(account.getUserName());
        img.setImage(new Image(account.getImagePath()));
        if(account.getShopName().equals("ยังไม่ได้สมัครเป็นผู้ขาย")){
            status.setText("User");
        }else{
            status.setText("Seller");
        }
    }

    public ArrayList<Object> getObjectForPassing() {
        castDataToObject();
        return objectForPassing;
    }

    public void addBookTypeToMenuItem() {
        for (String type : books.getBookType()) {
            MenuItem subBookTypeMenuItem = new MenuItem(type);
            bookTypeMenuItem.getItems().add(subBookTypeMenuItem);
            subBookTypeMenuItem.setOnAction(this :: handleSubBookTypeMenuItem);
        }
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
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handlePageAllTypeBookButton(ActionEvent actionEvent) {
        System.out.println("Click to " + currentType);
        bookHeadLabel.setText("หนังสือทั้งหมด");
        books.sort();
        changeBookType("ประเภททั้งหมด");
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

    @FXML
    public void handleToInformationButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้า home
        try {
            com.github.saacsos.FXRouter.goTo("accountDetail", accountList);
        } catch (IOException e) {
            System.err.println("ไปที่หน้าเพจหลักไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
