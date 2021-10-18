package ku.cs.shop.controllers.system;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ku.cs.shop.models.Book;
import ku.cs.shop.models.ProvideTypeBook;

import java.util.ArrayList;

public class ShowSubTypeController {
    @FXML private Label bookSubType;
    @FXML private Label bookTypeMoreInfo;

    private Book book;
    private ProvideTypeBook provideTypeBook;
    private BookDetailController bookDetailController;

    public void changeData(ArrayList<ProvideTypeBook> provideTypeBook,Book book, int index) {
        System.out.println(book.getTypeBookArrayList());
        bookSubType.setText(book.getTypeBookArrayList().get(index).getSubTypeBook());
        bookTypeMoreInfo.setText(provideTypeBook.get(index).getSubTypeBook());
    }
}
