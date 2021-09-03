package ku.cs.shop.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DetailUser implements Initializable {
    @FXML
    private HBox hBoxSellerStock;

    @FXML
    private GridPane gridHead;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label firstnameLabel;

    @FXML
    private Label lastnameLAbel;

    @FXML
    private Label birthdayLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label sexLabel;

    public void initialize(URL location, ResourceBundle resource) {
        try {
            FXMLLoader fxmlLoaderHead = new FXMLLoader();
            fxmlLoaderHead.setLocation(getClass().getResource("/ku/cs/headNoLogin.fxml"));
//            AnchorPane anchorPaneHead = fxmlLoaderHead.load();
            gridHead.add(fxmlLoaderHead.load(), 0, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
