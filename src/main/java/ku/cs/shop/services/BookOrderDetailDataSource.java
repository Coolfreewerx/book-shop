package ku.cs.shop.services;

import ku.cs.shop.models.Book;
import ku.cs.shop.models.BookList;

public class BookOrderDetailDataSource implements DataSource<Book>{
    private String filename;
    public BookOrderDetailDataSource(){}

    public BookOrderDetailDataSource(String filename) {this.filename = filename; }

    @Override
    public Book readData() {
        BookList bookList = new BookList();
        return null;
    }

    @Override
    public void writeData(Book book) {

    }
}
