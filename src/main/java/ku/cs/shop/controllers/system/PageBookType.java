package ku.cs.shop.controllers.system;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class PageBookType {

    @FXML private GridPane gridHead;

    public void initialize () {
        try {
            FXMLLoader fxmlLoaderHead = new FXMLLoader();
            fxmlLoaderHead.setLocation(getClass().getResource("/ku/cs/headNoLogin.fxml"));
            gridHead.add(fxmlLoaderHead.load(), 0, 0);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
