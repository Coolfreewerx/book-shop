package ku.cs.shop.controllers.system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.shop.models.Account;
import ku.cs.shop.models.AccountList;
import ku.cs.shop.models.Book;
import ku.cs.shop.services.AccountDataSource;

public class ItemCommentController {
    @FXML private ComboBox<String> choice;
    @FXML private Label commentLabel;
    @FXML private ImageView userImageView;
    @FXML private Label usernameLabel;

    private Book book;
    private AccountList accountList;
    private Account account;
    private AccountDataSource accountDataSource;
    BookDetailController bookDetailController;

    private ObservableList choiceList = FXCollections.observableArrayList() ;

    @FXML
    public void initialize(){
        accountList = (AccountList) com.github.saacsos.FXRouter.getData() ;
        account = accountList.getCurrentAccount();

        usernameLabel.setText(account.getUserName());
        choice.getItems().addAll(choiceList);
        userImageView.setImage(new Image(account.getImagePath()));
        lodeData();
    }

    private void lodeData() {
        choice.getItems().removeAll(choiceList);
        choiceList.removeAll(choiceList) ;
        String sex = "รายงานความไม่เหมาะสม" ;
        String[] arr = sex.split(",") ;
        int i = 0 ;

        while ( i < 1){
            choiceList.add(arr[i]) ;
            i++ ;
        }
        choice.getItems().addAll(choiceList) ;
    }


}
