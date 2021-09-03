package ku.cs.shop.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import ku.cs.shop.models.User;

public class HeadController {
    private User user;
    private Pane view;
    @FXML private HBox headVerNoLogin;
    @FXML private HBox headVerLogin;
    @FXML private Label userNameLabel;
    @FXML private AnchorPane paneVerLogin;
    @FXML private AnchorPane paneVerNoLogin;

//    @FXML
//    public void initialize() {
//        System.out.println("initialize HeadController");
//        user = (User) com.github.saacsos.FXRouter.getData();
//        showUsername();
//    }

    public AnchorPane pagesHeader() {
        try{
            if (user.login(user.getUserName(), user.getPassword()) == true) {
                FXMLLoader fxmlLoaderHead = new FXMLLoader();
                fxmlLoaderHead.setLocation(getClass().getResource("/ku/cs/headWhenLogin.fxml"));
                paneVerLogin = fxmlLoaderHead.load();
                System.out.println(pagesHeader());
                return paneVerLogin;
            }
            else{
                FXMLLoader fxmlLoaderHead = new FXMLLoader();
                fxmlLoaderHead.setLocation(getClass().getResource("/ku/cs/headNoLogin.fxml"));
                paneVerNoLogin = fxmlLoaderHead.load();
                System.out.println(pagesHeader());
                return paneVerNoLogin;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AnchorPane();
    }

    private void showUsername() {
        userNameLabel.setText(user.getUserName());
    }

//    public void initialize(URL location, ResourceBundle resource) {
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("/ku/cs/head.fxml"));
//    }
//
//    public FXMLLoader getFxmlLoader() {
//        return fxmlLoader;
//    }
}
