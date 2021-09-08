package ku.cs.shop.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class Seller extends Book {

    private String addressShop;
    private String mailShop;
    private String telShop;
    private String bankShopAccount;
    private boolean bookShopCheck;

    public Seller(){};
    public Seller(String bookName, String bookShop, String bookAuthor, String bookISBN, String bookType, String bookDetail, String bookPublisher, String bookStatus, String bookImg, int bookStock, String bookPage, int leastStock, double bookPrice)
    { super(bookName,bookShop,bookAuthor,bookISBN, bookType, bookDetail, bookPublisher, bookStatus, bookImg, bookStock, bookPage, leastStock,bookPrice); }

    public String getAddressShop() { return addressShop; }
    public String getMailShop() { return mailShop; }
    public String getTelShop() { return telShop; }
    public String getBankShopAccount() { return bankShopAccount; }

    public void setAddressShop(String addressShop) { this.addressShop = addressShop; }
    public void setMailShop(String mailShop) { this.mailShop = mailShop; }
    public void setTelShop(String telShop) { this.telShop = telShop; }
    public void setBankShopAccount(String bankShopAccount) { this.bankShopAccount = bankShopAccount; }

    public String checkBookShop(String bookshop){
        if (checkBookShopHaveUsed(bookshop)) {
            this.bookShopCheck = false ;
            return "ชื่อร้านค้านี้ถูกใช้งานไปแล้ว" ;
        }
        else {
            this.bookShopCheck = true ;
            return "ชื่อร้านค้านี้สามารถใช้งานได้" ; }
    }
    public boolean checkBookShopHaveUsed(String bookshop){
        File bookData = new File("src/main/java/ku/cs/shop/bookDetail.csv");
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(bookData));
            String line;
            while ((line = buffer.readLine()) != null) {
                String[] arr = line.split(",");
                if (arr[1].equals(bookshop)) { return true ;}
            }
            buffer.close();
        } catch (IOException e){ e.printStackTrace(); }
        return false ;
    }


}
