package ku.cs.shop.controllers.seller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import ku.cs.shop.controllers.system.StockController;
import ku.cs.shop.models.*;

import ku.cs.shop.services.BookDetailDataSource;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SellerStockController implements Initializable {
    @FXML private HBox hBoxSellerStock;
    @FXML private Label userNameLabel;
    @FXML private ScrollPane scoll;
    @FXML private GridPane grid;
    @FXML private Button orderListButtonn;
    @FXML private Button addStockButton;
    @FXML private Button status;
    @FXML private Label usernameInHead;
    @FXML private ImageView img;
    @FXML private ImageView logoJavaPai;
    @FXML private ImageView userImageView;

    private BookDetailDataSource data = new BookDetailDataSource("csv-data/bookDetail.csv");
    private BookList books = data.readData();
    private ArrayList<Account> accountsList = new ArrayList<>();
    private AccountList accountList ;
    private Account account ;

    public void initialize (URL location, ResourceBundle resource){
        accountList = (AccountList) com.github.saacsos.FXRouter.getData() ;
        account = accountList.getCurrentAccount() ;
        userImageView.setImage(new Image(account.getImagePath()));

        pagesHeader();
        int column = 0;
        int row = 1;

        ArrayList<Book> bookInShop = books.getBookByShop(account.getShopName());

        try {
            for (int i = 0; i < books.getCountBookByShop(account.getShopName()) ; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/stock.fxml"));

                grid.add(fxmlLoader.load(), column, row++); // child,col,row
                StockController stockController = fxmlLoader.getController();
                stockController.setData(bookInShop.get(i),accountList,books);
                stockController.changeData();

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_COMPUTED_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_COMPUTED_SIZE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAddStockButton(){
        try {
            com.github.saacsos.FXRouter.goTo("applyBook",accountList);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า applyBook ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleOrderListButton(){
        try {
            com.github.saacsos.FXRouter.goTo("orderList",accountList);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า orderList ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleToAccountDetailButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("accountDetail" ,accountList);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า accountDetail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleToSellerButton(ActionEvent actionEvent) {
        if (true) {
            try {
                com.github.saacsos.FXRouter.goTo("sellerHaventApply",accountList);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("ไปที่หน้า sellerHaventApply ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
    }

    @FXML
    public void handleAllTypeBookButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าหนังสือทั้งหมด
        try {
            com.github.saacsos.FXRouter.goTo("pageBookType", accountList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void mouseClickedInLogo(MouseEvent event){ //คลิกที่รูป logo แล้วจะไปหน้า home
        try{
            logoJavaPai.getOnMouseClicked();
            com.github.saacsos.FXRouter.goTo("home" ,accountList);
        } catch (Exception e) {
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

    //ออกจากระบบกลับไปล็อกอิน
    @FXML
    public void handleToLogoutButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
