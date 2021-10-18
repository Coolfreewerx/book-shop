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
    private ProvideTypeBookList typeBookList;
    private ArrayList<ProvideTypeBook> typeBookArrayList;
    private AccountList accountList;
    private int index;
    private boolean checkAddButton = false;

    @FXML private Label subTypeBookLabel;
    @FXML private TextField subTypeBookTextField;
    @FXML private Button addSubTypeBookButton;
    @FXML private Label notificationAddButton;

    public void setData(ProvideTypeBook provideTypeBook,ArrayList<ProvideTypeBook> typeBookArrayList, AccountList accountList,int index,ProvideTypeBookList typeBookList){
        this.provideTypeBook = provideTypeBook;
        this.typeBookArrayList = typeBookArrayList;
        this.accountList = accountList;
        this.index = index;
        this.typeBookList = typeBookList;
    }

    public void changeData() {
        subTypeBookLabel.setText(provideTypeBook.getSubTypeBook());
    }
    public void changeTextFieldData() {
        System.out.println("changeTextFieldData " + typeBookArrayList.get(index).getSubTypeBook());
        subTypeBookTextField.setText(typeBookArrayList.get(index).getSubTypeBook());
    }

    public void handleKeySubTypeBookTextField(){
        if (checkAddButton == false)
            notificationAddButton.setText("กรุณากด add ข้อมูล");
        else
            notificationAddButton.setText("");
    }

    @FXML
    public void handleAddSubTypeButton(ActionEvent actionEvent) {
        provideTypeBookData = new ProvideTypeBook();
        System.out.println(provideTypeBookData);
        System.out.println(subTypeBookTextField);
        provideTypeBookData.setSubTypeBook(subTypeBookTextField.getText());
        provideTypeBookData.setSuperTypeBook(provideTypeBook.getSuperTypeBook());
        if (typeBookArrayList.size() != typeBookList.numOfSubTypeBook(provideTypeBook.getSuperTypeBook())){
            typeBookArrayList.add(provideTypeBookData);
        }
        else {
            typeBookArrayList.set(index,provideTypeBookData);
        }
        checkAddButton = true;
        notificationAddButton.setText("");
    }
}
