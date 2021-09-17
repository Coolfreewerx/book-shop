package ku.cs.shop.controllers.seller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ku.cs.shop.models.Seller;


import java.io.IOException;

public class ApplySellerController {
    Seller seller = new Seller();

    private String bookName;
    private String bookShop;
    private String bookAuthor;
    private String bookISBN;
    private String bookType;
    private String bookDetail;
    private String bookPublisher;
    private String bookStatus;
    private String bookImg;
    private String bookStock;
    private String bookPage;
    private String leastStock;
    private String bookPrice;

    @FXML private Label userNameLabel;
    @FXML private Button addImgButton;
    @FXML private TextField bookNameTextField;
    @FXML private TextField bookAuthorTextField;
    @FXML private TextField bookISBNTextField;
    @FXML private TextField bookTypeTextField;
    @FXML private Button addBookButton;
    @FXML private TextField bookPageTextField;
    @FXML private Button goToSellerStockButton;
    @FXML private TextArea bookDetailTextArea;
    @FXML private TextField bookPublisherTextField;
    @FXML private TextField bookStockTextField;
    @FXML private TextField leastStockTextField;
    @FXML private TextField bookStatusTextField;
    @FXML private TextField bookPriceTextField;
    @FXML private Label NotificationBookISBN;
    @FXML private Label NotificationBookPage;
    @FXML private Label NotificationBookStock;
    @FXML private Label NotificationLeastStock;
    @FXML private Label NotificationCantAdd;

    @FXML
    public void handleAddBookButton(ActionEvent actionEvent){
        bookName = bookNameTextField.getText();
        bookAuthor = bookAuthorTextField.getText();
        bookISBN = bookISBNTextField.getText();
        NotificationBookISBN.setText(seller.checkBookISBNCorrect(bookISBN));
        if(!seller.isBookISBNCorrect(bookISBN)){ bookISBN = ""; }
        bookType = bookTypeTextField.getText();
        bookPage = bookPageTextField.getText();
        NotificationBookPage.setText(seller.checkNumber(bookPage));
        if(! seller.isNumber(bookPage)){bookPage = "";}
        bookDetail = bookDetailTextArea.getText();
        bookPublisher = bookPublisherTextField.getText();
        bookStock = bookStockTextField.getText();
        NotificationBookStock.setText(seller.checkNumber(bookStock));
        if(! seller.isNumber(bookStock)){bookStock = "";}
        leastStock = leastStockTextField.getText();
        NotificationLeastStock.setText(seller.checkNumber(bookStock));
        if(!seller.isNumber(bookStock)){leastStock = "";}
        bookStatus = bookStatusTextField.getText();
        bookPrice = bookPriceTextField.getText();

//        try {
//            com.github.saacsos.FXRouter.goTo("sellerStock");
//        } catch (IOException e) {
//            System.err.println("ไปที่หน้า sellerStock ไม่ได้");
//            System.err.println("ให้ตรวจสอบการกำหนด route");
//        }

        if (seller.getDataCheck(bookName,"Temporary",bookAuthor,bookISBN,bookType,bookDetail,bookPublisher,bookStatus,"Just test", bookStock,bookPage,leastStock,bookPrice)) {
            seller.writeSeller(bookName,"Temporary",bookAuthor,bookISBN,bookType,bookDetail,bookPublisher,bookStatus,"Just test", bookStock,bookPage,leastStock,bookPrice);
            NotificationCantAdd.setText("Can Add merchandise");

            try {
                com.github.saacsos.FXRouter.goTo("sellerStock");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า sellerStock ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
        else{
            NotificationCantAdd.setText("Cant Add merchandise");
        }

    }

    @FXML public void handleSellerStockButton(){
        try {
            com.github.saacsos.FXRouter.goTo("sellerStock");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า sellerStock ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleToHomeButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้า home
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้าเพจหลักไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }



}
