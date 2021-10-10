package ku.cs.shop.controllers.system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import ku.cs.shop.models.*;
import ku.cs.shop.services.ReviewsDataSource;
import java.io.IOException;
import java.util.ArrayList;

public class BookDetailController
{
    @FXML private Label bookNameLabel;
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
    @FXML private Button status;
    @FXML private FlowPane commentFlowPane;
    @FXML private ImageView bookImg;
    @FXML private ImageView img;
    @FXML private ImageView logoJavaPai;
    @FXML private Hyperlink bookDetailByShop;
    @FXML private TextField commentTextField;
    @FXML private GridPane showPopupGrid;
    @FXML private Label numberOfComments;
    @FXML private Label bookRatingLabel;

    Reviews reviews = new Reviews();

    private AccountList accountList;
    private Account account;
    private Book book;
    private BookList bookList;
    private ReviewsList reviewsList;
    private ReviewsDataSource reviewsDataSource;
    private String imageName;

//    private OrderDataSource orderDataSource = new OrderDataSource("csv-data/bookOrder.csv");
//    private OrderList orderList = orderDataSource.readData();

    private ArrayList<Object> objectForPassing = new ArrayList<>();

    @FXML
    public void initialize()
    {
        objectForPassing = (ArrayList<Object>) com.github.saacsos.FXRouter.getData();
        reviewsDataSource = new ReviewsDataSource("csv-data/reviews.csv") ;
        reviewsList = reviewsDataSource.readData();
        castObjectToData();
        showData();
        pagesHeader();
        showCommentByBookNameAndShop(book.getBookName(), book.getBookShop());
        numberOfComments.setText("(" + reviewsList.getCountBookByNameAndShop(book.getBookName(), book.getBookShop()) + ")");
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
        bookNameLabel.setText(book.getBookName());
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

    public BookList getBookList() {
        return bookList;
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

    // show pop-up to buy book
    @FXML
    public void handleToBuyBook(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/buyBookPopup.fxml"));

            showPopupGrid.add(fxmlLoader.load(), 0, 0);
            ConfirmOrderController confirmOrderController = fxmlLoader.getController();
            confirmOrderController.setController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    public void handleClosePage() {
        showPopupGrid.getChildren().remove(0);
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

    @FXML //คลิกที่ logo แล้วจะไปหน้า home
    public void mouseClickedInLogo(){
        try{
            logoJavaPai.getOnMouseClicked();
            com.github.saacsos.FXRouter.goTo("home" ,accountList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // comment
    public void showCommentByBookNameAndShop(String bookName, String bookShop) { //แสดง comment
        commentFlowPane.getChildren().clear();
        ArrayList<Reviews> bookByNameAndShop = reviewsList.getReviewsByBookNameAndShopName(bookName, bookShop);
        System.out.println(reviewsList.sumOfRatingByBookNameAndBookShop(book.getBookName(),book.getBookShop(),reviews.getBookRating()));
        try {
            for (Reviews reviews : bookByNameAndShop) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/itemComment.fxml"));

                commentFlowPane.getChildren().add(fxmlLoader.load());
                ItemCommentController itemCommentController = fxmlLoader.getController();
                itemCommentController.setCommentData(reviews);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendReviewToWrite(){
        Reviews reviews = new Reviews(
                book.getBookName(),
                book.getBookShop(),
                account.getUserName(),
                imageName,
                commentTextField.getText(),
                addRating()
        );

        reviewsList.addReviews(reviews);
        reviewsDataSource.writeData(reviewsList);
    }

    public void setImageName() {
        imageName = account.getImageName() ;
    }

    @FXML
    public void handleAddRatingPlusOneButton(ActionEvent actionEvent){ // ให้คะแนนหนังสือเล่มนั้น 1 คะแนน
        reviews.addRatingPlusOne();
        addRating();
    }
    @FXML
    public void handleAddRatingPlusTwoButton(ActionEvent actionEvent){ // ให้คะแนนหนังสือเล่มนั้น 2 คะแนน
        reviews.addRatingPlusTwo();
        addRating();
    }
    @FXML
    public void handleAddRatingPlusThreeButton(ActionEvent actionEvent){ // ให้คะแนนหนังสือเล่มนั้น 3 คะแนน
        reviews.addRatingPlusThree();
        addRating();
    }
    @FXML
    public void handleAddRatingPlusFourButton(ActionEvent actionEvent){ // ให้คะแนนหนังสือเล่มนั้น 4 คะแนน
        reviews.addRatingPlusFour();
        addRating();
    }
    @FXML
    public void handleAddRatingPlusFiveButton(ActionEvent actionEvent){ // ให้คะแนนหนังสือเล่มนั้น 5 คะแนน
        reviews.addRatingPlusFive();
        addRating();
    }
    public int addRating(){
        return reviews.getBookRating();
    }

    @FXML // บันทึก comment ลง csv
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