package ku.cs.shop.models;

import ku.cs.shop.services.BookInventoryFileDataSource;
import ku.cs.shop.services.DataSource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookInventoryTest {

    @Test
    void testMaxPrice() {
        DataSource<BookInventory> dataSource;
        dataSource = new BookInventoryFileDataSource();
        BookInventory bookInventory = dataSource.readData();

        Comparator<Book> comparator = new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                if (o1.getBookPrice() > o2.getBookPrice()) return 1;
                if (o1.getBookPrice() < o2.getBookPrice()) return -1;
                return 0;
            }
        };
        Book name = bookInventory.max(comparator);
        assertEquals(436.05, name.getBookPrice());
    }

    @Test
    @DisplayName("ชื่อหนังสือที่ราคาแพงที่สุด")
    void testNameOfMaxPrice() {
        DataSource<BookInventory> dataSource;
        dataSource = new BookInventoryFileDataSource();
        BookInventory bookInventory = dataSource.readData();

        Comparator<Book> comparator = new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                if (o1.getBookPrice() > o2.getBookPrice()) return 1;
                if (o1.getBookPrice() < o2.getBookPrice()) return -1;
                return 0;
            }
        };
        Book name = bookInventory.max(comparator);
        assertEquals("สตรีต้องมีสมอง", name.getBookName());
    }

}