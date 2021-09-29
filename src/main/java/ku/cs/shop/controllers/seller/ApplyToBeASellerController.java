package ku.cs.shop.controllers.seller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import ku.cs.shop.models.Account;
import ku.cs.shop.models.AccountList;
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
    @FXML private Label usernameLabel;
    @FXML private Button submitButton;
    @FXML private TextField nameShopTextField;
    @FXML private PasswordField passwordTextField1;
    @FXML private PasswordField passwordTextField2;
    @FXML private Label notificationShopName;
    @FXML private Label notificationPassword1;
    @FXML private Label notificationPassword2;
    @FXML private GridPane gridpane;
    @FXML private GridPane gridPaneInHead;

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
        showHead();
    }

    @FXML
    public void handleKeyCheckShopName(){
        if(accountList.checkShopNameHaveUsed(nameShopTextField.getText())) {
            notificationShopName.setText("** ชื่อร้านค้านี้ถูกใช้ไปแล้ว กรุณากรอกใหม่อีกครั้ง **") ;
            shopName = "";
        } else {
            notificationShopName.setText("** ชื่อร้านนี้ค้าสามารถใช้ได้ **") ;
            shopName = nameShopTextField.getText();
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

//        DataSource<BookList> dataSource;
//        dataSource = new BookDetailDataSource("src/main/java/ku/cs/shop/bookDetail.csv");
//        BookList bookList = dataSource.readData();
//        bookList.addBook(book);
//        dataSource.writeData(bookList);

        if(shopName != "") {
            DataSource<AccountList> dataSource;
            dataSource = new AccountDataSource("csv-data/accountData.csv");
            AccountList accountList = dataSource.readData();
            accountList.editInformationByName(account.getUserName(), account);
            dataSource.writeData(accountList);
            try {
                com.github.saacsos.FXRouter.goTo("applyBook");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า applyBook ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
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
    public void showHead(){ //แสดงหัวเพจ
        try{
            FXMLLoader fxmlLoaderHead = new FXMLLoader();
            fxmlLoaderHead.setLocation(getClass().getResource("/ku/cs/headPage.fxml"));
            gridpane.add(fxmlLoaderHead.load(), 0,0);
            gridPaneInHead.add(fxmlLoaderHead.load(), 0,0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleToAccountDetailButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้า home
        try {
            com.github.saacsos.FXRouter.goTo("accountDetail" ,accountList);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า accountDetail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleToSellerButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้า home
        if (true) {
            try {
                com.github.saacsos.FXRouter.goTo("sellerHaventApply",accountList);
            } catch (IOException e) {
                System.err.println("ไปที่หน้า sellerHaventApply ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
    }

    public void handleApplyToBeSellerButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("applyToBeASeller" ,accountList);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า applyToBeASeller ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
