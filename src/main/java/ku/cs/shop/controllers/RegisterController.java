package ku.cs.shop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ku.cs.shop.models.User ;

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


    public void checkAndComparePassword(ActionEvent actionEvent) {
        String passwordStr = passwordTextField.getText() ;
        String passwordRecheckStr = passwordRecheckTextField.getText() ;
        //ตรวจสอบความปลอดภัยของรหัสผ่านตามที่กำหนด

        //ตรวจสอบการยืนยันรหัสผ่าน
        if (!(passwordStr.equals(passwordRecheckStr))){
            System.err.println("รหัสผ่านไม่ตรงกัน");
            System.err.println("ตรวจสอบรหัสผ่านอีกครั้ง");
        }
    }

    public void checkUserNameCondition(ActionEvent actionEvent) {
        String userNameStr = userNameTextField.getText() ;
        //ตรวจสอบ username ว่าตรงเงื่อนไขมั้ย

        //ตรวจสอบ username ว่าซ้ำมั้ย
    }

    //เก็บข้อมูลของ user
    public void writeUserInfo(ActionEvent actionEvent) {
        String firstNameStr = firstNameTextField.getText() ;
        String lastNameStr = lastNameTextField.getText() ;
        String userNameStr = userNameTextField.getText() ;
        String passwordStr = passwordTextField.getText() ;
        String birthDayStr = birthDayTextField.getText() ;
        String birthMonthStr = birthMonthTextField.getText() ;
        String birthYearStr = birthYearTextField.getText() ;
        //นำข้อมูล String เก็บใน FieldClass และบันทึกลง CSV
        user = new User(firstNameStr, lastNameStr, userNameStr, passwordStr, birthDayStr, birthMonthStr, birthYearStr);

    }
}
