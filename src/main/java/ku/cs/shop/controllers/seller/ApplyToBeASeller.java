package ku.cs.shop.controllers.seller;

import javafx.fxml.FXML;

import java.io.IOException;

public class ApplyToBeASeller { //สมัครเป็นผู้ขายสินค้า
    @FXML
    public void handleApplyToBeSellerButton(){
        try {
            com.github.saacsos.FXRouter.goTo("applyToBeASeller");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า sellerStock ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleAddSellerStockButton(){
        try {
            com.github.saacsos.FXRouter.goTo("seller");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า sellerStock ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
