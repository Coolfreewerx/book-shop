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
    private ArrayList<ProvideTypeBook> typeBookArrayList;

    @FXML private Label subTypeBookLabel;
    @FXML private TextField subTypeBookTextField;

    public void setData(ProvideTypeBook provideTypeBook,ArrayList<ProvideTypeBook> typeBookArrayList){
        this.provideTypeBook = provideTypeBook;
        this.typeBookArrayList = typeBookArrayList;
    }

    public void changeData() {
        subTypeBookLabel.setText(provideTypeBook.getSubTypeBook());
    }

    public void sendBackData() {
        provideTypeBook.setSubTypeBook(subTypeBookTextField.getText());
        typeBookArrayList.add(provideTypeBook);
    }
}
