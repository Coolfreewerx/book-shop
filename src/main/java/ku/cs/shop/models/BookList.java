package ku.cs.shop.models;

import ku.cs.shop.services.BookSortByTimeComparator;

import java.lang.reflect.Array;
import java.util.*;

public class BookList {

    private Set<String> bookTypes;
    private Set<String> bookNames;
    private Set<String> bookShops;
    private ArrayList<Book> books;

    public BookList() {
        books = new ArrayList<>();
        bookTypes = new HashSet<>();
        bookNames = new HashSet<>();
        bookShops = new HashSet<>();
    }

    public void addBook(Book book) {
        books.add(book);
        bookTypes.add(book.getBookType());
        bookShops.add(book.getBookShop());
        sort();
    }

    public Book getBook(int index) {
        return books.get(index);
    }

    public ArrayList<Book> getBookByType(String type) {
        if (type.equals("ประเภททั้งหมด")) return books;
        ArrayList<Book> bookByType = new ArrayList<>();
        for(Book book : books) {
            if (book.getBookType().equals(type)) {
                bookByType.add(book);
            }
        }
        return bookByType;
    }

    public void editIndexBookByName(String name, Book newDetailbook) {
        int index = 0;
        for (Book book : this.books){
            if (book.getBookName().equals(name)){
                this.books.set(index, newDetailbook);
                break;
            }
            index++;
        }
    }


    public ArrayList<Book> getBookByShop(String nameShop) {
        ArrayList<Book> bookByShop = new ArrayList<>();
        for (Book book : books) {
            if (book.getBookShop().equals(nameShop)) {
               bookByShop.add(book);
            }
        }
        return bookByShop;
    }

    public ArrayList<Book> getBookByName(String bookName) {
        ArrayList<Book> bookByName = new ArrayList<>();
        for (Book book : books) {
            if (book.getBookName().equals(bookName)) {
                bookByName.add(book);
            }
        }
        return bookByName;
    }

    public int getCountBookByShop(String nameShop) {
        int count = 0;
        ArrayList<Book> bookByShop = new ArrayList<>();
        for (Book book : books) {
            if (book.getBookShop().equals(nameShop)) {
                bookByShop.add(book);
                count++;
            }
        }
        return count;
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

    public void sort(Comparator<Book> bookComparator) {
        Collections.sort(this.books, bookComparator);
    }

    public void sort() {
        BookSortByTimeComparator bookSortByTimeComparator = new BookSortByTimeComparator();
        sort(bookSortByTimeComparator);
    }

    public int getBookListCount(){return this.books.size();}
}
