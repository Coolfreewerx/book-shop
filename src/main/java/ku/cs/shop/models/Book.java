package ku.cs.shop.models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Book {
    private String bookName;
    private String bookShop;
    private String bookAuthor;
    private String bookISBN;
    private String bookType;
    private String bookDetail;
    private String bookPublisher;
    private String bookStatus;
    private String bookImg;
    private int bookStock;
    private String bookPage;
    private int leastStock;
    private double bookPrice;

    //เก็บค่าเริ่มต้น
    public Book() {}

    public Book(String bookName, String bookShop, String bookAuthor, String bookISBN, String bookType, String bookDetail, String bookPublisher, String bookStatus, String bookImg, int bookStock, String bookPage, int leastStock, double bookPrice) {
        this.bookName = bookName;
        this.bookShop = bookShop;
        this.bookAuthor = bookAuthor;
        this.bookISBN = bookISBN;
        this.bookType = bookType;
        this.bookDetail = bookDetail;
        this.bookPublisher = bookPublisher;
        this.bookStatus = bookStatus;
        this.bookImg = bookImg;
        this.bookStock = bookStock;
        this.bookPage = bookPage;
        this.leastStock = leastStock;
        this.bookPrice = bookPrice;
    }

    public Book(String bookName, double bookPrice, String bookShop) {
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.bookShop = bookShop;
    }

    public String getBookName() { return bookName; }
    public String getBookShop() { return bookShop; }
    public String getBookAuthor() { return bookAuthor; }
    public String getBookISBN() { return bookISBN; }
    public String getBookType() { return bookType; }
    public String getBookDetail() { return bookDetail; }
    public String getBookPublisher() { return bookPublisher; }
    public String getBookStatus() { return bookStatus; }
    public String getBookImg() { return bookImg; }
    public int getBookStock() { return bookStock; }
    public String getBookPage() { return bookPage; }
    public int getLeastStock() { return leastStock; }
    public double getBookPrice() { return bookPrice; }

    public void setBookName(String bookName) { this.bookName = bookName; }
    public void setBookShop(String bookShop) { this.bookShop = bookShop; }
    public void setBookAuthor(String bookAuthor) { this.bookAuthor = bookAuthor; }
    public void setBookISBN(String bookISBN) { this.bookISBN = bookISBN; }
    public void setBookType(String bookType) { this.bookType = bookType; }
    public void setBookDetail(String bookDetail) { this.bookDetail = bookDetail; }
    public void setBookPublisher(String bookPublisher) { this.bookPublisher = bookPublisher; }
    public void setBookStatus(String bookStatus) { this.bookStatus = bookStatus; }
    public void setBookImg(String bookImg) { this.bookImg = bookImg; }
    public void setBookStock(int bookStock) { this.bookStock = bookStock; }
    public void setLeastStock(int leastStock) { this.leastStock = leastStock; }
    public void setBookPrice(double bookPrice) { this.bookPrice = bookPrice; }

    //เก็บข้อมูลของ book
    public void writeBookInfo() {
        //นำข้อมูลของหนังสือ เก็บใน FieldClass และบันทึกลง CSV
        File bookDetail = new File("src/main/java/ku/cs/shop/bookDetail.csv");

        FileWriter writer;
        try {
            writer = new FileWriter(bookDetail, true);
            writer.write(bookName + "," + bookShop + "," + bookAuthor + "," + bookISBN + "," +
                    bookType + "," + bookDetail + "," + bookPublisher + "," + bookStatus + "," +
                    bookImg + "," + bookStock + "," + leastStock + "," + bookPrice + "\r\n");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("สมัครไม่สำเร็จ");
        }
    }

    public String getPicturePath() {
        return new File(System.getProperty("user.dir")
                + File.separator
                + "images"
                + File.separator
                + bookImg).toURI().toString();
    }



    // increase method
    public void increaseStock (int newAmountBookStock)
    {
        setBookStock(this.bookStock + newAmountBookStock);
    }

    // decrease method
    public void decreaseStock (int newAmountBookStock) { setBookStock(this.bookStock - newAmountBookStock); }

    // edit method
    public void editStock (int newAmountBookStock) { setBookStock(this.bookStock + newAmountBookStock); }



}
