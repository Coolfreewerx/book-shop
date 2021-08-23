package ku.cs.models;

public class User {

    private String firstName ;
    private String lastName ;
    private String userName ;
    private String password ;
    private String birthDay ;
    private String birthMonth ;
    private String birthYear ;

    //เก็บค่าเริ่มต้น
    public User(String firstName, String lastName, String userName, String password, String birthDay, String birthMonth, String birthYear){
        this.firstName = firstName ;
        this.lastName = lastName ;
        this.userName = userName ;
        this.password = password ;
        this.birthDay = birthDay ;
        this.birthMonth = birthMonth ;
        this.birthYear = birthYear ;
    }

    //สำหรับส่งค่าแสดงผล
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

    //สำหรับแก้ไขค่า ไม่สามารถแก้ไข userName และส่วนของวันเกิด
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
