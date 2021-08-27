package ku.cs.shop.models;

import java.util.ArrayList;

public class BookList {
    private ArrayList<Book> books;

    public BookList() { // build new instance of ArrayList
        books = new ArrayList<>();
    }

    public ArrayList<Book> getAllBooks() { return books; }
}
