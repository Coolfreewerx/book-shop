package ku.cs.shop.controllers.seller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import ku.cs.shop.controllers.system.ChoiceApplySubtypeBookController;
import ku.cs.shop.models.*;
import ku.cs.shop.services.BookDetailDataSource;
import ku.cs.shop.services.DataSource;
import ku.cs.shop.services.ProvideTypeBookDataSource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditBookController {
    private Seller seller = new Seller();
    private Book book;
    private String startedBookname;
    private BookList bookList;
    public void setData(Book book) { this.book = book; }

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
    @FXML private Label bookNameTopicLabel;
    @FXML private Button status;
    @FXML private Label usernameInHead;
    @FXML private ImageView img;
    @FXML private ImageView logoJavaPai;
    @FXML private FlowPane flowPaneSubTypeBook;

    private File selectedImage;
    private String imageName;
    private String currentType;

    private ArrayList<Account> accountsList = new ArrayList<>();
    private AccountList accountList ;
    private Account account ;
    private ArrayList<Object> objectForPassing = new ArrayList<>();

    private ProvideTypeBookDataSource provideTypeBookDataSource = new ProvideTypeBookDataSource("csv-data/provideTypeBookData.csv");
    private ProvideTypeBookList typeBookList = provideTypeBookDataSource.readData();
    private ProvideTypeBook provideTypeBook = new ProvideTypeBook("","");

    @FXML public void initialize(){
        objectForPassing = (ArrayList<Object>) com.github.saacsos.FXRouter.getData();
        castObjectToData();

        startedBookname = book.getBookName();
        bookNameTopicLabel.setText(book.getBookName());
        bookNameTextField.setText(book.getBookName());
        bookAuthorTextField.setText(book.getBookAuthor());
        bookISBNTextField.setText(book.getBookISBN());
        bookPageTextField.setText(book.getBookPage());
        bookDetailTextArea.setText(book.getBookDetail());
        bookPublisherTextField.setText(book.getBookPublisher());
        bookStockTextField.setText(String.valueOf(book.getBookStock()));
        leastStockTextField.setText(String.valueOf(book.getLeastStock()));
        bookPriceTextField.setText(String.format("%.02f",book.getBookPrice()));
        menuButton.setText(book.getBookType());
        imageView.setImage(new Image(book.getPicturePath()));
        pagesHeader();

        changeBookType(book.getBookType());
        addBookTypeToMenuItem();
    }
    public void castObjectToData() {
        book = (Book) objectForPassing.get(0);
        bookList = (BookList) objectForPassing.get(1);
        account = (Account) objectForPassing.get(2);
        accountList = (AccountList) objectForPassing.get(3);
    }

    @FXML
    public void handleKeyBookName(){
        book.setBookName(bookNameTextField.getText());
    }

    @FXML
    public void handleKeyBookAuthor(){
        book.setBookAuthor(bookAuthorTextField.getText());
    }


    @FXML public void handleKeyBookISBN(){
        book.setBookISBN(bookISBNTextField.getText());
        if(!seller.isBookISBNCorrect(book.getBookISBN())){ book.setBookISBN(""); }
        NotificationBookISBN.setText(seller.checkBookISBNCorrect(book.getBookISBN()));
    }

    @FXML public void handleKeyBookPage(){
        book.setBookPage(bookPageTextField.getText());
        if(! seller.isIntNumber(book.getBookPage())){book.setBookPage("");}
        NotificationBookPage.setText(seller.checkIntNumber(book.getBookPage()));
    }

    @FXML public void handleKeyBookStock(){
        if(! seller.isIntNumber(bookStockTextField.getText())){book.setBookStock(-1);}
        else {book.setBookStock(Integer.parseInt(bookStockTextField.getText()));}
        NotificationBookStock.setText(seller.checkIntNumber(bookStockTextField.getText()));
    }

    @FXML public void handleKeyLeastStock(){
        if(!seller.isIntNumber(leastStockTextField.getText())){book.setLeastStock(-1);}
        else {book.setLeastStock(Integer.parseInt(leastStockTextField.getText()));}
        NotificationLeastStock.setText(seller.checkIntNumber(leastStockTextField.getText()));
    }

    @FXML public void handleKeyBookPrice(){
        if(!seller.isDoubleNumber(bookPriceTextField.getText())){book.setBookPrice(-1);}
        else {book.setBookPrice(Double.parseDouble(bookPriceTextField.getText()));}
        NotificationBookPrice.setText(seller.checkDoubleNumber(bookPriceTextField.getText()));
    }

    public void addBookTypeToMenuItem() {
        for (String type : bookList.getBookType()) {
            System.out.println("for addBookTypeToMenuItem : " + type);

            MenuItem subBookTypeMenuItem = new MenuItem(type);
            menuButton.getItems().add(subBookTypeMenuItem);
            subBookTypeMenuItem.setOnAction(this :: handleSubBookTypeMenuItem);

        }
    }

    public void handleSubBookTypeMenuItem(ActionEvent actionEvent) {
        MenuItem menuItem = (MenuItem) actionEvent.getSource();
        changeBookType(menuItem.getText());
        System.out.println("Click to " + currentType);
    }

    public void changeBookType(String type){
        flowPaneSubTypeBook.getChildren().clear();
        currentType = type;
        book.setBookType(type);
        menuButton.setText(type);
        ArrayList<ProvideTypeBook> provideTypeBookArrayList = typeBookList.findSubTypeBook(book.getBookType());
        int numTypeBookList = typeBookList.numOfSubTypeBook(book.getBookType());

        System.out.println("NumArraylist" + provideTypeBookArrayList.size());
        System.out.println(provideTypeBookArrayList.get(0).getSuperTypeBook() +  "Arraylist Subtype 1 is " + provideTypeBookArrayList.get(0).getSubTypeBook());

        try {
            for (int i = 0; i < numTypeBookList ; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/choiceApplySubTypeBook.fxml"));
                flowPaneSubTypeBook.getChildren().add(fxmlLoader.load());

                ChoiceApplySubtypeBookController choiceApplySubtypeBookController = fxmlLoader.getController();
                choiceApplySubtypeBookController.setData(provideTypeBookArrayList.get(i));
                choiceApplySubtypeBookController.changeData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        setImageName();
        book.setBookImg(imageName);

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
    public void handleEditBookButton(ActionEvent actionEvent){
        book.setBookName(bookNameTextField.getText());
        book.setBookAuthor(bookAuthorTextField.getText());
        book.setBookDetail(bookDetailTextArea.getText());
        book.setBookPublisher(bookPublisherTextField.getText());
        book.setBookShop(account.getShopName());
        book.setTimeOfAddingBook(LocalDateTime.now());

        book.setBookISBN(bookISBNTextField.getText());
        book.setBookPage(bookPageTextField.getText());
        book.setBookStock(Integer.parseInt(bookStockTextField.getText()));
        book.setLeastStock(Integer.parseInt(leastStockTextField.getText()));
        book.setBookPrice(Double.parseDouble(bookPriceTextField.getText()));

        if (seller.getDataCheck(book) && (seller.isBookISBNCorrect(book.getBookISBN())) && (seller.isIntNumber(book.getBookPage()))
                &&(seller.isIntNumber(bookStockTextField.getText()))&&(seller.isIntNumber(leastStockTextField.getText())) &&(seller.isDoubleNumber(bookPriceTextField.getText()))) {
            DataSource<BookList> dataSource;
            dataSource = new BookDetailDataSource("csv-data/bookDetail.csv");
            System.out.println("Can Edit booklist");
            System.out.println(bookList.getBookByShop(account.getShopName()).get(0).getBookName());

            dataSource.writeData(bookList);
            try {
                com.github.saacsos.FXRouter.goTo("sellerStock", accountList);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("ไปที่หน้า sellerStock ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
        else{
            NotificationCantAdd.setText("ไม่สามารถแก้ไขสินค้าได้ กรุณาตรวจสอบข้อมูลใหม่อีกครั้งค่ะ");
            System.out.println("seller.getDataCheck(book) " + seller.getDataCheck(book) );
            System.out.println("seller.isBookISBNCorrect(book.getBookISBN()) " + seller.isBookISBNCorrect(book.getBookISBN()));
            System.out.println("seller.isNumber(book.getBookPage() " + seller.isIntNumber(book.getBookPage()));
            System.out.println("seller.isNumber(bookStockTextField.getText()) " + seller.isIntNumber(bookStockTextField.getText()));
            System.out.println("seller.isNumber(leastStockTextField.getText()) " + seller.isIntNumber(leastStockTextField.getText()));
            System.out.println("seller.isNumber(bookPriceTextField.getText()) " + seller.isDoubleNumber(bookPriceTextField.getText()));
        }

    }

    @FXML public void handleSellerStockButton(){
        try {
            com.github.saacsos.FXRouter.goTo("sellerStock", accountList);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า sellerStock ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleToAccountDetailButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("accountDetail", accountList);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า accountDetail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleToSellerButton(ActionEvent actionEvent) {
        if (account.getShopName().equals("ยังไม่ได้สมัครเป็นผู้ขาย")) {
            try {
                com.github.saacsos.FXRouter.goTo("sellerHaventApply",accountList);
            } catch (IOException e) {
                System.err.println("ไปที่หน้า sellerHaventApply ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
        else{
            try {
                com.github.saacsos.FXRouter.goTo("sellerStock",accountList);
            } catch (IOException e) {
                System.err.println("ไปที่หน้า sellerHaventApply ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
    }

    @FXML
    public void handleAllTypeBookButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าหนังสือทั้งหมด
        try {
            com.github.saacsos.FXRouter.goTo("pageBookType", accountList);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    @FXML
    public void mouseClickedInLogo(MouseEvent event){
        try{
            logoJavaPai.getOnMouseClicked();
            com.github.saacsos.FXRouter.goTo("home" ,accountList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //ออกจากระบบกลับไปล็อกอิน
    @FXML
    public void handleToLogoutButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}