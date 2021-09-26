package ku.cs.shop.services;

import ku.cs.shop.models.Book;

public class BookFromPriceConditionFillterer
        implements ConditionFilterer<Book> {

        private int fromPrice;


    public BookFromPriceConditionFillterer(int fromPrice) {
        this.fromPrice = fromPrice;
    }

    @Override
    public boolean match(Book book) { return book.getBookPrice() >= fromPrice; }
}
