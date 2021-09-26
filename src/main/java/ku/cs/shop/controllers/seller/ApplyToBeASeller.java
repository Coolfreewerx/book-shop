package ku.cs.shop.controllers.seller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.shop.models.BookList;
import ku.cs.shop.models.User;
import ku.cs.shop.models.UserList;
import ku.cs.shop.services.BookDetailDataSource;
import ku.cs.shop.services.DataSource;
import ku.cs.shop.services.UserDataSource;

import java.io.IOException;

public class ApplyToBeASeller{ //สมัครเป็นผู้ขายสินค้า
    private User user = new User("Freshmin", "Na", "justmeka", "13082000",
         "15", "11", "2001", "Temporary", "0823341025", "Women", "");
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


    @FXML
    public void handleKeyCheckShopName(){
        notificationShopName.setText(user.checkShopNameCondition(nameShopTextField.getText()));
    }
    @FXML
    public void handleKeyPassword() {
        notificationPassword1.setText(user.comparePassword(passwordTextField1.getText(),user.getPassword()));
    }
    @FXML
    public void handleKeyCheckPassword() {
        notificationPassword2.setText(user.comparePassword(passwordTextField1.getText(),passwordTextField2.getText()));
    }

    @FXML
    public void handleAddSellerStockButton(){
        shopName = nameShopTextField.getText();
        password = passwordTextField1.getText();
        passwordRecheck = passwordTextField1.getText();
        user.setShopName(shopName);

//        DataSource<BookList> dataSource;
//        dataSource = new BookDetailDataSource("src/main/java/ku/cs/shop/bookDetail.csv");
//        BookList bookList = dataSource.readData();
//        bookList.addBook(book);
//        dataSource.writeData(bookList);

        DataSource<UserList> dataSource;
        dataSource = new UserDataSource("csv-data/userData.csv");
        UserList userList = dataSource.readData();
        userList.addUser(user);
        dataSource.writeData(userList);

        try {
            com.github.saacsos.FXRouter.goTo("seller");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า seller ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
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

}
