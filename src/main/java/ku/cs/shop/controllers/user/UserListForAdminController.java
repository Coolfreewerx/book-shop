package ku.cs.shop.controllers.user;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import ku.cs.shop.models.Account;
import ku.cs.shop.models.AccountList;
import ku.cs.shop.models.UserAccount;
import ku.cs.shop.services.AccountDataSource;
import ku.cs.shop.services.AccountSortByTimeComparator;

import java.io.File;
import java.util.ArrayList;

public class UserListForAdminController {

    @FXML private GridPane gridPaneInHead ;
    @FXML private ImageView accountImage ;
    @FXML private Label userNameLabel ;
    @FXML private Label firstNameLabel ;
    @FXML private Label lastNameLabel ;
    @FXML private Label birthDayLabel ;
    @FXML private Label birthMonthLabel ;
    @FXML private Label birthYearLabel ;
    @FXML private Label sexLabel ;
    @FXML private Label phoneLabel ;
    @FXML private Label shopNameLabel ;
    @FXML private Label addressLabel ;
    @FXML private Label loginTimeLabel ;
    @FXML private Label accountStatusLabel ;
    @FXML private ListView<UserAccount> userAccountListView ;
    //@FXML private ListView reportUserListView ;

    private AccountList accountList ;
    private Account account ;

    public void initialize(){
        accountList = (AccountList) com.github.saacsos.FXRouter.getData() ;
        account = accountList.getCurrentAccount() ;
        showHead();
        showUserAccountListView();
        clearSelectedUserAccount();
        handleSelectedUserAccountListView();
    }

    private void showUserAccountListView() {
        userAccountListView.getItems().addAll(accountList.getUserAccounts());
        userAccountListView.refresh();
    }

    private void handleSelectedUserAccountListView() {
        userAccountListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<UserAccount>() {
                    @Override
                    public void changed(ObservableValue<? extends UserAccount> observable,
                                        UserAccount oldValue, UserAccount newValue) {
                        showSelectedUserAccount(newValue);
                    }
                });
    }

    private void showSelectedUserAccount(UserAccount userAccount) {
        accountImage.setImage(new Image(userAccount.getImagePath()));
        userNameLabel.setText(userAccount.getUserName());
        firstNameLabel.setText(userAccount.getFirstName());
        lastNameLabel.setText(userAccount.getLastName());
        birthDayLabel.setText(userAccount.getBirthDay());
        birthMonthLabel.setText(userAccount.getBirthMonth());
        birthYearLabel.setText(userAccount.getBirthYear());
        sexLabel.setText(userAccount.getSex().replace("null", "ยังไม่ได้เพิ่มข้อมูลเพศ"));
        phoneLabel.setText(userAccount.getPhone().replace("null", "ยังไม่ได้เพิ่มข้อมูลเบอร์โทร"));
        shopNameLabel.setText(userAccount.getShopName());
        addressLabel.setText(userAccount.getAddress().replace("null", "ยังไม่ได้เพิ่มข้อมูลที่อยู่"));
        loginTimeLabel.setText(userAccount.getLoginTime().toString());
        accountStatusLabel.setText(userAccount.getStatus());
    }

    private void clearSelectedUserAccount() {
        accountImage.setImage(new Image(new File(System.getProperty("user.dir")
                + File.separator
                + "account-images"
                + File.separator
                + "default.png").toURI().toString()));
        userNameLabel.setText("ชื่อผู้ใช้งาน : ยังไม่ระบุ");
        firstNameLabel.setText("ชื่อ : ยังไม่ระบุ");
        lastNameLabel.setText("นามสกุล : ยังไม่ระบุ");
        birthDayLabel.setText("ยังไม่ระบุ");
        birthMonthLabel.setText("");
        birthYearLabel.setText("");
        sexLabel.setText("ยังไม่ระบุ");
        phoneLabel.setText("ยังไม่ระบุ");
        shopNameLabel.setText("ยังไม่ระบุ");
        addressLabel.setText("ยังไม่ระบุ");
        loginTimeLabel.setText("ยังไม่ระบุ");
        accountStatusLabel.setText("ยังไม่ระบุ");
    }

    @FXML
    public void showHead(){ //แสดงหัวเพจ
        try{
            FXMLLoader fxmlLoaderHead = new FXMLLoader();
            fxmlLoaderHead.setLocation(getClass().getResource("/ku/cs/headPage.fxml"));
            gridPaneInHead.add(fxmlLoaderHead.load(), 0,0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
