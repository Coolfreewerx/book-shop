package ku.cs.shop.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class AdminInfoChoiceController {

    @FXML private GridPane gridInfo ;

    @FXML
    public void handleChoiceClick(){
        try {
            FXMLLoader fxmlLoaderInfo = new FXMLLoader();
            fxmlLoaderInfo.setLocation(getClass().getResource("/ku/cs/information.fxml"));
            gridInfo.add(fxmlLoaderInfo.load(), 0, 1);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
