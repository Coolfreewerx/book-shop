package ku.cs.shop.controllers.system;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import ku.cs.shop.models.Promotion;
import ku.cs.shop.models.PromotionList;
import ku.cs.shop.models.Reviews;
import ku.cs.shop.services.PromotionDataSource;

public class ItemPromotionController {
    @FXML private TextField codePromotionTextField;
    @FXML private Label promotionDetailLabel;
    @FXML private Label ratePromotionLabel;

    private Promotion promotion;
    private PromotionDataSource promotionDataSource = new PromotionDataSource("csv-data/promotion.csv");
    private PromotionList promotionList = promotionDataSource.readData();

    public void setPromotionData(Promotion promotion) {
        this.promotion = promotion;
        changeData();
    }

    public void changeData() { // เปลี่ยนข้อมูล comment
        codePromotionTextField.setText(promotion.getCodePromotion());
        promotionDetailLabel.setText(promotion.getPromotionDetail());
        ratePromotionLabel.setText(promotion.getRatePrice()+"");
    }
}
