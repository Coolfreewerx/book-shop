package ku.cs.shop.controllers.system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.shop.models.*;

import java.util.ArrayList;

public class ItemCommentController {
    @FXML private ComboBox<String> choice;
    @FXML private Label commentLabel;
    @FXML private ImageView userImageView;
    @FXML private Label usernameLabel;

    private Reviews reviews;

    private ObservableList choiceList = FXCollections.observableArrayList();
    private ArrayList<Object> objectForPassing = new ArrayList<>();

    private void lodeData() { // ตัวเลือกตรงช้อยของคอมเม้น
        choice.getItems().removeAll(choiceList);
        choiceList.removeAll(choiceList);
        String sex = "รายงานความไม่เหมาะสม";
        String[] arr = sex.split(",");
        int i = 0;

        while ( i < 1){
            choiceList.add(arr[i]);
            i++;
        }
        choice.getItems().addAll(choiceList);
    }

    public void setCommentData(Reviews reviews) {
        this.reviews = reviews;
        changeData();
    }

    public void changeData() { // เปลี่ยนข้อมูล comment
        usernameLabel.setText(reviews.getUserName());
        choice.getItems().addAll(choiceList);
        userImageView.setImage(new Image(reviews.getImagePath()));
        commentLabel.setText(reviews.getComment());
        lodeData();
    }
}
