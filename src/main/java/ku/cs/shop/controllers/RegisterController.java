package ku.cs.shop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ku.cs.shop.models.User ;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterController {

    private User user ;
    // controller เชื่อมต่อกับ view เพื่อรับข้อมูลมาเก็บ
    @FXML private TextField firstNameTextField ;
    @FXML private TextField lastNameTextField ;
    @FXML private TextField userNameTextField ;
    @FXML private TextField passwordTextField ;
    @FXML private TextField passwordRecheckTextField ;
    @FXML private TextField birthDayTextField ;
    @FXML private TextField birthMonthTextField ;
    @FXML private TextField birthYearTextField ;


//    public void checkAndComparePassword(ActionEvent actionEvent) {
//        String passwordStr = passwordTextField.getText() ;
//        String passwordRecheckStr = passwordRecheckTextField.getText() ;
//        //ตรวจสอบความปลอดภัยของรหัสผ่านตามที่กำหนด
//
//        //ตรวจสอบการยืนยันรหัสผ่าน
//        if (!(passwordStr.equals(passwordRecheckStr))){
//            System.err.println("รหัสผ่านไม่ตรงกัน");
//            System.err.println("ตรวจสอบรหัสผ่านอีกครั้ง");
//        }
//    }
//
//    public void checkUserNameCondition(ActionEvent actionEvent) {
//        String userNameStr = userNameTextField.getText() ;
//        //ตรวจสอบ username ว่าตรงเงื่อนไขมั้ย
//
//        //ตรวจสอบ username ว่าซ้ำมั้ย
//    }
    //ปุ่ม Login
    @FXML
    public void registerButton(ActionEvent actionEvent) {
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
        String passwordStr = passwordTextField.getText() ;
        String birthDayStr = birthDayTextField.getText() ;
        String birthMonthStr = birthMonthTextField.getText() ;
        String birthYearStr = birthYearTextField.getText() ;

        //นำข้อมูล String เก็บใน FieldClass และบันทึกลง CSV
        File userData = new File("src/main/java/ku/cs/shop/userData.txt");

        FileWriter writer ;
        try {
            writer = new FileWriter(userData, true);
            writer.write(firstNameStr+","+lastNameStr+","+userNameStr+","+passwordStr+","+
                    birthDayStr+","+birthMonthStr+","+birthYearStr+"\r\n");
            writer.close();
        }
        catch (IOException e){
            System.err.println("เกิดข้อผิดพลาด สมัครไม่สำเร็จ");
        }
        //ยังไม่ได้ใช้ user = new User(firstNameStr, lastNameStr, userNameStr, passwordStr, birthDayStr, birthMonthStr, birthYearStr);
    }
    @FXML
    public void handleToLoginButton(ActionEvent actionEvent) { //ปุ่มสำหรับกดไปหน้า หนังสือทั้งหมด (เพจหลัก)
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
