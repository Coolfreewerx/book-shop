package ku.cs.shop.controllers.user;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import ku.cs.shop.models.Account;
import ku.cs.shop.models.AccountList;
import ku.cs.shop.models.UserAccount;
import ku.cs.shop.services.AccountDataSource;

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

//    private AccountList accountList ;
//    private Account account ;
//
//    public void initialize(){
//        accountList = (AccountList) com.github.saacsos.FXRouter.getData() ;
//        account = accountList.getCurrentAccount() ;
//        showHead();
//        showUserAccountListView();
//        clearSelectedUserAccount();
//        handleSelectedUserAccountListView();
//    }
//
////    private void showUserAccountListView() {
////        userAccountListView.getItems().addAll(accountList.());
////        userAccountListView.refresh();
////    }
//
//    private void handleSelectedUserAccountListView() {
//        userAccountListView.getSelectionModel().selectedItemProperty().addListener(
//                new ChangeListener<UserAccount>() {
//                    @Override
//                    public void changed(ObservableValue<? extends UserAccount> observable,
//                                        UserAccount oldValue, UserAccount newValue) {
//                        System.out.println(newValue + " is selected");
//                        showSelectedUserAccount(newValue);
//                    }
//                });
//    }
//
//    private void showSelectedUserAccount(UserAccount userAccount) {
//        userNameLabel.setText(userAccount.getUserName());
//        firstNameLabel.setText(userAccount.getFirstName());
//        lastNameLabel.setText(userAccount.getLastName());
//        birthDayLabel.setText(userAccount.getBirthDay());
//        birthMonthLabel.setText(userAccount.getBirthMonth());
//        birthYearLabel.setText(userAccount.getBirthYear());
//        sexLabel.setText(userAccount.getSex());
//        phoneLabel.setText(userAccount.getPhone());
//        shopNameLabel.setText(userAccount.getShopName());
//        addressLabel.setText(userAccount.getAddress());
//        loginTimeLabel.setText(userAccount.getLoginTime().toString());
//        accountStatusLabel.setText(userAccount.getStatus());
//    }
//
//    private void clearSelectedUserAccount() {
//        userNameLabel.setText("");
//        firstNameLabel.setText("");
//        lastNameLabel.setText("");
//        birthDayLabel.setText("");
//        birthMonthLabel.setText("");
//        birthYearLabel.setText("");
//        sexLabel.setText("");
//        phoneLabel.setText("");
//        shopNameLabel.setText("");
//        addressLabel.setText("");
//        loginTimeLabel.setText("");
//        accountStatusLabel.setText("");
//    }
//
//    @FXML
//    public void showHead(){ //แสดงหัวเพจ
//        try{
//            FXMLLoader fxmlLoaderHead = new FXMLLoader();
//            fxmlLoaderHead.setLocation(getClass().getResource("/ku/cs/headPage.fxml"));
//            gridPaneInHead.add(fxmlLoaderHead.load(), 0,0);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
