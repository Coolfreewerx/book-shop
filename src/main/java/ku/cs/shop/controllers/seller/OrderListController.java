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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import ku.cs.shop.controllers.system.OrderController;
import ku.cs.shop.models.*;
import ku.cs.shop.services.BookDetailDataSource;
import ku.cs.shop.services.OrderDataSource;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderListController implements Initializable {

    @FXML private ScrollPane scoll;
    @FXML private Button allSellerStockButtonn;
    @FXML private Button status;
    @FXML private Label usernameInHead;
    @FXML private ImageView img;
    @FXML private ImageView logoJavaPai;
    @FXML private ImageView userImageView;
    @FXML private Button shippedButton;
    @FXML private Button newOrderButton;
    @FXML private FlowPane flowPaneOrder;

    private OrderDataSource orderData ;
    private OrderList orders ;

    private BookDetailDataSource data = new BookDetailDataSource("csv-data/bookDetail.csv");
    private BookList books = data.readData();

    private ArrayList<Account> accountsList = new ArrayList<>();
    private AccountList accountList ;
    private Account account ;

    public void initialize (URL location, ResourceBundle resource){
        orderData = new OrderDataSource("csv-data/bookOrder.csv");
        orders = orderData.readData();
        accountList = (AccountList) com.github.saacsos.FXRouter.getData() ;
        account = accountList.getCurrentAccount() ;

        userImageView.setImage(new Image(account.getImagePath()));
        pagesHeader();

        ArrayList<Order> bookOrderInShop = orders.getOrderByShop(account.getShopName());
        flowPaneOrder.getChildren().clear();

        try {
            for (int i = 0; i < orders.getCountOrderByShop(account.getShopName()); i++) {
                if (bookOrderInShop.get(i).getTrackingNumber().equals("ยังไม่จัดส่ง")) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/ku/cs/newOrder.fxml"));

                    flowPaneOrder.getChildren().add(fxmlLoader.load()); // child,col,row
                    OrderController orderController = fxmlLoader.getController();
                    orderController.setData(bookOrderInShop.get(i), accountList, orders);
                    orderController.changeData();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void handleShippedButton(ActionEvent actionEvent){
        flowPaneOrder.getChildren().clear();
        ArrayList<Order> bookOrderInShop = orders.getOrderByShop(account.getShopName());
        try {
            for (int i = 0; i < orders.getCountOrderByShop(account.getShopName()); i++) {
                if (! bookOrderInShop.get(i).getTrackingNumber().equals("ยังไม่จัดส่ง")) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/ku/cs/shippedOrder.fxml"));

                    flowPaneOrder.getChildren().add(fxmlLoader.load()); // child,col,row
                    OrderController orderController = fxmlLoader.getController();
                    orderController.setData(bookOrderInShop.get(i), accountList, orders);
                    orderController.changeData();
                }
            }
            pagesHeader();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void handleNewOrderButton(ActionEvent actionEvent){
        flowPaneOrder.getChildren().clear();
        ArrayList<Order> bookOrderInShop = orders.getOrderByShop(account.getShopName());
        try {
            for (int i = 0; i < orders.getCountOrderByShop(account.getShopName()); i++) {
                if (bookOrderInShop.get(i).getTrackingNumber().equals("ยังไม่จัดส่ง")) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/ku/cs/newOrder.fxml"));

                    flowPaneOrder.getChildren().add(fxmlLoader.load()); // child,col,row
                    OrderController orderController = fxmlLoader.getController();
                    orderController.setData(bookOrderInShop.get(i), accountList, orders);
                    orderController.changeData();
                }
            }
            pagesHeader();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleToSellerButton(ActionEvent actionEvent) {
        if (account.getShopName().equals("ยังไม่ได้สมัครเป็นผู้ขาย")) {
            try {
                com.github.saacsos.FXRouter.goTo("sellerHaventApply",accountList);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("ไปที่หน้า sellerHaventApply ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
        else{
            try {
                com.github.saacsos.FXRouter.goTo("sellerStock",accountList);
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
    public void handleToAccountDetailButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("accountDetail", accountList);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า accountDetail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
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

    @FXML
    public void mouseClickedInLogo(MouseEvent event){ // คลิกที่ logo แล้วจะไปหน้า home
        try{
            logoJavaPai.getOnMouseClicked();
            com.github.saacsos.FXRouter.goTo("home" ,accountList);
        } catch (Exception e) {
            e.printStackTrace();
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

    @FXML
    public void handleToOrderPageButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("bookOrderOfUser" ,accountList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleSellerStockButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("sellerStock" ,accountList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}