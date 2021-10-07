package ku.cs.shop.controllers.system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import ku.cs.shop.models.*;
import ku.cs.shop.services.ReviewsDataSource;

import java.io.IOException;
import java.util.ArrayList;

public class ReviewController {
    private Review review;
    private ReviewList reviewList;
    private AccountList accountList;
    private Account account;
    private Book book;
    private BookList bookList;

    @FXML private TextField commentTextField;
    @FXML private ImageView backButton;

    private ArrayList<Object> objectForPassing = new ArrayList<>();

    @FXML
    public void initialize()
    {
//        objectForPassing = (ArrayList<Object>) com.github.saacsos.FXRouter.getData();
    }

    public void sendReviewToWrite(){
        review.setComment(commentTextField.getText());

        ReviewsDataSource reviewsDataSource = new ReviewsDataSource("csv-data/reviews.csv") ;
        reviewsDataSource.writeData(reviewList);
    }

    @FXML
    public void handleSendCommentButton(ActionEvent actionEvent) {
        sendReviewToWrite();
        try {
            com.github.saacsos.FXRouter.goTo("bookDetail");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void mouseClickedInLogo(MouseEvent event){ //ปุ่มคลิกกลับหน้า bookDetail
        try{
            backButton.getOnMouseClicked();
            com.github.saacsos.FXRouter.goTo("bookDetail");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
