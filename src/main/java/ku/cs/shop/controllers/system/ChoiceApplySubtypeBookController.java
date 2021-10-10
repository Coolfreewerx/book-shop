package ku.cs.shop.controllers.system;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ku.cs.shop.models.Book;
import ku.cs.shop.models.BookList;
import ku.cs.shop.models.ProvideTypeBook;
import ku.cs.shop.models.ProvideTypeBookList;

import java.util.ArrayList;

public class ChoiceApplySubtypeBookController {
    private ProvideTypeBook provideTypeBook;

    @FXML private Label subTypeBookLabel;
    @FXML private TextField subTypeBookTextField;

    public void setData(ProvideTypeBook provideTypeBook){
        this.provideTypeBook = provideTypeBook;
    }

    public void changeData() {
        subTypeBookLabel.setText(provideTypeBook.getSubTypeBook());
    }
}
