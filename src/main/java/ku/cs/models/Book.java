package ku.cs.models;

public class Book {
    private String bookName;
    private String bookShop;
    private String bookAuthor;
    private String bookISBN;
    private String bookType;
    private String bookDetail;
    private String bookPublisher;
    private String bookStatus;
    private int bookStock;
    private int newAmountBookStock;
    private int bookPage;
    private int leastStock;
    private double bookPrice;

    //set default value
    public Book(String bookName, String bookShop, String bookAuthor, String bookISBN, String bookType, String bookDetail, String bookPublisher, String bookStatus, int bookStock, int newAmountBookStock, int bookPage, int leastStock, double bookPrice) {
        this.bookName = bookName;
        this.bookShop = bookShop;
        this.bookAuthor = bookAuthor;
        this.bookISBN = bookISBN;
        this.bookType = bookType;
        this.bookDetail = bookDetail;
        this.bookPublisher = bookPublisher;
        this.bookStatus = bookStatus;
        this.bookStock = bookStock;
        this.newAmountBookStock = newAmountBookStock;
        this.bookPage = bookPage;
        this.leastStock = leastStock;
        this.bookPrice = bookPrice;
    }

    // show data
    public String getBookName() {
        return bookName;
    }
    public String getBookShop() {
        return bookShop;
    }
    public String getBookAuthor() {
        return bookAuthor;
    }
    public String getBookISBN() {
        return bookISBN;
    }
    public String getBookType() {
        return bookType;
    }
    public String getBookDetail() {
        return bookDetail;
    }
    public String getBookPublisher() {
        return bookPublisher;
    }
    public String getBookStatus() {
        return bookStatus;
    }
    public int getBookPage() {
        return bookPage;
    }
    public int getLeastStock() {
        return leastStock;
    }
    public double getBookPrice() {
        return bookPrice;
    }
    public int getBookStock() {
        return bookStock;
    }
    public int getNewAmountBookStock() {
        return newAmountBookStock;
    }

    // set new value
    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
    public void setBookDetail(String bookDetail) {
        this.bookDetail = bookDetail;
    }
    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public void setBookShop(String bookShop) {
        this.bookShop = bookShop;
    }
    public void setBookType(String bookType) {
        this.bookType = bookType;
    }
    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }
    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }
    public void setBookPage(int bookPage) {
        this.bookPage = bookPage;
    }
    public void setLeastStock(int leastStock) {
        this.leastStock = leastStock;
    }
    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }
    public void setBookStock(int bookStock) {
        this.bookStock = bookStock;
    }
    public void setNewAmountBookStock(int newAmountBookStock) {
        this.newAmountBookStock = newAmountBookStock;
    }

    // increase method
    void increaseStock (int newAmountBookStock) {
        bookStock += newAmountBookStock;
    }

    void decreaseStock (int newAmountBookStock) {
        bookStock -= newAmountBookStock;
    }
}
