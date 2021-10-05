package ku.cs.shop.models;

import java.io.File;

public class Review extends Account {
    private String bookName;
    private String userName;
    private String imageName ;
    private String comment;

    public Review(String bookName, String userName, String imageName, String comment){
        this.bookName = bookName;
        this.userName = userName;
        this.imageName = imageName;
        this.comment = comment;
    }

    public String getBookName(){ return bookName; }
    public String getUserName(){ return userName; }
    public String getImageName(){ return imageName; }
    public String getComment(){ return comment; }

    public void setBookName(String bookName) { this.bookName = bookName; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setImageName(String imageName) { this.imageName = imageName; }
    public void setComment(String comment) { this.comment = comment; }

    @Override
    public String getImagePath() {
        return new File(System.getProperty("user.dir")
                + File.separator
                + "account-images"
                + File.separator
                + imageName).toURI().toString();
    }

    public String toCsv(){
        return "\"" + getBookName() + "\"" + getUserName() + ",\"" + getImageName() + "," + getComment();
    }
}
