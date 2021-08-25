package ku.cs.shop.models;

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
    private String bookPage;
    private int leastStock;
    private double bookPrice;

    //set default value
    public Book() {
    }

    public Book(String bookName, String bookShop, String bookAuthor, String bookISBN, String bookType,
                String bookDetail, String bookPublisher, String bookStatus, int bookStock,
                String bookPage, int leastStock, double bookPrice) {
        this.bookName = bookName;
        this.bookShop = bookShop;
        this.bookAuthor = bookAuthor;
        this.bookISBN = bookISBN;
        this.bookType = bookType;
        this.bookDetail = bookDetail;
        this.bookPublisher = bookPublisher;
        this.bookStatus = bookStatus;
        this.bookStock = bookStock;
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

    public int getLeastStock() {
        return leastStock;
    }

    public String getBookPage() {
        return bookPage;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public int getBookStock() {
        return bookStock;
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

    public void setLeastStock(int leastStock) {
        this.leastStock = leastStock;
    }

    public void setBookPage(String bookPage) {
        this.bookPage = bookPage;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public void setBookStock(int bookStock) {
        this.bookStock = bookStock;
    }

    // increase method
    public void increaseStock (int newAmountBookStock)
    {
        setBookStock(this.bookStock + newAmountBookStock);
    }

    // decrease method
    public void decreaseStock (int newAmountBookStock) { setBookStock(this.bookStock - newAmountBookStock); }

    public void editStock (int newAmountBookStock) { setBookStock(this.bookStock + newAmountBookStock); }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", bookShop='" + bookShop + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookISBN='" + bookISBN + '\'' +
                ", bookType='" + bookType + '\'' +
                ", bookDetail='" + bookDetail + '\'' +
                ", bookPublisher='" + bookPublisher + '\'' +
                ", bookStatus='" + bookStatus + '\'' +
                ", bookStock=" + bookStock +
                ", bookPage=" + bookPage +
                ", leastStock=" + leastStock +
                ", bookPrice=" + bookPrice +
                '}';
    }
}
