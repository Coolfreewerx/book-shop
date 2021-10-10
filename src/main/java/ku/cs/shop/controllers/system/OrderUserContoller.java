package ku.cs.shop.controllers.system;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import ku.cs.shop.models.*;
import ku.cs.shop.services.OrderDataSource;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderUserContoller implements Initializable {

    @FXML private ImageView logoJavaPai;
    @FXML private Button status;
    @FXML private Label usernameInHead;
    @FXML private ImageView img;
    @FXML private ImageView userImageView;
    @FXML private FlowPane orderListFlowPane;

    private ArrayList<Account> accountsList = new ArrayList<>();
    private String currentUser;
    private AccountList accountList;
    private Account account;
    private Order order = new Order();

    private OrderDataSource orderDataSource = new OrderDataSource("csv-data/bookOrder.csv");
    private OrderList orderList = orderDataSource.readData();

    public void initialize (URL location, ResourceBundle resource) {

        accountList = (AccountList) com.github.saacsos.FXRouter.getData();
        account = accountList.getCurrentAccount();

        currentUser = account.getUserName();
        pagesHeader();

        System.out.println(currentUser);
        order.setCustomerName(currentUser);
        userImageView.setImage(new Image(account.getImagePath()));
        ArrayList<Order> orderByName = orderList.getOrderByCustomerName("coolfreewerx");

        try {
            for (Order order : orderByName) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/orderUserItem.fxml"));

                orderListFlowPane.getChildren().add(fxmlLoader.load());
                OrderUserItemController orderUserItemController = fxmlLoader.getController();
                orderUserItemController.setData(order);
            }
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

    //ปุ่มสำหรับกดไปหน้าหนังสือทั้งหมด
    @FXML
    public void handleAllTypeBookButton(ActionEvent actionEvent) {
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

    // กำหนดและแสดงข้อมูลตรงส่วน head page
    public void pagesHeader() {
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

    // คลิกที่ logo แล้วจะไปหน้า home
    @FXML
    public void mouseClickedInLogo(MouseEvent event){
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
}
