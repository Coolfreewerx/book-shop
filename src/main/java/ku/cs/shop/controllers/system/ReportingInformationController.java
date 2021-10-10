package ku.cs.shop.controllers.system;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.shop.controllers.user.UserListForAdminController;
import ku.cs.shop.models.Book;
import ku.cs.shop.models.Reporting;

import java.io.File;

public class ReportingInformationController {

    @FXML
    private ImageView reportImage;
    @FXML
    private Label reportTypeLabel;
    @FXML
    private Label informationLabel;
    @FXML
    private Label reporterLabel;

    private Reporting reporting;
    private UserListForAdminController userListForAdminController;

    public void setData(Reporting reporting) {
        this.reporting = reporting;
        changeData();
    }

    public void setUserListForAdminController(UserListForAdminController userListForAdminController) {
        this.userListForAdminController = userListForAdminController;
    }

    public void changeData() {
        reportImage.setImage(new Image(reporting.getImagePath()));
        reportTypeLabel.setText(reporting.getReportType());
        informationLabel.setText(reporting.getInformation());
        reporterLabel.setText(reporting.getReporter());
    }

    public void handleCancelButton() {
        userListForAdminController.handleCancelButton();
    }

}