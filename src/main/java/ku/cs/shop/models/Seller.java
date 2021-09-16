package ku.cs.shop.models;

import java.io.*;
import java.util.regex.Pattern;

public class Seller{

    private boolean bookShopCheck;
    private Book book;
    public Seller() { }


    public String isBookShopNameCanUsed(String bookshop) {
        if (isNameBookShopHaveUsed(bookshop)) {
            this.bookShopCheck = false;
            return "ชื่อร้านค้านี้ถูกใช้งานไปแล้ว";
        } else {
            this.bookShopCheck = true;
            return "ชื่อร้านค้านี้สามารถใช้งานได้";
        }
    }

    public boolean isNameBookShopHaveUsed(String bookshop) {
        File bookData = new File("src/main/java/ku/cs/shop/bookDetail.csv");
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(bookData));
            String line;
            while ((line = buffer.readLine()) != null) {
                String[] arr = line.split(",");
                if (arr[1].equals(bookshop)) {
                    return true;
                }
            }
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isBookISBNCorrect(String bookISBN) {
        if (bookISBN.length() == 10) {
            return true;
        } else {
            return false;
        }
    }

    public String checkBookISBNCorrect(String bookISBN) {
        if (isBookISBNCorrect(bookISBN)) {
            return "";
        } else {
            return "รหัส ISBN นี้ไม่ถูกต้อง";
        }
    }

    public boolean isNumber(String num) {
        if (Pattern.matches("[0-9]+", num)) {
            return true;
        } else {
            return false;
        }
    }

    public String checkNumber(String num) {
        if (isNumber(num)) {
            return "";
        } else {
            return "ข้อมูลผิดพลาด กรูณาใส่ตัวเลข 0 - 9 ";
        }
    }

    public boolean getDataCheck(String bookName, String bookShop, String bookAuthor, String bookISBN, String bookType, String bookDetail,
                                String bookPublisher, String bookStatus, String bookImg, String bookStock, String bookPage, String leastStock, String bookPrice) {
        if (bookName.equals("") || bookShop.equals("") || bookAuthor.equals("") || bookISBN.equals("") || bookType.equals("") || bookDetail.equals("") ||
                bookPublisher.equals("") || bookStatus.equals("") || bookImg.equals("") || bookStock.equals("") || bookPage.equals("") || leastStock.equals("") || bookPrice.equals("")) {
            return false;
        } else
            return true;
    }

    public void writeSeller(String bookName, String bookShop, String bookAuthor, String bookISBN, String bookType, String bookDetail,
                            String bookPublisher, String bookStatus, String bookImg, String bookStock, String bookPage, String leastStock, String bookPrice){
        File bookDetailData = new File("src/main/java/ku/cs/shop/bookDetail.csv");
        FileWriter writer;
        try {
            writer = new FileWriter(bookDetailData, true);
            writer.write(bookName + "," + bookShop + "," + bookAuthor + "," + bookISBN + "," + bookType + "," + bookDetail + "," +
                    bookPublisher + "," +  bookStatus + "," + bookImg + "," +  bookStock + "," + bookPage + "," +  leastStock + "," + bookPrice + "\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ลงข้อมูลสินค้าไม่สำเร็จ");
        }
    }


}
