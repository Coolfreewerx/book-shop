package ku.cs.shop.controllers.system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import ku.cs.shop.models.Book;
import ku.cs.shop.models.OrderList;
import ku.cs.shop.services.OrderDataSource;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ConfirmOrderController {
    @FXML private Label bookNameLabel;
    @FXML private Label sumBookPriceLabel;
    @FXML private Text noficationItem;
    @FXML private TextField inputNumOfBookTextField;

    private int totalBookOrdered;
    private double costOfBook;
    private int stockInShop;

    BookDetailController bookDetailController;

    private OrderDataSource orderDataSource = new OrderDataSource("csv-data/bookOrder.csv");
    private OrderList orderList = orderDataSource.readData();

    public void setController(BookDetailController book) {
        this.bookDetailController = book;
        bookNameLabel.setText(bookDetailController.getBook().getBookName());
        setCostOfBook(bookDetailController.getBook().getBookPrice());
        setStockInShop(bookDetailController.getBook().getBookStock());
        System.out.println("book stock of " + bookNameLabel.getText() + "is " + stockInShop);
    }

    public void setStockInShop(int stockInShop) {
        this.stockInShop = stockInShop;
    }

    public void setCostOfBook(double costOfBook) {
        this.costOfBook = costOfBook;
    }

    public void setTotalBookOrdered(int totalBookOrdered) {
        this.totalBookOrdered = totalBookOrdered;
    }

    public int checkInputNumOfOrder() {
        if ( Pattern.matches("[1-9]+[0-9]+" , inputNumOfBookTextField.getText())
                || ((inputNumOfBookTextField.getText().length() == 1) && Pattern.matches("[0-9]+", inputNumOfBookTextField.getText()) )
                && Integer.parseInt(inputNumOfBookTextField.getText()) >= 0) {
            setTotalBookOrdered(Integer.parseInt(inputNumOfBookTextField.getText()));
        }

        return 0;
    }

    @FXML
    void handleAddNumOfBookInput(ActionEvent event) {
        checkInputNumOfOrder();

        if(totalBookOrdered > stockInShop)
            noficationItem.setText("สินค้าในคลังไม่เพียงพอ กรุณากรอกจำนวนใหม่");
            sumBookPriceLabel.setText("0.00");

        if (totalBookOrdered <= stockInShop) {
            noficationItem.setText("");
            sumBookPriceLabel.setText(String.format("%.02f",costOfBook * totalBookOrdered));
             if ( sumBookPriceLabel.getText().equals("0.00") ) {
                 noficationItem.setText("กรุณากรอกจำนวนสินค้าให้ถูกต้อง (มากกว่า 0) ");
             }
        }

    }

    @FXML
    void handleComfirmBuyBook(ActionEvent event) {

    }

    @FXML
    void handleClosePage(MouseEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("bookDetail");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
