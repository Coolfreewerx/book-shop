package ku.cs.shop.controllers.seller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import ku.cs.shop.models.Account;
import ku.cs.shop.models.AccountList;
import ku.cs.shop.models.AdminAccount;
import ku.cs.shop.models.UserAccount;
import ku.cs.shop.services.AccountDataSource;
import ku.cs.shop.services.DataSource;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ApplyToBeASellerController { //สมัครเป็นผู้ขายสินค้า
    private String shopName;
    private String password;
    private String passwordRecheck;

    @FXML private Button submitButton;
    @FXML private TextField nameShopTextField;
    @FXML private PasswordField passwordTextField1;
    @FXML private PasswordField passwordTextField2;
    @FXML private Label notificationShopName;
    @FXML private Label notificationPassword1;
    @FXML private Label notificationPassword2;
    @FXML private Button status;
    @FXML private Label usernameInHead;
    @FXML private ImageView img;
    @FXML private ImageView logoJavaPai;
    @FXML private ImageView userImageView;


//    private Account account = new UserAccount ("Freshmin", "Na", "justmeka", "13082000",
//            "15", "11", "2001", "default.png", "0823341025", "Women",
//            "null", "ยังไม่สมัครเป็นผู้ขาย", "working", LocalDateTime.now());
//    (String firstName, String lastName, String userName, String password,
//    String birthDay, String birthMonth, String birthYear,
//    String imageName, String phone, String sex, String address, String shopName,
//    String status, LocalDateTime loginTime )

    private ArrayList<Account> accountsList = new ArrayList<>();
    private AccountList accountList ;
    private Account account ;

    public void initialize(){
        accountList = (AccountList) com.github.saacsos.FXRouter.getData() ;
        account = accountList.getCurrentAccount() ;
        userImageView.setImage(new Image(account.getImagePath()));
        pagesHeader();
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
    public void handleKeyCheckShopName(){
        System.out.println(nameShopTextField.getText());
        if(accountList.checkShopNameHaveUsed(nameShopTextField.getText())) {
            notificationShopName.setText("** ชื่อร้านค้านี้ถูกใช้ไปแล้ว กรุณากรอกใหม่อีกครั้ง **") ;
            shopName = "";
        } else {
            shopName = nameShopTextField.getText();
            notificationShopName.setText("") ;
        }
    }
    @FXML
    public void handleKeyPassword() {
        notificationPassword1.setText(Account.comparePassword(passwordTextField1.getText(),account.getPassword()));
    }
    @FXML
    public void handleKeyCheckPassword() {
        notificationPassword2.setText(Account.comparePassword(passwordTextField1.getText(),passwordTextField2.getText()));
    }

    @FXML
    public void handleAddSellerStockButton(){
        password = passwordTextField1.getText();
        passwordRecheck = passwordTextField1.getText();
        account.setShopName(shopName);

        if(shopName != "") {
            DataSource<AccountList> dataSource;
            dataSource = new AccountDataSource("csv-data/accountData.csv");
            AccountList accountListForWriteData = dataSource.readData();
            accountListForWriteData.editInformationByName(account.getUserName(), account);
            System.out.println(account.getUserName());
            System.out.println(account.getShopName());
            dataSource.writeData(accountListForWriteData);

            try {
                com.github.saacsos.FXRouter.goTo("sellerStock",accountList);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("ไปที่หน้า sellerStock ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
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
    public void handleApplyToBeSellerButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("applyToBeASeller" ,accountList);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า applyToBeASeller ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
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
    public void mouseClickedInLogo(MouseEvent event){ //คลิกที่ logo แล้วจะไปหน้า home
        try{
            logoJavaPai.getOnMouseClicked();
            com.github.saacsos.FXRouter.goTo("home" ,accountList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
