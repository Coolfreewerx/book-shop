package ku.cs.shop.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AboutUsInformationController {

    @FXML private ImageView infoImage ;

    public void setInfoImage1() {
        String path = getClass().getResource("/ku/cs/image/fresh.jpg").toExternalForm();
        infoImage.setImage(new Image(path));
    }
    public void setInfoImage2() {
        String path = getClass().getResource("/ku/cs/images/fresh.jpg").toExternalForm();
        infoImage.setImage(new Image(path));
    }
    public void setInfoImage3() {
        String path = getClass().getResource("/ku/cs/images/fresh.jpg").toExternalForm();
        infoImage.setImage(new Image(path));
    }
    public void setInfoImage4() {
        String path = getClass().getResource("/ku/cs/images/fresh.jpg").toExternalForm();
        infoImage.setImage(new Image(path));
    }
//    public void setInfoImage5() {
//        String path = getClass().getResource("/ku/cs/images/myprofile.jpg").toExternalForm();
//        infoImage.setImage(new Image(path));
//    }
//    public void setInfoImage1() {
//        String path = getClass().getResource("/ku/cs/images/myprofile.jpg").toExternalForm();
//        infoImage.setImage(new Image(path));
//    }
//    public void setInfoImage1() {
//        String path = getClass().getResource("/ku/cs/images/myprofile.jpg").toExternalForm();
//        infoImage.setImage(new Image(path));
//    }
//    public void setInfoImage1() {
//        String path = getClass().getResource("/ku/cs/images/myprofile.jpg").toExternalForm();
//        infoImage.setImage(new Image(path));
//    }
}
