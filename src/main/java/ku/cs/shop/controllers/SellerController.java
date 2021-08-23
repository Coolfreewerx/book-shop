package ku.cs.shop.controllers;

import javafx.event.ActionEvent;
import ku.cs.shop.models.Book;

public class SellerController {
    private Book book;
    public void handleDecreaseStockButton(ActionEvent actionEvent) {
        String decraseStock = decreaseStockTextField.getText();
        int decrease = Integer.parseInt(decraseStock);
        book.decreaseStock(decrease);
    }

    public void handleIncreaseStockButton(ActionEvent actionEvent) {
        String incraseStock = increaseStockTextField.getText();
        int increase = Integer.parseInt(incraseStock);
        book.increaseStock(increase);
    }

    public void handlSetBookDetailButton(ActionEvent actionEvent) {
        String setBookDetail = setBookDetailTextField.getText();
        book.setBookDetail(setBookDetail);
    }

    public void handleSetBookPriceButton(ActionEvent actionEvent) {
        String setBookPrice = setBookPriceTextField.getText();
        book.setBookPrice(setBookPrice);
    }

    public void handleSetBookTypeButton(ActionEvent actionEvent) {
        String setBookType = setBookTypeTextField.getText();
        book.setBookType(setBookType);
    }

    public void handleSetBookNameButton(ActionEvent actionEvent) {
        String setBookName = setBookNameTextField.getText();
        book.setBookName(setBookName);
    }
}
