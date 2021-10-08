package ku.cs.shop.controllers.system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import ku.cs.shop.models.*;
import ku.cs.shop.services.AccountDataSource;
import ku.cs.shop.services.BookDetailDataSource;
import ku.cs.shop.services.OrderDataSource;
import ku.cs.shop.services.ReviewsDataSource;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class BookDetailController
{
    BookDetailDataSource data = new BookDetailDataSource("csv-data/bookDetail.csv");

    @FXML private Label bookName;
    @FXML private Label bookShop;
    @FXML private Label bookStatus;
    @FXML private Label bookDetail;
    @FXML private Label bookType;
    @FXML private Label bookISBN;
    @FXML private Label bookPage;
    @FXML private Label bookPublisher;
    @FXML private Label bookAuthor;
    @FXML private Label bookPrice;
    @FXML private Label typeLabel;
    @FXML private Label usernameInHead;
    @FXML private Label bookRating;
    @FXML private Button status;
    @FXML private FlowPane commentFlowPane;
    @FXML private ImageView bookImg;
    @FXML private ImageView img;
    @FXML private ImageView logoJavaPai;
    @FXML private Hyperlink bookDetailByShop;
    @FXML private TextField commentTextField;
    @FXML private GridPane showPopupGrid;


    private AccountList accountList;
    private Account account;
    private Book book;
    private BookList bookList;
    private Review review;
    private ReviewList reviewList;
    private ReviewsDataSource reviewsDataSource;
    private File selectedImage ; // ใช้นะแต่น้องไม่ขึ้นสีเฉยเลย
    private String imageName;

    private OrderDataSource orderDataSource = new OrderDataSource("csv-data/bookOrder.csv");
    private OrderList orderList = orderDataSource.readData();
    ReviewsDataSource reviewsData = new ReviewsDataSource("csv-data/reviews.csv");
    ReviewList reviews = reviewsDataSource.readData();

    private ArrayList<Object> objectForPassing = new ArrayList<>();

    @FXML
    public void initialize()
    {
        objectForPassing = (ArrayList<Object>) com.github.saacsos.FXRouter.getData();
        reviewsDataSource = new ReviewsDataSource("csv-data/reviews.csv") ;
        reviewList = reviewsDataSource.readData();
        castObjectToData();
        showData();
        pagesHeader();
        showCommentByBookName(book.getBookName());
    }

    public void setData(Review review) {
        this.review = review;
    }

    public void castObjectToData() {
        book = (Book) objectForPassing.get(3);
        bookList = (BookList) objectForPassing.get(0);
        account = (Account) objectForPassing.get(1);
        accountList = (AccountList) objectForPassing.get(2);
        System.out.println(book.getBookShop());
        System.out.println(account.getUserName());
    }

    public Book getBook() {
        return book;
    }

    public Account getAccount() {
        return account;
    }

    public void showData() {
        bookName.setText(book.getBookName());
        bookShop.setText(book.getBookShop());
        bookStatus.setText(book.getBookStatus());
        bookDetail.setText(book.getBookDetail());
        bookType.setText(book.getBookType());
        bookISBN.setText(book.getBookISBN());
        bookPage.setText(book.getBookPage());
        bookPublisher.setText(book.getBookPublisher());
        bookAuthor.setText(book.getBookAuthor());
        typeLabel.setText(book.getBookType());
        bookImg.setImage(new Image(book.getPicturePath()));
        bookPrice.setText(String.format("%.02f",book.getBookPrice()) + " Baht.");
    }

    public ArrayList<Object> castDataToObject() {
        objectForPassing.clear();
        objectForPassing.add(book);
        objectForPassing.add(bookList);
        objectForPassing.add(account);
        objectForPassing.add(accountList);

        return objectForPassing;
    }

    public void pagesHeader() { // กำหนดและแสดงข้อมูลตรงส่วน head page
        usernameInHead.setText(account.getUserName());
        img.setImage(new Image(account.getImagePath()));

        if (account instanceof AdminAccount){
            status.setText("Admin");
        } else if (account.getShopName().equals("ยังไม่ได้สมัครเป็นผู้ขาย")) {
            status.setText("User");
        } else {
            status.setText("Seller");
        }
    }

    public void handleToInformationButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("accountDetail", accountList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleToBuyBook(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/buyBookPopup.fxml"));

            showPopupGrid.add(fxmlLoader.load(), 0, 0);
            ConfirmOrderController  confirmOrderController = fxmlLoader.getController();
            confirmOrderController.setController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    @FXML
    public void handleBackToMarket() {
        try {
            com.github.saacsos.FXRouter.goTo("pageBookType", accountList);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า pageBookType ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleToBookSortFromShop(ActionEvent actionevent) {
        try {
            System.out.println("Click to pageBookShop");
            com.github.saacsos.FXRouter.goTo("pageBookShop", castDataToObject());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า pageBookShop ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleAllTypeBookButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("pageBookType", accountList);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า pageBookType ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void mouseClickedInLogo(MouseEvent event){ //คลิกที่ logo แล้วจะไปหน้า home
        try{
            logoJavaPai.getOnMouseClicked();
            com.github.saacsos.FXRouter.goTo("home" ,accountList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showCommentByBookName(String bookName) { //รับ String bookName
        bookName = book.getBookName();
        commentFlowPane.getChildren().clear();
        ArrayList<Review> bookByName = reviewList.getReviewsByBookName(bookName);
        try {
            for (Review review : bookByName) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/itemComment.fxml"));

                commentFlowPane.getChildren().add(fxmlLoader.load()); // child,col,row
                ItemCommentController itemCommentController = fxmlLoader.getController();
                itemCommentController.setData(review);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendReviewToWrite(){
        Review review = new Review(
                book.getBookName(),
                book.getBookShop(),
                account.getUserName(),
                imageName,
                commentTextField.getText(),
                Integer.parseInt(bookRating.getText())
        );
        System.out.println(reviewList.getCountBookByNameAndShop(book.getBookName()));
        reviewList.addReviews(review);
        reviewsDataSource.writeData(reviewList);
    }

    public void setImageName() {
        if (selectedImage != null) {
            imageName =  account.getUserName() + "-"
                    + LocalDate.now().getYear() + "-"
                    + LocalDate.now().getMonth() + "-"
                    + LocalDate.now().getDayOfMonth() + "-"
                    + LocalDateTime.now().getHour() + LocalDateTime.now().getMinute() + LocalDateTime.now().getSecond() + ".png" ;
            UserAccount.copyImageToPackage(selectedImage , imageName) ;
        } else {
            imageName = account.getImageName() ;
        }
    }


    @FXML
    public void handleAddRatingPlusOneButton(ActionEvent actionEvent){
        review.addRatingPlusOne();
        changeRating();
    }
    @FXML
    public void handleAddRatingPlusTwoButton(ActionEvent actionEvent){
        review.addRatingPlusTwo();
        changeRating();
    }
    @FXML
    public void handleAddRatingPlusThreeButton(ActionEvent actionEvent){
        review.addRatingPlusThree();
        changeRating();
    }
    @FXML
    public void handleAddRatingPlusFourButton(ActionEvent actionEvent){
        review.addRatingPlusFour();
        changeRating();
    }
    @FXML
    public void handleAddRatingPlusFiveButton(ActionEvent actionEvent){
        review.addRatingPlusFive();
        changeRating();
    }

    public void changeRating(){
        bookRating.setText(review.getRating() + ""); // แสดงผลคะแนนเฉลี่ย
    }

    @FXML
    public void handleSendCommentButton(ActionEvent actionEvent) {
        setImageName();
        sendReviewToWrite();
        try {
            com.github.saacsos.FXRouter.goTo("bookDetail", objectForPassing);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
