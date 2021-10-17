package ku.cs.shop.controllers.system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import ku.cs.shop.models.*;
import ku.cs.shop.services.PromotionDataSource;
import java.io.IOException;
import java.util.ArrayList;

public class PromotionToUserController {
    @FXML private ImageView img;
    @FXML private ImageView logoJavaPai;
    @FXML private Button status;
    @FXML private Label usernameInHead;

    @FXML private FlowPane promotionListFlowPane;
    @FXML private Label shopNameLabel;

    private PromotionList promotionList;
    private AccountList accountList ;
    private Account account;
    private PromotionDataSource promotionDataSource;

    public void initialize(){
        promotionDataSource = new PromotionDataSource("csv-data/promotion.csv");
        promotionList = promotionDataSource.readData();
        accountList = (AccountList) com.github.saacsos.FXRouter.getData() ;
        account = accountList.getCurrentAccount() ;
        pagesHeader();
        shopNameLabel.setText(account.getShopName());
        showPromotionByShopNameInPagePromotionByBookShop(account.getShopName());
    }

    public void showPromotionByShopNameInPagePromotionByBookShop(String shopName) { // แสดงโปรโมชั่น
        promotionListFlowPane.getChildren().clear();
        ArrayList<Promotion> promotionByShopName = promotionList.getPromotionByShopName(shopName);
        try {
            for (Promotion promotion : promotionByShopName) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/itemPromotion.fxml"));

                promotionListFlowPane.getChildren().add(fxmlLoader.load());
                ItemPromotionController itemPromotionController = fxmlLoader.getController();
                itemPromotionController.setPromotionData(promotion);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
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
    public void handleToInformationButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("accountDetail", accountList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void mouseClickedInLogo(MouseEvent event){ //คลิกที่รูป logo แล้วจะไปหน้า home
        try{
            logoJavaPai.getOnMouseClicked();
            com.github.saacsos.FXRouter.goTo("home" ,accountList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
