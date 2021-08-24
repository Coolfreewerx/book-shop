package ku.cs.shop.controllers;

import javafx.event.ActionEvent;
import ku.cs.shop.models.Book;

public class SellerController {
    private int newAmountBookStock;
    Book book = new Book() ;
//    public void handleEditStockButton(ActionEvent actionEvent) {
//        String editStock = editStockTextField.getText();
//        int edit = Integer.parseInt(editStock);
//        decreaseStock(decrease);
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

    // increase method
    public void increaseStock (int newAmountBookStock)
    {
        book.setBookStock(book.getBookStock()+newAmountBookStock);
    }

    // decrease method
    public void decreaseStock (int newAmountBookStock) {
        book.setBookStock(book.getBookStock()-newAmountBookStock);
    }
    public void editStock (int newAmountBookStock) {
        book.setBookStock(book.getBookStock()+newAmountBookStock);
    }
}
