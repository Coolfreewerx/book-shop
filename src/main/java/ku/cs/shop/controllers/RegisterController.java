package ku.cs.shop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import ku.cs.shop.models.User ;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterController {

    // controller เชื่อมต่อกับ view เพื่อรับข้อมูล
    @FXML private TextField firstNameTextField ;
    @FXML private TextField lastNameTextField ;
    @FXML private TextField userNameTextField ;
    @FXML private PasswordField passwordField ;
    @FXML private PasswordField checkPasswordField ;
    @FXML private TextField birthDayTextField ;
    @FXML private TextField birthMonthTextField ;
    @FXML private TextField birthYearTextField ;
    @FXML private Label passwordCompareLabel ;
    @FXML private Label registerErrorLabel ;

    //private boolean usernameCheck = true ;
    private boolean passwordCheck = false ;

    //ตรวจสอบการยืนยันรหัสผ่าน
    @FXML
    public void comparePassword() {
        String passwordStr = passwordField.getText();
        String passwordRecheckStr = checkPasswordField.getText();

        if (passwordStr.equals(passwordRecheckStr)) {
            passwordCompareLabel.setText("");
            passwordCheck = true ;
        }
        else {
            passwordCompareLabel.setText("รหัสผ่านไม่ตรงกัน โปรดตรวจสอบรหัสผ่าน");
            passwordCheck = false ;
        }

    }

    //ตรวจสอบรหัสผ่านตามเงื่อนไข  //setTextFill(Color.rgb(21, 117, 84));

//    public void checkUserNameCondition() {
//        String userNameStr = userNameTextField.getText() ;
//        //ตรวจสอบ username ว่าตรงเงื่อนไขมั้ย
//
//        //ตรวจสอบ username ว่าซ้ำมั้ย
//    }

    //ปุ่ม register
    @FXML
    public void registerButton(ActionEvent actionEvent) {
        //ตรวจสอบความถูกต้อง username password
        if(!(passwordCheck/*&&usernameCheck*/)) {
            registerErrorLabel.setText("พบข้อผิดพลาด กรุณาตรวจสอบข้อมูล");
            registerErrorLabel.setTextFill(Color.rgb(210, 39, 30));
            return;
        }
        // ตรวจสอบว่าทุกช่องมีข้อมูล
        else if (firstNameTextField.getText().equals("")||lastNameTextField.getText().equals("")||
        userNameTextField.getText().equals("")||checkPasswordField.getText().equals("")||
        birthDayTextField.getText().equals("")||birthMonthTextField.getText().equals("")||birthYearTextField.getText().equals("")) {
            registerErrorLabel.setText("ข้อมูลไม่ครบถ้วน กรุณาตรวจสอบข้อมูล");
            registerErrorLabel.setTextFill(Color.rgb(210, 39, 30));
            return;
        }

        registerErrorLabel.setTextFill(Color.rgb(255, 255, 255));
        writeUserInfo();
        try {
            com.github.saacsos.FXRouter.goTo("login");
        }
        catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    //เก็บข้อมูลของ user
    @FXML
    public void writeUserInfo() {

        String firstNameStr = firstNameTextField.getText() ;
        String lastNameStr = lastNameTextField.getText() ;
        String userNameStr = userNameTextField.getText() ;
        String passwordStr = passwordField.getText() ;
        String birthDayStr = birthDayTextField.getText() ;
        String birthMonthStr = birthMonthTextField.getText() ;
        String birthYearStr = birthYearTextField.getText() ;

        //นำข้อมูล String เก็บใน FieldClass และบันทึกลง CSV
        File userData = new File("src/main/java/ku/cs/shop/userData.csv");

        FileWriter writer ;
        try {
            writer = new FileWriter(userData, true);
            writer.write(""+userNameStr+","+firstNameStr+","+lastNameStr+","+passwordStr+","+
                    birthDayStr+","+birthMonthStr+","+birthYearStr+"\r\n");
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
            System.err.println("สมัครไม่สำเร็จ");
        }
    }

    //ปุ่มกลับไปหน้า login
    @FXML
    public void handleToLoginButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
