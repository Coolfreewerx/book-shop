package ku.cs.shop.models;

import ku.cs.shop.services.ProvideTypeBookDataSource;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Book {
    private String bookName;
    private String bookShop;
    private String bookAuthor;
    private String bookISBN;
    private String bookType;
    private String bookDetail;
    private String bookPublisher;
    private String bookImg;
    private int bookStock;
    private String bookPage;
    private int leastStock;
    private double bookPrice;
    private LocalDateTime timeOfAddingBook;
    private ArrayList<ProvideTypeBook> typeBookArrayList;

    //เก็บค่าเริ่มต้น
    public Book() {}

    public Book(String bookName, String bookShop, String bookAuthor, String bookISBN, String bookType,
                String bookDetail, String bookPublisher, String bookImg,
                int bookStock, String bookPage, int leastStock, double bookPrice, LocalDateTime timeOfAddingBook,ArrayList<ProvideTypeBook> typeBookArrayList) {
        this.bookName = bookName;
        this.bookShop = bookShop;
        this.bookAuthor = bookAuthor;
        this.bookISBN = bookISBN;
        this.bookType = bookType;
        this.bookDetail = bookDetail;
        this.bookPublisher = bookPublisher;
        this.bookImg = bookImg;
        this.bookStock = bookStock;
        this.bookPage = bookPage;
        this.leastStock = leastStock;
        this.bookPrice = bookPrice;
        this.timeOfAddingBook = timeOfAddingBook;
        this.typeBookArrayList = typeBookArrayList;
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
    public String getBookImg() { return bookImg; }
    public int getBookStock() { return bookStock; }

    public String getBookStatus()
    {
        if (bookStock > 0) return "พร้อมส่ง";
        return "สินค้าหมด";
    };

    public String getBookPage() { return bookPage; }
    public int getLeastStock() { return leastStock; }
    public double getBookPrice() { return bookPrice; }
    public LocalDateTime getTimeOfAddingBook() { return timeOfAddingBook; }
    public ArrayList<ProvideTypeBook> getTypeBookArrayList() { return typeBookArrayList; }

    public void setBookName(String bookName) { this.bookName = bookName; }
    public void setBookShop(String bookShop) { this.bookShop = bookShop; }
    public void setBookAuthor(String bookAuthor) { this.bookAuthor = bookAuthor; }
    public void setBookISBN(String bookISBN) { this.bookISBN = bookISBN; }
    public void setBookType(String bookType) { this.bookType = bookType; }
    public void setBookDetail(String bookDetail) { this.bookDetail = bookDetail; }
    public void setBookPublisher(String bookPublisher) { this.bookPublisher = bookPublisher; }
    public void setBookImg(String bookImg) { this.bookImg = bookImg; }
    public void setBookStock(int bookStock) { this.bookStock = bookStock; }
    public void setLeastStock(int leastStock) { this.leastStock = leastStock; }
    public void setBookPrice(double bookPrice) { this.bookPrice = bookPrice; }
    public void setBookPage(String bookPage) { this.bookPage = bookPage; }
    public void setTimeOfAddingBook(LocalDateTime timeOfAddingBook) { this.timeOfAddingBook = timeOfAddingBook; }
    public void setTypeBookArrayList(ArrayList<ProvideTypeBook> typeBookArrayList) { this.typeBookArrayList = typeBookArrayList; }

    public String getPicturePath() {
        return new File(System.getProperty("user.dir")
                + File.separator
                + "images/book-images"
                + File.separator
                + bookImg).toURI().toString();
    }

    // increase method
    public void increaseStock () { this.setBookStock(this.bookStock + 1); }

    // decrease method
    public void decreaseStock () { this.setBookStock(this.bookStock - 1); }

    public String toCsv(){
        String result = "";
        String subType = "";
        for(int i = 0; i < typeBookArrayList.size(); i++){
            subType +=  "," + typeBookArrayList.get(i).getSubTypeBook();
        }

        result = "\"" + bookName.replace("\"","\"\"") + "\""  + ","
                + "\"" + bookShop.replace("\"","\"\"") + "\"" +  ","
                + "\"" + bookAuthor.replace("\"","\"\"")  + "\"" + ","
                + bookISBN + ","
                + "\"" + bookType.replace("\"","\"\"")  + "\"" + ","
                + "\"" + bookDetail.replace("\"","\"\"")  + "\"" + ","
                + "\"" + bookPublisher.replace("\"","\"\"") + "\"" + ","
                + "\"" + bookImg.replace("\"","\"\"") +  "\"" + ","
                + "\"" + Integer.toString(bookStock).replace(",","\"\"") + "\"" + ","
                + bookPage + ","
                + leastStock + ","
                + bookPrice + ","
                + timeOfAddingBook.toString() + subType + "\n" ;
        return result;
    }




}
