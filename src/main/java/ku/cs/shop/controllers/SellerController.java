package ku.cs.shop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ku.cs.shop.models.Book;
import ku.cs.shop.models.Seller;

public class SellerController {
    private int newAmountBookStock;
    Seller bookSeller = new Seller();
    @FXML
    private TextField editStockTextField;
    @FXML
    private Button editStockButton;

    public void handleEditStockButton(ActionEvent actionEvent) {
        String editStock = editStockTextField.getText();
        int edit = Integer.parseInt(editStock);
        bookSeller.editStock(edit);
    }
//    }
//    public void handleDecreaseStockButton(ActionEvent actionEvent) {
//        String decreaseStock = decreaseStockTextField.getText();
//        int decrease = Integer.parseInt(decreaseStock);
//        decreaseStock(decrease);
//    }
//
//    public void handleIncreaseStockButton(ActionEvent actionEvent) {
//        String increaseStock = increaseStockTextField.getText();
//        int increase = Integer.parseInt(increaseStock);
//        increaseStock(increase);
//    }
//
//    public void handlSetBookDetailButton(ActionEvent actionEvent) {
//        String setBookDetail = setBookDetailTextField.getText();
//        book.setBookDetail(setBookDetail);
//    }
//
//    public void handleSetBookPriceButton(ActionEvent actionEvent) {
//        String setBookPrice = setBookPriceTextField.getText();
//        book.setBookPrice(setBookPrice);
//    }
//
//    public void handleSetBookTypeButton(ActionEvent actionEvent) {
//        String setBookType = setBookTypeTextField.getText();
//        book.setBookType(setBookType);
//    }
//
//    public void handleSetBookNameButton(ActionEvent actionEvent) {
//        String setBookName = setBookNameTextField.getText();
//        book.setBookName(setBookName);
//    }
}
