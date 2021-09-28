package ku.cs.shop.controllers.system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.shop.models.AccountList;
import ku.cs.shop.models.Book;

import java.io.IOException;
import java.util.ArrayList;

public class ItemController {

    private Book book;
    private AccountList accountList;
    TypeBookController typeBookController;
    BookShopDetailController bookShopDetailController;


    @FXML private Label bookNameLabel;
    @FXML private Label bookPriceLabel;
    @FXML private ImageView img;

    private ArrayList<Object> objectForPassing = new ArrayList<>();
    private String type;

    @FXML
    public void handleSeeFullDetailButton(ActionEvent actionEvent) {
        try {
            setObjectForPassing(type);
            addBookToArrayList();
            com.github.saacsos.FXRouter.goTo("bookDetail", objectForPassing);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า bookDetail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public String setController(Object o, String type) {
        if (type.equals("byType")) {
            this.typeBookController = (TypeBookController) o;
        }
        if (type.equals("byShop")) {
            this.bookShopDetailController = (BookShopDetailController) o;
        }
        return this.type = type;
    }

    public void addBookToArrayList() {
        objectForPassing.add(book);
    }

    public void setObjectForPassing(String type) {
        ArrayList<Object> objects;
        objectForPassing.clear();

        if (type.equals("byType")) {
            objects = typeBookController.getObjectForPassing();
            objectForPassing = objects;
        }
        if (type.equals("byShop")) {
            objects = bookShopDetailController.getObjectForPassing();
            objectForPassing = objects;
        }
    }

    public void setData(Book book) {
        this.book = book;
        changeData();
    }

    public void changeData() {
        bookNameLabel.setText(book.getBookName());
        bookPriceLabel.setText(String.format("%.02f",book.getBookPrice()) + " Bahts.");
        img.setImage(new Image(book.getPicturePath()));
    }
}
