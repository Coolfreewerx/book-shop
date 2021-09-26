package ku.cs.shop.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BookList {

    private Set<String> bookTypes;
    private Set<String> bookNames;
    private ArrayList<Book> books;

    public BookList() {
        books = new ArrayList<>();
        bookTypes = new HashSet<>(); // hashset implement set
        bookNames = new HashSet<>();
    }

    public void addBook(Book book) {
        books.add(book);
        bookTypes.add(book.getBookType());
    }

    public Book getBook(int index) {
        return books.get(index);
    }

    public ArrayList<Book> getBookByType(String type) {
        ArrayList<Book> bookByType = new ArrayList<>();
        for(Book book : books) {
            if (book.getBookType().equals(type)) {
                bookByType.add(book);
            }
        }
        return bookByType;
    }

    public ArrayList<Book> getBookByName(String name) {
        ArrayList<Book> bookByName = new ArrayList<>();
        for (Book book : books){
            if (book.getBookName().equals(name)){
                bookByName.add(book);
            }
        }
        return bookByName;
    }

    public void editIndexBookByName(String name, Book newDetailbook) {
        int index = 0;
        for (Book book : books){
            if (book.getBookName().equals(name)){
                this.books.set(index, newDetailbook);
                break;
            }
            index++;
        }
    }

    public Set<String> getBookType() {
        return bookTypes;
    }

    public String toCSV() {
        String csv = "" ;
        for(Book book : books) {
            csv += book.toCsv();
        }
        return csv;
    }

    public int getBookListCount(){return this.books.size();}
}
