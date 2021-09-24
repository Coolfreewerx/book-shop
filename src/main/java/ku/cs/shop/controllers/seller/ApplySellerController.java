package ku.cs.shop.controllers.seller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ku.cs.shop.models.Book;
import ku.cs.shop.models.BookList;
import ku.cs.shop.models.Seller;
import ku.cs.shop.models.User;
import ku.cs.shop.services.BookDetailDataSource;
import ku.cs.shop.services.DataSource;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ApplySellerController {
    Seller seller = new Seller();
    BookDetailDataSource bookDetailDataSource;
    private BookDetailDataSource data = new BookDetailDataSource("src/main/java/ku/cs/shop/bookDetail.csv");
    private BookList books = data.readData();

    private String bookName;
    private String bookShop;
    private String bookAuthor;
    private String bookISBN;
    private String bookType = "";
    private String bookDetail;
    private String bookPublisher;
    private String bookStatus;
    private String bookImg = "";
    private String bookStock;
    private String bookPage;
    private String leastStock;
    private String bookPrice;

    @FXML private Label userNameLabel;
    @FXML private Button addImgButton;
    @FXML private TextField bookNameTextField;
    @FXML private TextField bookAuthorTextField;
    @FXML private TextField bookISBNTextField;
    @FXML private Button addBookButton;
    @FXML private TextField bookPageTextField;
    @FXML private Button goToSellerStockButton;
    @FXML private TextArea bookDetailTextArea;
    @FXML private TextField bookPublisherTextField;
    @FXML private TextField bookStockTextField;
    @FXML private TextField leastStockTextField;
    @FXML private TextField bookPriceTextField;
    @FXML private Label NotificationBookISBN;
    @FXML private Label NotificationBookPage;
    @FXML private Label NotificationBookStock;
    @FXML private Label NotificationLeastStock;
    @FXML private Label NotificationBookPrice;
    @FXML private Label NotificationCantAdd;
    @FXML private ImageView imageView;
    @FXML private MenuButton menuButton;

    private File selectedImage;
    private String imageName;

    @FXML
    public void handleKeyBookISBN(){
        bookISBN = bookISBNTextField.getText();
        NotificationBookISBN.setText(seller.checkBookISBNCorrect(bookISBN));
        if(!seller.isBookISBNCorrect(bookISBN)){ bookISBN = ""; }
    }

    @FXML
    public void handleKeyBookPage(){
        bookPage = bookPageTextField.getText();
        NotificationBookPage.setText(seller.checkNumber(bookPage));
        if(! seller.isNumber(bookPage)){bookPage = "";}
    }

    @FXML
    public void handleKeyBookStock(){
        bookStock = bookStockTextField.getText();
        NotificationBookStock.setText(seller.checkNumber(bookStock));
        if(! seller.isNumber(bookStock)){bookStock = "";}
        bookStatus = "พร้อมส่ง";
    }

    @FXML
    public void handleKeyLeastStock(){
        leastStock = leastStockTextField.getText();
        NotificationLeastStock.setText(seller.checkNumber(bookStock));
        if(!seller.isNumber(bookStock)){leastStock = "";}
    }

    @FXML
    public void handleKeyBookPrice(ActionEvent actionEvent){
        bookPrice = bookPriceTextField.getText();
        NotificationBookPrice.setText(seller.checkNumber(bookPrice));
        if(!seller.isNumber(bookPrice)){bookPrice = "";}
    }

    @FXML
    public void handleCartoonMenuButton(ActionEvent actionEvent){
        bookType = "หนังสือการ์ตูน";
        menuButton.setText("หนังสือการ์ตูน");
    }
    @FXML
    public void handleMegazineMenuButton(ActionEvent actionEvent){
        bookType = "นิตยสาร";
        menuButton.setText("นิตยสาร");
    }
    @FXML
    public void handleNovelMenuButton(ActionEvent actionEvent){
        bookType = "นิยาย";
        menuButton.setText("นิยาย");
    }
    @FXML
    public void handleStudyBookMenuButton(ActionEvent actionEvent){
        bookType = "หนังสือเรียน";
        menuButton.setText("หนังสือเรียน");
    }
    @FXML
    public void handleEBookMenuButton(ActionEvent actionEvent){
        bookType = "e-book";
        menuButton.setText("e-book");
    }

    @FXML
    public void handleAddImageButton (ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Picture");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*jpg"));
        selectedImage = fileChooser.showOpenDialog(null);
        if (selectedImage != null) {
            Image image = new Image(selectedImage.toURI().toString());
            imageView.setImage(image);
        }
        bookImg = "Haveimage";
    }
    public void setImageName() {
        if (!selectedImage.equals("")) {
            imageName =  bookNameTextField.getText() + "-"
                    + LocalDate.now().getYear() + "-"
                    + LocalDate.now().getMonth() + "-"
                    + LocalDate.now().getDayOfMonth() + "-"
                    + LocalDateTime.now().getHour() + LocalDateTime.now().getMinute() + LocalDateTime.now().getSecond() + ".png" ;
            seller.copyImageToPackage(selectedImage , imageName) ;
        } else {
            imageName = "default.png" ;
        }
    }

    @FXML
    public void handleAddBookButton(ActionEvent actionEvent){
        bookName = bookNameTextField.getText();
        bookAuthor = bookAuthorTextField.getText();
        bookISBN = bookISBNTextField.getText();
        bookPage = bookPageTextField.getText();
        bookDetail = bookDetailTextArea.getText();
        bookPublisher = bookPublisherTextField.getText();
        bookStock = bookStockTextField.getText();
        leastStock = leastStockTextField.getText();
        bookPrice = bookPriceTextField.getText();

        if (seller.getDataCheck(bookName,"nanazenShop",bookAuthor,bookISBN,bookType,bookDetail,bookPublisher,bookStatus,bookImg, bookStock,bookPage,leastStock,bookPrice)) {
            NotificationCantAdd.setText("Can Add merchandise");

            setImageName();
            bookImg = imageName;
            int bookStockPara = Integer.parseInt(bookStock);
            int leastStockPara = Integer.parseInt(leastStock);
            double bookPricePara = Double.parseDouble(bookPrice);

            Book book = new Book(bookName,"Shop",bookAuthor,bookISBN,bookType,bookDetail,bookPublisher,bookImg,bookStockPara,bookPage,leastStockPara,bookPricePara);

            DataSource<BookList> dataSource;
            dataSource = new BookDetailDataSource("src/main/java/ku/cs/shop/bookDetail.csv");
            BookList bookList = dataSource.readData();
            bookList.addBook(book);
            dataSource.writeData(bookList);

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
