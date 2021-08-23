package ku.cs.controllers;

import javafx.event.ActionEvent;


public class SellerController {
    private Book book;
    public void handleDecreaseStockButton(ActionEvent actionEvent) {
        String decraseStock = decreaseStockTextField.getText();
        int decrease = Integer.parseInt(decraseStock);
        book.decreaseStock(decrase);
    }

    public void handleIncreaseStockButton(ActionEvent actionEvent) {
        String incraseStock = increaseStockTextField.getText();
        int increase = Integer.parseInt(incraseStock);
        book.increaseStock(incrase);
    }

    public void handlSetBookDetailButton(ActionEvent actionEvent) {
        String setBookDetail = setBookDetailTextField.getText();
        book.setBookDetail(setBookDetail);
    }

    public void handleSetBookPriceButton(ActionEvent actionEvent) {
        String setBookPrice = setBookPriceTextField.getText();
        book.setBookPrice(setBookPrice);
    }

    public void handleSetCategoryButton(ActionEvent actionEvent) {
        String setCategory = setCategoryTextField.getText();
        book.setCategory(setCategory);
    }

    public void handleSetBookNameButton(ActionEvent actionEvent) {
        String setBookName = setBookNameTextField.getText();
        book.setBookName(setBookName);
    }
}
