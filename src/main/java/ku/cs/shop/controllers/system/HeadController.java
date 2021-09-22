package ku.cs.shop.controllers.system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import ku.cs.shop.models.User;
import com.github.saacsos.FXRouter;
import java.io.IOException;

public class HeadController {
    private User user;
    private Pane view;
    @FXML private HBox hBoxHeadPage;
    @FXML private Button status;
    @FXML private Label usernameInHead;
    @FXML private ImageView img;

    @FXML
    void handleAllTypeBookButton(ActionEvent event) {

    }
    @FXML
    public void initialize() {
        System.out.println("initialize HeadController");
        user = (User)FXRouter.getData();
    }

    public String pagesHeader() {
        String pane = "/ku/cs/headPage.fxml";
        return pane;
    }

    private void showDataInHead() {
        pagesHeader();
        usernameInHead.setText(user.getUserName());
        img.setImage(new Image(user.getImagePath()));
        if (user.getShopName() != " "){
            status.setText("Seller");
        }
        if (user.getShopName() == " "){
            status.setText("User");
        }
//        status.setText();
    }

    @FXML
    public void handleToHomeButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้า home
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
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
