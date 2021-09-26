package ku.cs.shop.models;

import ku.cs.shop.services.ConditionFilterer;
import ku.cs.shop.services.Filterer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BookInventory {
    private ArrayList<Book> books;
    public BookInventory() {
        books = new ArrayList<>();
    }

    /**
     * ต้องการหาหนังสือที่ปีในพิมพ์ล่าสุด หรือมีชื่ออยู่หลังสุด หรือมีจำนวนหน้ามากที่สุด หรือมีราคามากที่สุด
     * ขึ้นกับว่าใช้ Comparator ใด
     * @param bookComparator callback comparator
     * @return Book object
     */
    public Book max(Comparator<Book> bookComparator) {
        Book maxName = null;
        for (Book book : books) {
            if (maxName == null) {
                maxName = book;
            }
            if (bookComparator.compare(book, maxName) > 0) {
                maxName = book;
            }
        }
        return maxName;
    }

    /**
     * ต้องการเรียงลำดับหนังสือจากชื่อหนังสือ หรือชื่อผู้แต่ง หรือปีที่พิมพ์ หรือจำนวนหน้า หรือราคา จากน้อยไปมากหรือมากไปน้อยก็ได้
     * ขึ้นกับว่าใช้ Comparator ใด
     *
     * @param bookComparator callback comparator
     */
    public void sort(Comparator<Book> bookComparator) {
        Collections.sort(this.books, bookComparator);
    }

    /**
     * ต้องการค้นหารายการหนังสือที่มีปีที่พิมพ์ตั้งแต่ปีที่กำหนดถึงปัจจุบัน หรือชื่อหนังสือมีคำที่กำหนด หรือชื่อผู้แต่งคือชื่อที่กำหนด หรือมีจำนวนหน้าหรือราคาอยู่ในช่วงที่กำหนด
     * ขึ้นกับว่าเรียก Filterer ใด
     * @param filterer callback filterer
     * @return ArrayList ของหนังสือตามเงื่อนไขของ Filterer
     */
    public ArrayList<Book> search(Filterer<BookInventory> filterer) {
        return filterer.filter(this).getBooks();
    }

    public ArrayList<Book> search(ConditionFilterer<Book> filterer) {
        ArrayList<Book> filtered = new ArrayList<>();
        for (Book book : this.books) {
            if (filterer.match(book)) {
                filtered.add(book);
            }
        }
        return filtered;
    }


    public void addBook(Book book) {
        this.books.add(book);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public int count() {
        return this.books.size();
    }

    public String toCsv() {
        String result = "";
        for (Book book : this.books) {
            result += book.toCsv() + "\n";
        }
        return result;
    }

    @Override
    public String toString() {
        return "BookInventory{" +
                "books=" + books +
                '}';
    }
}
