package ku.cs.shop.models;

import java.time.LocalDateTime;

public class UserAccount extends Account {

    private String status ;
    private LocalDateTime loginTime ;

    public UserAccount(String firstName, String lastName, String userName, String password,
                String birthDay, String birthMonth, String birthYear,
                String imageName) {

        this(firstName, lastName, userName, password, birthDay, birthMonth, birthYear,
                imageName, "null", "null", "null", "ยังไม่ได้สมัครเป็นผู้ขาย", "working" , LocalDateTime.now() );
    }
    public UserAccount(String firstName, String lastName, String userName, String password,
                       String birthDay, String birthMonth, String birthYear,
                       String imageName, String phone, String sex, String address, String shopName,
                       String status, LocalDateTime loginTime ) {

        super(firstName, lastName, userName, password, birthDay, birthMonth, birthYear,
                imageName, phone, sex, address, shopName );
        this.status = status ;
        this.loginTime = loginTime ;
    }

    public String getStatus() {
        return status ;
    }
    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setStatus(String status) {
        this.status = status ;
    }
    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toCsv() {
        return "User" + ","
                + getUserName() + ",\""
                + getFirstName().replace("\"", "\"\"") + "\",\""
                + getLastName().replace("\"", "\"\"") + "\","
                + getPassword() + ","
                + getBirthDay() + ","
                + getBirthMonth() + ","
                + getBirthYear() + ","
                + getImageName() + ","
                + getPhone() + ","
                + getSex() + ",\""
                + getAddress().replace("\"", "\"\"") + "\","
                + getShopName() + ","
                + getStatus() + ","
                + getLoginTime().toString() ;
    }
}
