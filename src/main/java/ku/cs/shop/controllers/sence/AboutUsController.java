package ku.cs.shop.controllers.sence;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;

public class AboutUsController {

    @FXML private GridPane gridHeadChoice ;

    public void initialize () {
        try {
            FXMLLoader fxmlLoaderHead = new FXMLLoader();
            fxmlLoaderHead.setLocation(getClass().getResource("/ku/cs/aboutUsChoice.fxml"));
            gridHeadChoice.add(fxmlLoaderHead.load(), 0, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAboutUsButton (ActionEvent actionEvent) {
        try {
            gridHeadChoice.getChildren().remove(0) ;
            FXMLLoader fxmlLoaderHead = new FXMLLoader();
            fxmlLoaderHead.setLocation(getClass().getResource("/ku/cs/aboutUsChoice.fxml"));
            gridHeadChoice.add(fxmlLoaderHead.load(), 0, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAdminInfoButton (ActionEvent actionEvent) {
        try {
            gridHeadChoice.getChildren().remove(0) ;
            FXMLLoader fxmlLoaderHead = new FXMLLoader();
            fxmlLoaderHead.setLocation(getClass().getResource("/ku/cs/adminInfoChoice.fxml"));
            gridHeadChoice.add(fxmlLoaderHead.load(), 0, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
