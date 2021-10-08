package ku.cs.shop.controllers.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
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

    int column = 0;
    int row = 2;
    int num = 1;

    @FXML
    public void handleAddSubTypeBookButton(ActionEvent actionEvent) {
        Label subTypeLabel = new Label("ระบุประเภทย่อย " + num++);
        gridPaneForSubTypeBook.setAlignment(Pos.TOP_LEFT);
        gridPaneForSubTypeBook.add(subTypeLabel,column,row++);
        subTypeLabel.setPadding(new Insets(20,0,0,80));
        subTypeLabel.setFont(Font.font("JasmineUPC",32));

        TextField subTypeTextField = new TextField();
        gridPaneForSubTypeBook.add(subTypeTextField,column,row++);
        subTypeTextField.setPadding(new Insets(50,0,0,120));
//        subTypeTextField.setPrefHeight(50);
//        subTypeTextField.setPrefWidth(620);
//        subTypeTextField.setMinHeight(Region.USE_COMPUTED_SIZE);
//        subTypeTextField.setMaxHeight(Region.USE_COMPUTED_SIZE);
//        subTypeTextField.setMinWidth(Region.USE_COMPUTED_SIZE);
//        subTypeTextField.setMaxWidth(Region.USE_COMPUTED_SIZE);

        Button subTypeButton = new Button("+");
        gridPaneForSubTypeBook.add(subTypeButton,column,row++);
        subTypeButton.setPadding(new Insets(20,0,0,80));
        subTypeButton.setFont(Font.font(32));
        subTypeButton.setFont(Font.font("JasmineUPC"));
        subTypeButton.setFont(Font.font("Bold"));






//        try {
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("/ku/cs/choiceProvideTypeBook.fxml"));
//
//                gridPaneForSubTypeBook.add(fxmlLoader.load(), column, row++); // child,col,row
//                ChoiceProvideTypeBookController choiceProvideTypeBookController = fxmlLoader.getController();
//
//
//                gridPaneForSubTypeBook.setMinWidth(Region.USE_COMPUTED_SIZE);
//                gridPaneForSubTypeBook.setPrefWidth(Region.USE_COMPUTED_SIZE);
//                gridPaneForSubTypeBook.setMaxWidth(Region.USE_COMPUTED_SIZE);
//
//                gridPaneForSubTypeBook.setMinHeight(Region.USE_COMPUTED_SIZE);
//                gridPaneForSubTypeBook.setPrefHeight(Region.USE_COMPUTED_SIZE);
//                gridPaneForSubTypeBook.setMaxHeight(Region.USE_COMPUTED_SIZE);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
