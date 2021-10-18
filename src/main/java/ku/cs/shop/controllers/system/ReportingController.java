package ku.cs.shop.controllers.system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import ku.cs.shop.models.*;
import ku.cs.shop.services.AccountDataSource;
import ku.cs.shop.services.ReportingDataSource;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class ReportingController {

    @FXML private ImageView reportImage ;
    @FXML private TextField userNameTextField ;
    @FXML private ComboBox<String> reportTypeChoice ;
    @FXML private TextField informationTextField ;
    @FXML private Text errorText ;

    private ObservableList reportTypeList = FXCollections.observableArrayList() ;

    private AccountList accountList;
    private Account account;

    private File selectedImage ;
    private File selectedImageForCopy ;
    private String imageName ;

    private ReportingList reportingList ;
    private ReportingDataSource reportingDataSource ;

    private ArrayList<Object> objectForPassing  = new ArrayList<>();

    public void initialize(){
        accountList = (AccountList) com.github.saacsos.FXRouter.getData();
        account = accountList.getCurrentAccount();
        reportingDataSource = new ReportingDataSource("csv-data/report.csv") ;
        reportingList = reportingDataSource.readData() ;
        lodeReportTypeData();
    }

    public void lodeReportTypeData() {
        reportTypeList.removeAll(reportTypeList) ;
        reportTypeList.add("ความคิดเห็นไม่เหมาะสม") ;
        reportTypeList.add("สินค้าที่ลงขายไม่เหมาะสม") ;
        reportTypeList.add("อื่น ๆ") ;
        reportTypeChoice.getItems().addAll(reportTypeList) ;
    }

    @FXML
    public void handleAddImageButton (ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Picture");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*jpg"));
        selectedImage = fileChooser.showOpenDialog(null);

        if (selectedImage != null) {
            Image image = new Image(selectedImage.toURI().toString());
            reportImage.setImage(image);
            selectedImageForCopy = selectedImage ;
        }
    }

    public void setImageName() {
        if (selectedImageForCopy != null) {
            imageName =  userNameTextField.getText() + "-"
                    + LocalDate.now().getYear() + "-"
                    + LocalDate.now().getMonth() + "-"
                    + LocalDate.now().getDayOfMonth() + "-"
                    + LocalDateTime.now().getHour() + LocalDateTime.now(ZoneId.of("Asia/Bangkok")).getMinute() + LocalDateTime.now().getSecond() + ".png" ;
            Reporting.copyImageToPackage(selectedImageForCopy , imageName) ;
        }
    }

    public void sendDataToWrite() {
        //UserDataSource
        Reporting reporting = new Reporting(
                userNameTextField.getText(),
                reportTypeChoice.getValue(),
                imageName,
                informationTextField.getText(),
                account.getUserName()) ;

        reportingList.addReporting(reporting);
        reportingDataSource.writeData(reportingList) ;
    }

    public String checkData() {
        // ตรวจสอบว่าทุกช่องมีข้อมูล
        if ((selectedImageForCopy == null || userNameTextField.getText().equals("") || reportTypeChoice.getValue().equals("") || informationTextField.getText().equals(""))) {
            return "โปรดใส่รายละเอียดให้ครบถ้วน" ;
        } else if (!(accountList.checkUserNameHaveUsed(userNameTextField.getText()))) {
            return "ไม่พบชื่อผู้ใช้นี้ในระบบ" ;
        } else {
            return " ";
        }
    }

    @FXML
    public void handleReportButton(ActionEvent actionEvent) {

        errorText.setText(checkData());
        if (!(checkData().equals(" "))) {
            return;
        }
        setImageName();
        sendDataToWrite();

        try {
            com.github.saacsos.FXRouter.goTo("accountDetail", accountList);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า accountDetail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleToAccountDetailButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("accountDetail", accountList);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า accountDetail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

}
