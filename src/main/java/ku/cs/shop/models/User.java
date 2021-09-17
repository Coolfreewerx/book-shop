package ku.cs.shop.models;

import com.opencsv.CSVReader;

import java.io.*;
import java.io.FileReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class User {

    private String firstName ;
    private String lastName ;
    private String userName ;
    private String password ;
    private String birthDay ;
    private String birthMonth ;
    private String birthYear ;
    private String filename;
    private String imageName ;
    private String phone ;
    private String sex ;
    private String shopName ;
    private static String userLogin ;
    private boolean passwordCheck = false, passwordCondition = false, dataCheck = false, userNameCheck = false ;
    private String userImg;
    //เก็บค่าเริ่มต้น
    public User(){
        this.imageName = "default.png" ;
        this.phone = "null" ;
        this.sex = "null" ;
        this.shopName = "null" ;
    }
    public User(String firstName, String lastName, String userName, String password, String birthDay, String birthMonth, String birthYear, String userImg){
        this.firstName = firstName ;
        this.lastName = lastName ;
        this.userName = userName ;
        this.password = password ;
        this.birthDay = birthDay ;
        this.birthMonth = birthMonth ;
        this.birthYear = birthYear ;
        this.userImg = userImg;
    }
    public User(String filename){
        this.filename = filename;
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
    public String getBirthDay() { return birthDay; }
    public String getBirthMonth() {
        return birthMonth;
    }
    public String getBirthYear() {
        return birthYear;
    }
    public boolean getPasswordCondition() { return  passwordCondition; }
    public boolean getPasswordCheck() { return passwordCheck; }
    public boolean getDataCheck() {return dataCheck; }
    public boolean getUserNameCheck() {return userNameCheck; }
    public String getUserImg() { return userImg; }

    public static String getUserLogin() { return userLogin; }

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
    public void setImageName(String imageName) { this.imageName = imageName; }
    public void setUserImg(String userImg){ this.userImg = userImg; }

    public String checkUserNameCondition(String userName) {
        //ตรวจสอบ username ว่าตรงเงื่อนไขมั้ย
        if (!Pattern.matches("[a-zA-Z0-9]+", userName)) {
            this.userNameCheck = false ;
            return "ชื่อผู้ใช้ไม่ตรงตามรูปแบบที่กำหนด" ;
        }
        else if (checkUserNameHaveUsed(userName)) {
            this.userNameCheck = false ;
            return "ชื่อผู้ใช้นี้ถูกใช้งานไปแล้ว" ;
        }
        else {
            this.userNameCheck = true ;
            return "ชื่อผู้ใช้นี้สามารถใช้งานได้" ; }
    }

    //ตรวจสอบ username ว่าซ้ำมั้ย
    public boolean checkUserNameHaveUsed(String userName) {
        File userData = new File("src/main/java/ku/cs/shop/userData.csv");
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(userData));
            String line;
            while ((line = buffer.readLine()) != null) {
                String[] arr = line.split(","); //อ่าน username
                if (arr[0].equals(userName)) { return true ;}
            }
            buffer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return false ;
    }

    //ตรวจสอบรหัสผ่านตามเงื่อนไข
    public boolean checkPasswordCondition (String password)  {
        if (password.length()<8 || !(Pattern.matches("[a-zA-Z0-9]+", password)&&!Pattern.matches("[a-zA-Z]+", password)&&!Pattern.matches("[0-9]+", password))) {
            this.passwordCondition = false ;
            return false ;
        }
        else {
            this.passwordCondition = true ;
            return true ; }
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

    public Boolean comparePasswordBoolean(String password, String passwordRecheck) {
        if (password.equals(passwordRecheck)) {
            return true;
        }
        else {
            return false;
        }
    }

    //เก็บข้อมูลของ user
    public void writeUserInfo() {
        //นำข้อมูล String เก็บใน FieldClass และบันทึกลง CSV
        File userData = new File("src/main/java/ku/cs/shop/userData.csv");

        FileWriter writer = null;
        try {
            writer = new FileWriter(userData, true);
            writer.write(userName + ","
                    + firstName + ","
                    + lastName + ","
                    + password + ","
                    + birthDay + ","
                    + birthMonth + ","
                    + birthYear + ","
                    + imageName + ","
                    + phone + ","
                    + sex + ","
                    + shopName // 10
                    + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("สมัครไม่สำเร็จ");
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //เก็บรูปภาพ
    public void copyImageToPackage(File image, String imageName) {
        File file = new File("user-images") ;
        Path desPath = FileSystems.getDefault().getPath(file.getAbsolutePath() + "\\" + imageName);
        try {
            Files.copy(image.toPath(), desPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //เช็คการกรอกข้อมูลก่อนสมัคร
    public String dataCheck() {
        // ตรวจสอบว่าทุกช่องมีข้อมูล
        if ((firstName.equals("")||lastName.equals("")||userName.equals("")||password.equals("")||
                birthDay.equals("")||birthMonth.equals("")||birthYear.equals(""))) {
            this.dataCheck = false ;
            return "ข้อมูลไม่ครบถ้วน โปรดตรวจสอบข้อมูลอีกครั้ง";
        }
        else if (!(this.passwordCheck && this.passwordCondition && this.userNameCheck )) {
            this.dataCheck = false ;
            return "ข้อมูลมีข้อผิดพลาดโปรดตรวจสอบข้อมูลอีกครั้ง" ;
        }
        else {
            this.dataCheck = true ;
            return " ";
        }
    }

    //ล็อกอินเข้าสู่ระบบ
    public boolean login(String userName, String password){
        File userData = new File("src/main/java/ku/cs/shop/userData.csv");
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(userData));
            String line;
            while ((line = buffer.readLine()) != null) {
                String[] arr = line.split(","); //อ่าน username
                if (arr[0].equals(userName)) {
                    if (arr[3].equals(password)) {
                        this.userLogin = userName ;
                        return true;
                    }
                }
            }
            buffer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<User> readData(){ //อ่านข้อมูลใน user
        ArrayList<User> usersList = new ArrayList<>();

        try{
            System.out.println(filename);
            FileReader file = new FileReader(filename);

            CSVReader reader = new CSVReader(file);
            String[] data = null;

            while ((data = reader.readNext()) != null){
                String userNameInArrayList = data[0];
                String firstNameInArrayList = data[1];
                String lastNameInArrayList = data[2];
                String passwordInArrayList = data[3];
                String birthDayInArrayList = data[4];
                String birthMonthInArrayList = data[5];
                String birthYearInArrayList = data[6];
                String userImgInList = data[7];

                User detailUser = new User(firstNameInArrayList, lastNameInArrayList, userNameInArrayList, passwordInArrayList, birthDayInArrayList, birthMonthInArrayList, birthYearInArrayList, userImgInList);
                usersList.add(detailUser);
                System.out.println(Arrays.stream(data).toArray());
                //String firstName, String lastName, String userName, String password, String birthDay, String birthMonth, String birthYear, String userImg
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot read information in file " + filename);
        } catch (IOException e) {
            System.err.println("Error reading from file");
        }
        return usersList;
    }
    
    public void writeToEditUserInfo(User user) {
        File userData = new File("src/main/java/ku/cs/shop/userData.csv");
        FileWriter writer = null;
        try {
            writer = new FileWriter(userData, true);
            writer.write(userName + ","
                    + firstName + ","
                    + lastName + ","
                    + password + ","
                    + birthDay + ","
                    + birthMonth + ","
                    + birthYear + ","
                    + imageName + ","
                    + phone + ","
                    + sex + ","
                    + shopName // 10
                    + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("สมัครไม่สำเร็จ");
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String checkShopNameCondition(String shopName) {
        if (checkShopNameHaveUsed(shopName)) { return "** ชื่อร้านค้านี้ถูกใช้งานแล้ว **" ; }
        else { return "** ชื่อร้านค้านี้สามารถใช้งานได้ **" ; }
    }

    public boolean checkShopNameHaveUsed(String shopName) {
        File userData = new File("src/main/java/ku/cs/shop/userData.csv");
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(userData));
            String line;
            while ((line = buffer.readLine()) != null) {
                String[] arr = line.split(",");
                if (arr[10].equals(shopName)) { return true ;}
            }
            buffer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return false ;
    }

    public String getPicturePath() {
        return new File(System.getProperty("user.dir")
                + File.separator
                + "/user_images"
                + File.separator
                + userImg).toURI().toString();
    }
}
