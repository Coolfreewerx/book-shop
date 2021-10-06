package ku.cs.shop.controllers.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import ku.cs.shop.models.Account;
import ku.cs.shop.models.AccountList;
import ku.cs.shop.models.UserAccount;

import java.io.IOException;

public class ProvideTypeBookController {
    private AccountList accountList ;
    private Account account ;
    private UserAccount selectedAccount ;

    public void initialize(){
        accountList = (AccountList) com.github.saacsos.FXRouter.getData() ;
        account = accountList.getCurrentAccount() ;
    }


    @FXML private ImageView logoJavaPai;
    @FXML private Button provideUserButton;
    @FXML private Button provideShopButton;
    @FXML private Label topicLabel;
    @FXML private Label mainTopicLabel1;
    @FXML private Label mainTopicLabel;
    @FXML private Label topicLabel1;
    @FXML private TextField newBooktypeTextField;
    @FXML private Button checkBookButton;
    @FXML private Label notificationCheckTypeBookLabel;
    @FXML private Label topicLabel11;
    @FXML private Button addSubTypeBookButton;

    @FXML
    public void mouseClickedInLogo(MouseEvent event){
        try{
            logoJavaPai.getOnMouseClicked();
            com.github.saacsos.FXRouter.goTo("home" ,accountList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleToProvideShopButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("forAdmin" ,accountList);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า provideTypeBookByAdmin ไม่ได้");
            e.printStackTrace();
        }
    }

}
