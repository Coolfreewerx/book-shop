package ku.cs.shop.controllers.system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.shop.models.*;
import ku.cs.shop.services.AccountDataSource;

import java.util.ArrayList;

public class ItemCommentController {
    @FXML private ComboBox<String> choice;
    @FXML private Label commentLabel;
    @FXML private ImageView userImageView;
    @FXML private Label usernameLabel;

    private Book book;
    private AccountList accountList;
    private Account account;
    private AccountDataSource accountDataSource;
    private Review reviews;
    private BookList bookList;
    BookDetailController bookDetailController;

    private ObservableList choiceList = FXCollections.observableArrayList();
    private ArrayList<Object> objectForPassing = new ArrayList<>();

    @FXML
    public void initialize()
    {
        castObjectToData();
        objectForPassing = (ArrayList<Object>) com.github.saacsos.FXRouter.getData();
    }

    private void lodeData() {
        choice.getItems().removeAll(choiceList);
        choiceList.removeAll(choiceList);
        String sex = "รายงานความไม่เหมาะสม";
        String[] arr = sex.split(",");
        int i = 0;

        while ( i < 1){
            choiceList.add(arr[i]);
            i++;
        }
        choice.getItems().addAll(choiceList);
    }

    public void setData(Review reviews) {
        this.reviews = reviews;
        changeData();
    }

    public void changeData() {
        usernameLabel.setText(account.getUserName());
        choice.getItems().addAll(choiceList);
        userImageView.setImage(new Image(account.getImagePath()));
        commentLabel.setText(reviews.getComment());
        lodeData();
    }

    public void castObjectToData() {
        book = (Book) objectForPassing.get(3);
        bookList = (BookList) objectForPassing.get(0);
        account = (Account) objectForPassing.get(1);
        accountList = (AccountList) objectForPassing.get(2);
        System.out.println(book.getBookName());
        System.out.println(account.getUserName());
    }
}
