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
    Book book = new Book();
    private BookDetailDataSource data = new BookDetailDataSource("src/main/java/ku/cs/shop/bookDetail.csv");
    private BookList books = data.readData();


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

    @FXML public void handleKeyBookISBN(){
        book.setBookISBN(bookISBNTextField.getText());
        if(!seller.isBookISBNCorrect(book.getBookISBN())){ book.setBookISBN(""); }
        NotificationBookISBN.setText(seller.checkBookISBNCorrect(book.getBookISBN()));
    }

    @FXML public void handleKeyBookPage(){
        book.setBookPage(bookPageTextField.getText());
        if(! seller.isNumber(book.getBookPage())){book.setBookPage("");}
        NotificationBookPage.setText(seller.checkNumber(book.getBookPage()));
    }

    @FXML public void handleKeyBookStock(){
        if(! seller.isNumber(bookStockTextField.getText())){book.setBookStock(-1);}
        else {book.setBookStock(Integer.parseInt(bookStockTextField.getText()));}
        NotificationBookStock.setText(seller.checkNumber(bookStockTextField.getText()));
    }

    @FXML public void handleKeyLeastStock(){
        if(!seller.isNumber(leastStockTextField.getText())){book.setLeastStock(-1);}
        else {book.setLeastStock(Integer.parseInt(leastStockTextField.getText()));}
        NotificationLeastStock.setText(seller.checkNumber(leastStockTextField.getText()));
    }

    @FXML public void handleKeyBookPrice(ActionEvent actionEvent){
        if(!seller.isNumber(bookPriceTextField.getText())){book.setBookPrice(-1);}
        else {book.setBookPrice(Double.parseDouble(bookPriceTextField.getText()));}
        NotificationBookPrice.setText(seller.checkNumber(bookPriceTextField.getText()));
    }

    @FXML public void handleCartoonMenuButton(ActionEvent actionEvent){ book.setBookType("หนังสือการ์ตูน");menuButton.setText("หนังสือการ์ตูน"); }
    @FXML public void handleMegazineMenuButton(ActionEvent actionEvent){ book.setBookType("นิตยสาร");menuButton.setText("นิตยสาร"); }
    @FXML public void handleNovelMenuButton(ActionEvent actionEvent){ book.setBookType("นิยาย");menuButton.setText("นิยาย"); }
    @FXML public void handleStudyBookMenuButton(ActionEvent actionEvent){ book.setBookType("หนังสือเรียน");menuButton.setText("หนังสือเรียน"); }
    @FXML public void handleEBookMenuButton(ActionEvent actionEvent){ book.setBookType("e-book");menuButton.setText("e-book"); }

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
        book.setBookImg("Haveimage");
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
        book.setBookName(bookNameTextField.getText());
        book.setBookAuthor(bookAuthorTextField.getText());
        book.setBookDetail(bookDetailTextArea.getText());
        book.setBookPublisher(bookPublisherTextField.getText());
        book.setBookShop("nanazenislovingShop");

        book.setBookISBN(bookISBNTextField.getText());
        book.setBookPage(bookPageTextField.getText());
        book.setBookStock(Integer.parseInt(bookStockTextField.getText()));
        book.setLeastStock(Integer.parseInt(leastStockTextField.getText()));
        book.setBookPrice(Double.parseDouble(bookPriceTextField.getText()));

        if (seller.getDataCheck(book)) {
            NotificationCantAdd.setText("Can Add merchandise");
            setImageName();
            book.setBookImg(imageName);
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
            NotificationCantAdd.setText("Can not Add merchandise");
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
