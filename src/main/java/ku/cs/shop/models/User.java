package ku.cs.shop.models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class User {

    private String firstName ;
    private String lastName ;
    private String userName ;
    private String password ;
    private String birthDay ;
    private String birthMonth ;
    private String birthYear ;
    private boolean passwordCheck = false, passwordCondition = false, dataCheck = false;

    //เก็บค่าเริ่มต้น
    public User(){}
    public User(String firstName, String lastName, String userName, String password, String birthDay, String birthMonth, String birthYear){
        this.firstName = firstName ;
        this.lastName = lastName ;
        this.userName = userName ;
        this.password = password ;
        this.birthDay = birthDay ;
        this.birthMonth = birthMonth ;
        this.birthYear = birthYear ;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
    public String getBirthDay() {
        return birthDay;
    }
    public String getBirthMonth() {
        return birthMonth;
    }
    public String getBirthYear() {
        return birthYear;
    }
    public boolean getDataCheck() {return dataCheck; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setUserName(String userName) { this.userName = userName; }
    public void setBirthDay(String birthDay) { this.birthDay = birthDay; }
    public void setBirthMonth(String birthMonth) { this.birthMonth = birthMonth; }
    public void setBirthYear(String birthYear) { this.birthYear = birthYear; }
    public void setPassword(String password) {
        this.password = password;
    }

    //    public void checkUserNameCondition() {
//        String userNameStr = userNameTextField.getText() ;
//        //ตรวจสอบ username ว่าตรงเงื่อนไขมั้ย
//
//        //ตรวจสอบ username ว่าซ้ำมั้ย
//    }

    //ตรวจสอบรหัสผ่านตามเงื่อนไข
    public boolean checkPasswordCondition (String password)  {
        if (password.length()<8 ||!(Pattern.matches("[a-zA-Z0-9]+", password))) {
            this.passwordCondition = false ;
            return false ;
        }
        else {
            this.passwordCondition = true ;
            return true ;
        }
    }

    //ตรวจสอบการยืนยันรหัสผ่าน
    public String comparePassword(String password, String passwordRecheck) {
        if (password.equals(passwordRecheck)) {
            this.passwordCheck = true ;
            return "";
        }
        else {
            this.passwordCheck = false ;
            return "รหัสผ่านไม่ตรงกัน โปรดตรวจสอบรหัสผ่าน";
        }

    }

    //เก็บข้อมูลของ user
    public void writeUserInfo() {
        //นำข้อมูล String เก็บใน FieldClass และบันทึกลง CSV
        File userData = new File("src/main/java/ku/cs/shop/userData.csv");

        FileWriter writer;
        try {
            writer = new FileWriter(userData, true);
            writer.write(userName + "," + firstName + "," + lastName + "," + password + "," +
                    birthDay + "," + birthMonth + "," + birthYear + "\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("สมัครไม่สำเร็จ");
        }
    }

    //เช็คการกรอกข้อมูลก่อนสมัคร
    public String dataCheck() {

        // ตรวจสอบว่าทุกช่องมีข้อมูล
        if ((firstName.equals("")||lastName.equals("")||userName.equals("")||!(passwordCheck)||
                birthDay.equals("")||birthMonth.equals("")||birthYear.equals(""))) {
            this.dataCheck = false ;
            return "ข้อมูลไม่ครบถ้วน โปรดตรวจสอบข้อมูลอีกครั้ง";
        }
        else if (!(this.passwordCheck && this.passwordCondition /*&&usernameCheck*/)) {
            this.dataCheck = false ;
            return "ข้อมูลมีข้อผิดพลาดโปรดตรวจสอบข้อมูลอีกครั้ง" ;
        }
        else {
            this.dataCheck = true ;
            return " ";
        }
    }
}
