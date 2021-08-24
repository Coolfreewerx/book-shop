package ku.cs.shop.models;

public class Seller extends Book {
    Book book = new Book();

    // increase method
    public void increaseStock (int newAmountBookStock)
    {
        book.setBookStock(book.getBookStock()+newAmountBookStock);
    }

    // decrease method
    public void decreaseStock (int newAmountBookStock) {
        book.setBookStock(book.getBookStock()-newAmountBookStock);
    }

    public void editStock (int newAmountBookStock) {
        book.setBookStock(book.getBookStock()+newAmountBookStock);
    }
}
