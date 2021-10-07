package ku.cs.shop.controllers.system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import ku.cs.shop.models.*;
import ku.cs.shop.services.BookDetailDataSource;
import ku.cs.shop.services.BookLowPriceToMaxPriceComparator;
import ku.cs.shop.services.BookMaxPriceToLowPriceComparator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class TypeBookController<MenuItemCartoon, bookTypeLabel> implements Initializable {

    @FXML private Label bookType;
    @FXML private TextField inputLowPriceTextField;
    @FXML private TextField inputMaxPriceTextField;
    @FXML private FlowPane bookListFlowPane;
    @FXML private MenuButton bookTypeMenuItem;
    @FXML private Text bookHeadLabel;
    @FXML private Button status;
    @FXML private Label usernameInHead;
    @FXML private ImageView img;
    @FXML private ImageView logoJavaPai;

    private double maxPriceFromInput ;
    private double lowPriceFromInput;
    private String currentType;
    private Account account;
    private AccountList accountList;
    private ArrayList<Book> bookFromPrice = new ArrayList<>();

    private ArrayList<Object> objectForPassing = new ArrayList<>();
    private BookDetailDataSource data = new BookDetailDataSource("csv-data/bookDetail.csv");
    private BookList books = data.readData();

    public void initialize (URL location, ResourceBundle resource){
        System.out.println("Welcome to  Market Book Page");
        accountList = (AccountList) com.github.saacsos.FXRouter.getData();
        account = accountList.getCurrentAccount();
        pagesHeader();

        bookHeadLabel.setText("ประเภทของหนังสือ");
        changeBookType("ประเภททั้งหมด");
        addBookTypeToMenuItem();
    }

    public ArrayList<Object> castDataToObject() {
        objectForPassing.clear();
        objectForPassing.add(books);
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

    public void addBookTypeToMenuItem() {
        for (String type : books.getBookType()) {
            MenuItem subBookTypeMenuItem = new MenuItem(type);
            bookTypeMenuItem.getItems().add(subBookTypeMenuItem);
            subBookTypeMenuItem.setOnAction(this :: handleSubBookTypeMenuItem);
        }
    }

    public void handleSubBookTypeMenuItem(ActionEvent actionEvent) {
        bookHeadLabel.setText("ประเภทของหนังสือ");
        MenuItem menuItem = (MenuItem) actionEvent.getSource();
        changeBookType(menuItem.getText());
        System.out.println("Click to " + currentType);
    }

    public void changeBookType(String type) {
        bookFromPrice.clear();
        currentType = type;
        bookType.setText(currentType.replace("ประเภททั้งหมด", "หนังสือทั้งหมด"));
        inputLowPriceTextField.clear();
        inputMaxPriceTextField.clear();
        bookListFlowPane.getChildren().clear();
        ArrayList<Book> bookByType = books.getBookByType(type);

        try {
            for (Book book : bookByType) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/item.fxml"));

                bookListFlowPane.getChildren().add(fxmlLoader.load()); // child,col,row
                ItemController itemController = fxmlLoader.getController();
                itemController.setData(book);
                itemController.setController(this, "byType");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMaxPriceFromInput(double maxPriceFromInput) {
        this.maxPriceFromInput = maxPriceFromInput;
    }

    public void setLowPriceFromInput(double lowPriceFromInput) {
        this.lowPriceFromInput = lowPriceFromInput;
    }

    public ArrayList<Object> getObjectForPassing() {
        castDataToObject();
        return objectForPassing;
    }

    @FXML
    public void handleSortFromInputLowPriceToMaxPrice(ActionEvent event) {
        setLowPriceFromInput(-1); // set to -1 to prevent program show data that not use
        setMaxPriceFromInput(-1);
        sortLowPriceToMaxPriceFromInput();
        changeBookTypeAndSortPriceFromInput("ประเภททั้งหมด");
    }

    public void changeBookTypeAndSortPriceFromInput(String type) {
        currentType = type;
        bookType.setText(currentType);
        bookListFlowPane.getChildren().clear();
        bookFromPrice.clear();
        ArrayList<Book> bookByType = books.getBookByType(type);

        try {
            for (Book book : bookByType) {
                if (book.getBookPrice() >= lowPriceFromInput && book.getBookPrice() <= maxPriceFromInput
                    && lowPriceFromInput >= 0 && maxPriceFromInput >= 0) {
                    bookFromPrice.add(book);
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/ku/cs/item.fxml"));

                    bookListFlowPane.getChildren().add(fxmlLoader.load()); // child,col,row
                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(book);
                    itemController.setController(this, "byType");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public double sortLowPriceToMaxPriceFromInput() {
        if ( Pattern.matches("[1-9]+[0-9]+" , inputLowPriceTextField.getText()) // fisrt case input many element : input from user by first element not == 0 and not be string
                || ((inputLowPriceTextField.getText().length() == 1) && Pattern.matches("[0-9]+", inputLowPriceTextField.getText()) )// second case input only one element
                && Double.parseDouble(inputLowPriceTextField.getText()) >= 0 ) {
            setLowPriceFromInput(Double.parseDouble(inputLowPriceTextField.getText()));
        }

        if ( Pattern.matches("[1-9]+[0-9]+" ,inputMaxPriceTextField.getText())
                || ((inputMaxPriceTextField.getText().length() == 1) && Pattern.matches("[0-9]+", inputMaxPriceTextField.getText()) )
                && Double.parseDouble(inputMaxPriceTextField.getText()) >= 0 ) {
            setMaxPriceFromInput(Double.parseDouble(inputMaxPriceTextField.getText()));
        }

        System.out.println("low price from user input " + lowPriceFromInput);
        System.out.println("max price from user input " + maxPriceFromInput);
        return 0;
    }

    @FXML
    public void handleLowPriceToMaxPrice(ActionEvent actionEvent) {
        if (!bookFromPrice.isEmpty()) {
            System.out.println("Sort Low Price To Max Price From User Input");

            BookLowPriceToMaxPriceComparator comparator = new BookLowPriceToMaxPriceComparator();
            bookListFlowPane.getChildren().clear();
            bookFromPrice.sort(comparator);

            try {
                for (Book book : bookFromPrice) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/ku/cs/item.fxml"));

                        bookListFlowPane.getChildren().add(fxmlLoader.load()); // child,col,row
                        ItemController itemController = fxmlLoader.getController();
                        itemController.setData(book);
                        itemController.setController(this, "byType");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (bookFromPrice.size() == 0) {
            BookLowPriceToMaxPriceComparator comparator = new BookLowPriceToMaxPriceComparator();
            bookListFlowPane.getChildren().clear();
            books.sort(comparator);
            changeBookType(currentType);
        }
    }

    @FXML
    public void handleMaxPriceToLowPrice(ActionEvent actionEvent) {
        if (!bookFromPrice.isEmpty()) {
            System.out.println("Sort Max Price To Low Price From User Input");

            BookMaxPriceToLowPriceComparator comparator = new BookMaxPriceToLowPriceComparator();
            bookListFlowPane.getChildren().clear();
            bookFromPrice.sort(comparator);

            try {
                for (Book book : bookFromPrice) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/ku/cs/item.fxml"));

                    bookListFlowPane.getChildren().add(fxmlLoader.load()); // child,col,row
                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(book);
                    itemController.setController(this, "byType");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (bookFromPrice.size() == 0) {
            BookMaxPriceToLowPriceComparator comparator = new BookMaxPriceToLowPriceComparator();
            bookListFlowPane.getChildren().clear();
            books.sort(comparator);
            changeBookType(currentType);
        }

    }

    @FXML
    public void handlePageAllTypeBookButton(ActionEvent actionEvent) {
        System.out.println("Click to " + currentType);
        bookHeadLabel.setText("หนังสือทั้งหมด");
        books.sort();
        changeBookType("ประเภททั้งหมด");
    }

    @FXML
    public void handleToInformationButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้าข้อมูลส่วนตัว
        try {
            com.github.saacsos.FXRouter.goTo("accountDetail", accountList);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า accountDetail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void mouseClickedInLogo(MouseEvent event){ // คลิกที่ logo แล้วจะไปหน้า home
        try{
            logoJavaPai.getOnMouseClicked();
            com.github.saacsos.FXRouter.goTo("home" ,accountList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
