package ku.cs.shop.controllers.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import ku.cs.shop.models.Account;
import ku.cs.shop.models.AccountList;
import ku.cs.shop.models.UserAccount;
import ku.cs.shop.services.AccountDataSource;
import com.github.saacsos.FXRouter;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class EditInformationController {

    private Account account;
    private AccountList accountList;
    private AccountDataSource accountDataSource;

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

    private File selectedImage ;
    private String imageName;

    private ObservableList sexList = FXCollections.observableArrayList() ;

    @FXML
    public void initialize(){
        accountList = (AccountList) com.github.saacsos.FXRouter.getData() ;
        account = accountList.getCurrentAccount() ;
        showData();
    }
    public void showData(){
        usernameInEditInformationLabel.setText(account.getUserName());
        birthdayInEditInformationLabel.setText(account.getBirthDay());
        birthMonthInEditInformationLabel.setText(account.getBirthMonth());
        birthYearInEditInformationLabel.setText(account.getBirthYear());
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

    public void setSexChoice(ActionEvent event) {
        sexChoice.setValue("เพศ");
        sexChoice.getItems().removeAll(sexList) ;
        sexList.removeAll(sexList);
        lodeSexData();
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
        sendDataToWrite();
        try{
            FXRouter.goTo("accountDetail");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า detailUser ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleGotoInformationPageWhenCancel(){ //ปุ่มกลับไปหน้าข้อมูลส่วนตัวเมื่อไม่ต้องการแก้ไขข้อมูลแล้ว
        try {
            FXRouter.goTo("accountDetail");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า detailUser ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void sendDataToWrite() {
        //UserDataSource
        Account account = new UserAccount(
                firstnameTextField.getText(),
                lastnameTextField.getText(),
                usernameInEditInformationLabel.getText(),
                passwordField.getText(),
                birthdayInEditInformationLabel.getText(),
                birthMonthInEditInformationLabel.getText(),
                birthYearInEditInformationLabel.getText(),
                imageName,
                phoneNumberTextField.getText(),
                sexChoice.getValue(),
                "null",
                "ยังไม่สมัครเป็นผู้ขาย",
                "working",
                LocalDateTime.now() ) ;

//        (String firstName, String lastName, String userName, String password,
//                String birthDay, String birthMonth, String birthYear,
//                String imageName, String phone, String sex, String address, String shopName,
//                String status, LocalDateTime loginTime )

        accountList.addAccount(account);
        accountDataSource.writeData(accountList) ;
    }
}
