package ku.cs.shop.controllers.scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import ku.cs.shop.controllers.user.RegisterController;

public class AddImageController {

    @FXML private Button useImageButton ;
    @FXML private Button cancelButton ;

    RegisterController registerController ;

    public void setRegisterController(RegisterController registerController) {
        this.registerController = registerController;
    }
    
    @FXML
    public void handleUseButton (ActionEvent actionEvent) {
        registerController.handleUseButton();
    }
    @FXML
    public void handleCancelButton (ActionEvent actionEvent) {
        registerController.handleCancelButton();
    }
}
