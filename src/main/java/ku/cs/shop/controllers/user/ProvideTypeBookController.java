package ku.cs.shop.controllers.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import ku.cs.shop.controllers.system.StockController;
import ku.cs.shop.models.*;
import ku.cs.shop.services.BookDetailDataSource;

import java.io.IOException;
import java.util.ArrayList;

public class ProvideTypeBookController {
    private AccountList accountList ;
    private Account account ;
    private UserAccount selectedAccount ;
    @FXML private GridPane gridPaneForSubTypeBook;

    private BookDetailDataSource data = new BookDetailDataSource("csv-data/bookDetail.csv");
    private BookList books = data.readData();

    public void initialize(){
        accountList = (AccountList) com.github.saacsos.FXRouter.getData() ;
        account = accountList.getCurrentAccount() ;

        int column = 0;
        int row = 1;

        ArrayList<Book> bookInShop = books.getBookByShop(account.getShopName());

        try {
            for (int i = 0; i < 3 ; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/stock.fxml"));

                gridPaneForSubTypeBook.add(fxmlLoader.load(), column, row++); // child,col,row
                StockController stockController = fxmlLoader.getController();
                stockController.setData(bookInShop.get(i),accountList,books);
                stockController.changeData();

                gridPaneForSubTypeBook.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridPaneForSubTypeBook.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridPaneForSubTypeBook.setMaxWidth(Region.USE_COMPUTED_SIZE);

                gridPaneForSubTypeBook.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridPaneForSubTypeBook.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridPaneForSubTypeBook.setMaxHeight(Region.USE_COMPUTED_SIZE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
