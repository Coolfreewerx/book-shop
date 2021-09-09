package ku.cs.shop.controllers.seller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import ku.cs.shop.models.Seller;

public class ApplySellerController {
    Seller seller = new Seller();
    @FXML private TextField nameShopTextField;
    @FXML private TextField addressShopTextField;
    @FXML private TextField detailShopTextField;
    @FXML private TextField mailShopTextField;
    @FXML private TextField telShopTextField;
    @FXML private TextField bankShopTextField;
    @FXML private Button submitButton;

    @FXML
    public void initialize () {}


}
