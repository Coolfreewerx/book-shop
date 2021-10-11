package ku.cs.shop.controllers.system;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.shop.models.Book;
import ku.cs.shop.models.Order;

public class OrderController {
    @FXML private ImageView bookImageView;
    @FXML private Label bookNameLabel;
    @FXML private Label numBookLabel;
    @FXML private Label notificationForStock;
    @FXML private Label nameCustomerLabel;
    @FXML private Label contactCustomerLabel;
    @FXML private Label numPriceLabel;
    @FXML private Button editStatusButton;
    private Order order;

    public void setData(Order order) {
        this.order = order;
    }

    public void changeData() {
        bookNameLabel.setText(order.getBookName());
        numPriceLabel.setText(order.getTotalPriceOrdered() + "");
        numBookLabel.setText(order.getTotalBookOrdered() + "");
        nameCustomerLabel.setText(order.getCustomerName());
        contactCustomerLabel.setText(order.getCustomerPhone());
        bookImageView.setImage(new Image(order.getPicturePath()));
    }


}
