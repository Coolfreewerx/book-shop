package ku.cs.shop.models;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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
        if (bookISBN.length() == 10 && Pattern.matches("[0-9]+",bookISBN)) {
            return true;
        } else {
            return false;
        }
    }

    public String checkBookISBNCorrect(String bookISBN) {
        if (isBookISBNCorrect(bookISBN)) {
            return "";
        } else {
            return "กรุณากรอกให้ครบ 10 หลัก รหัส ISBN นี้ไม่ถูกต้อง ";
        }
    }

    public boolean isNumber(String num) {
        double number = Double.parseDouble(num);
        if (number >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public String checkNumber(String num) {
        if (isNumber(num)) {
            return "";
        } else {
            return "ข้อมูลผิดพลาด กรูณาใส่ตัวเลขจำนวนเต็มบวก";
        }
    }

    public boolean getDataCheck(Book book) {
        if (book.getBookName().equals("") || book.getBookShop().equals("") || book.getBookAuthor().equals("") || book.getBookISBN().equals("") || book.getBookType().equals("") || book.getBookDetail().equals("") ||
                book.getBookPublisher().equals("") || book.getBookStatus().equals("") || book.getBookImg().equals("") || (book.getBookStock() == -1) || book.getBookPage().equals("") || (book.getLeastStock() == -1) || (book.getBookPrice() == -1) ){
            return false;
        } else{ return true;}
    }

    public static void copyImageToPackage(File image, String imageName) {
        File file = new File("images") ;
        Path desPath = FileSystems.getDefault().getPath(file.getAbsolutePath() + "\\" + imageName);
        try {
            Files.copy(image.toPath(), desPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
