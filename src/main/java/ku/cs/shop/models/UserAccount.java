package ku.cs.shop.models;

public class UserAccount extends Account {

    private String status ;

    public UserAccount(String firstName, String lastName, String userName, String password,
                String birthDay, String birthMonth, String birthYear,
                String imageName) {

        this(firstName, lastName, userName, password, birthDay, birthMonth, birthYear,
                imageName, "null", "null", "null", "ยังไม่ได้สมัครเป็นผู้ขาย", "working" );
    }
    public UserAccount(String firstName, String lastName, String userName, String password,
                       String birthDay, String birthMonth, String birthYear,
                       String imageName, String phone, String sex, String address, String shopName,
                       String status ) {

        super(firstName, lastName, userName, password, birthDay, birthMonth, birthYear,
                imageName, phone, sex, address, shopName );
        this.status = status ;
    }

    public String getStatus() {
        return status ;
    }

    public void setStatus(String status) {
        this.status = status ;
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
                + getStatus();
    }
}
