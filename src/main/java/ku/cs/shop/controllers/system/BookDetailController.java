package ku.cs.shop.controllers.system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import ku.cs.shop.models.*;
import ku.cs.shop.services.BookDetailDataSource;
import java.io.IOException;
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
    @FXML private ImageView bookImg;
    @FXML private Hyperlink bookDetailByShop;
    @FXML private Button status;
    @FXML private Label usernameInHead;
    @FXML private ImageView img;
    @FXML private ImageView logoJavaPai;
    @FXML private FlowPane commentFlowPane;
    @FXML private Label bookRating;
    @FXML private TextField commentTextField;
    @FXML private Button sendComment;


    private AccountList accountList;
    private Account account;
    private Book book;
    private BookList bookList;

    private ArrayList<Object> objectForPassing = new ArrayList<>();

    @FXML
    public void initialize()
    {
        objectForPassing = (ArrayList<Object>) com.github.saacsos.FXRouter.getData();
        castObjectToData();
        showData();
        pagesHeader();
        showCommentByBookName();
    }



    public void castObjectToData() {
        book = (Book) objectForPassing.get(3);
        bookList = (BookList) objectForPassing.get(0);
        account = (Account) objectForPassing.get(1);
        accountList = (AccountList) objectForPassing.get(2);
        System.out.println(book.getBookShop());
        System.out.println(account.getUserName());
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
        if(account instanceof AdminAccount){
            status.setText("Admin");
        }else if(account.getShopName().equals("ยังไม่ได้สมัครเป็นผู้ขาย")){
            status.setText("User");
        }else {
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

    public void showCommentByBookName(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/ku/cs/itemComment.fxml"));

            commentFlowPane.getChildren().add(fxmlLoader.load()); // child,col,row
            ItemCommentController itemCommentController = fxmlLoader.getController();
//            itemCommentController.setData(accountList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
