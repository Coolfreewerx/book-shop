package ku.cs.shop.controllers.system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ku.cs.shop.models.*;

import java.util.ArrayList;

public class ChoiceApplySubtypeBookController {
    private ProvideTypeBook provideTypeBook ;
    private ProvideTypeBook provideTypeBookData;
    private ArrayList<ProvideTypeBook> typeBookArrayList;
    private AccountList accountList;

    @FXML private Label subTypeBookLabel;
    @FXML private TextField subTypeBookTextField;
    @FXML private Button addSubTypeBookButton;

    public void setData(ProvideTypeBook provideTypeBook,ArrayList<ProvideTypeBook> typeBookArrayList, AccountList accountList){
        this.provideTypeBook = provideTypeBook;
        this.typeBookArrayList = typeBookArrayList;
        this.accountList = accountList;
    }

    public void changeData() {
        subTypeBookLabel.setText(provideTypeBook.getSubTypeBook());
    }

    @FXML
    public void handleAddSubTypeButton(ActionEvent actionEvent) {
        provideTypeBookData = new ProvideTypeBook();
        System.out.println(provideTypeBookData);
        System.out.println(subTypeBookTextField);
        provideTypeBookData.setSubTypeBook(subTypeBookTextField.getText());
        provideTypeBookData.setSuperTypeBook(provideTypeBook.getSuperTypeBook());
        typeBookArrayList.add(provideTypeBookData);
    }

//    public ProvideTypeBook sendDataBack() {
//        return provideTypeBookData;
//    }
}
