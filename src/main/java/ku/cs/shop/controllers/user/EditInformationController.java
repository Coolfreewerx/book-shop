package ku.cs.shop.controllers.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import ku.cs.shop.models.Account;
import ku.cs.shop.models.AccountList;
import ku.cs.shop.models.BookList;
import ku.cs.shop.models.UserAccount;
import ku.cs.shop.services.AccountDataSource;
import com.github.saacsos.FXRouter;
import ku.cs.shop.services.BookDetailDataSource;
import ku.cs.shop.services.DataSource;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EditInformationController {

    private Account account;
    private AccountList accountList;
    private AccountDataSource accountDataSource;
    private File selectedImage ;
    private String imageName;
    private String username;

    @FXML private TextField lastnameTextField;
    @FXML private TextField firstnameTextField;
    @FXML private TextField phoneNumberTextField;
    @FXML private ComboBox<String> sexChoice;
    @FXML private PasswordField passwordField;
    @FXML private Label birthdayInEditInformationLabel;
    @FXML private Label birthMonthInEditInformationLabel;
    @FXML private Label birthYearInEditInformationLabel;
    @FXML private Label usernameInEditInformationLabel;
    @FXML private PasswordField recheckPasswordField;
    @FXML private Label passwordConditionCheckLabel;
    @FXML private Label passwordCompareLabel;
    @FXML private ImageView imageView;
    @FXML private Label warningEditInformation;

    private ObservableList sexList = FXCollections.observableArrayList() ;
    private AccountDataSource data = new AccountDataSource("csv-data/accountData.csv");

    @FXML
    public void initialize(){
        accountDataSource = new AccountDataSource("csv-data/accountData.csv") ;
        accountList = accountDataSource.readData() ;
        accountList = (AccountList) com.github.saacsos.FXRouter.getData() ;
        account = accountList.getCurrentAccount() ;

        username = account.getUserName();
        usernameInEditInformationLabel.setText(account.getUserName());
        birthdayInEditInformationLabel.setText(account.getBirthDay());
        birthMonthInEditInformationLabel.setText(account.getBirthMonth());
        birthYearInEditInformationLabel.setText(account.getBirthYear());
        firstnameTextField.setText(account.getFirstName());
        lastnameTextField.setText(account.getLastName());
        passwordField.setText(account.getPassword());
        sexChoice.getItems().addAll(sexList);
        lodeSexData();
    }

    @FXML //ทำงานเมื่อกรอกรหัส
    public void handleKeyPassword() {
        if (Account.checkPasswordCondition(passwordField.getText())){
            passwordConditionCheckLabel.setText("รหัสผ่านนี้สามารถใช้ได้") ;
            passwordConditionCheckLabel.setTextFill(Color.rgb(21, 117, 84));
        }
        else {
            passwordConditionCheckLabel.setText("รหัสผ่านไม่ตรงตามรูปแบบที่กำหนด");
            passwordConditionCheckLabel.setTextFill(Color.rgb(210, 39, 30));
        }
    }
    @FXML //ทำงานเมื่อกรอกยืนยันรหัส
    public void handleKeyCheckPassword() {
        passwordCompareLabel.setText(Account.comparePassword(passwordField.getText(), recheckPasswordField.getText()));
    }

    private void lodeSexData() {
        sexChoice.getItems().removeAll(sexList);
        sexList.removeAll(sexList) ;
        String sex = "หญิง,ชาย,ไม่ระบุ" ;
        String[] arr = sex.split(",") ;
        int i = 0 ;

        while ( i < 3){
            sexList.add(arr[i]) ;
            i++ ;
        }
        sexChoice.getItems().addAll(sexList) ;
    }

    @FXML
    public void handleGoToInformationPageWhenEditInformation(){ //ปุ่มกลับไปหน้าข้อมูลส่วนตัวหลังแก้ไขข้อมูลเสร็จ
        account.setFirstName(firstnameTextField.getText());
        account.setLastName(lastnameTextField.getText());
        account.setPassword(passwordField.getText());
        account.setPhone(phoneNumberTextField.getText());
        account.setSex(sexChoice.getValue());
        account.setImageName(imageName);

//        System.out.println(account.isMyUserName(username));
//        System.out.println(accountList.searchByUserName(username));
//        System.out.println(accountList.getCurrentAccount());

        if(accountList.getCurrentAccount().equals(accountList.searchByUserName(username))){
            System.out.println("เข้าเงื่อนไข");
            DataSource <AccountList> dataSource;
            dataSource = new AccountDataSource("csv-data/accountData.csv");

            AccountList accountList = dataSource.readData();
            accountList.editInformationByName(username, account);

            dataSource.writeData(accountList);
        }
        try{
            FXRouter.goTo("accountDetail", accountList);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า detailUser ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleGotoInformationPageWhenCancel(){ //ปุ่มกลับไปหน้าข้อมูลส่วนตัวเมื่อไม่ต้องการแก้ไขข้อมูลแล้ว
        try {
            FXRouter.goTo("accountDetail", accountList);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า detailUser ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleAddImageButton (ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Picture");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*jpg"));
        selectedImage = fileChooser.showOpenDialog(null);

        if (selectedImage != null) {
            Image image = new Image(selectedImage.toURI().toString());
            imageView.setImage(image);
        }
        setImageName();
        account.setImageName(imageName);
    }

    public void setImageName() {
        if (selectedImage != null) {
            imageName =  usernameInEditInformationLabel.getText() + "-"
                    + LocalDate.now().getYear() + "-"
                    + LocalDate.now().getMonth() + "-"
                    + LocalDate.now().getDayOfMonth() + "-"
                    + LocalDateTime.now().getHour() + LocalDateTime.now().getMinute() + LocalDateTime.now().getSecond() + ".png" ;
            UserAccount.copyImageToPackage(selectedImage , imageName) ;
        } else {
            imageName = "default.png" ;
        }
    }

    //เช็คการกรอกข้อมูลก่อนสมัคร
    public String checkData() {
        // ตรวจสอบว่าทุกช่องมีข้อมูล
        if ((firstnameTextField.getText().equals("") || lastnameTextField.getText().equals("")
                || passwordField.getText().equals("") || sexChoice.getValue().equals("")
                || imageView.getImage().equals(""))) {
            return "ข้อมูลไม่ครบถ้วน โปรดตรวจสอบข้อมูลอีกครั้ง";
        }
        else if (!(Account.getPasswordCheck() && Account.getPasswordCondition() && Account.getUserNameCheck() )) {
            return "ข้อมูลมีข้อผิดพลาดโปรดตรวจสอบข้อมูลอีกครั้ง" ;
        }
        else {
            return " ";
        }
    }
}
