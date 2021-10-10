package ku.cs.shop.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ReviewsList {
    private ArrayList<Reviews> reviews;
    private Set<String> bookRating;

    public ReviewsList(){
        reviews = new ArrayList<>();
        bookRating = new HashSet<>();
    }

    public void addReviews(Reviews reviews){
        this.reviews.add(reviews);
        this.bookRating.add(reviews.getBookRating() + "");
    }

    public ArrayList<Reviews> getReviewsByBookNameAndShopName(String bookName, String bookShop) {
        ArrayList<Reviews> reviewsByBookNameAndBookShop = new ArrayList<>();
        for(Reviews reviews : reviews){
            if(reviews.getBookName().equals(bookName)){
                if(reviews.getBookShop().equals(bookShop)){
                    reviewsByBookNameAndBookShop.add(reviews);
                }
            }
        }
        return reviewsByBookNameAndBookShop;
    }

    public int getCountBookByNameAndShop(String bookName, String bookShop) {
        int count = 0;
        ArrayList<Reviews> countBookByNameAndShop = new ArrayList<>();
        for(Reviews reviews : reviews){
            if(reviews.getBookName().equals(bookName)){
                if(reviews.getBookShop().equals(bookShop)){
                    countBookByNameAndShop.add(reviews);
                    count++;
                }
            }
        }
        return count;
    }

//    public Set<String> getBookRating(){ return bookRating; }

    public int sumOfRatingByBookNameAndBookShop(String bookName, String bookShop, int bookRating){
        int sum = 0;
        int count = 0;
        ArrayList<Reviews> sumOfRatingByBookNameAndBookShop = new ArrayList<>();
        for(Reviews reviews : reviews){
            if(reviews.getBookName().equals(bookName)){
                if(reviews.getBookShop().equals(bookShop)){
                    sumOfRatingByBookNameAndBookShop.add(reviews);
                    sum = sum + bookRating;
                }
            }
        }
        return sum;
    }

//    public double averageRating(String bookName, String bookShop, int bookRating){
//        double averageRating = 0;
//        averageRating =
//        return 0;
//    }

    public String toCsv() {
        String result = "" ;
        for (Reviews reviews : this.reviews) {
            result += reviews.toCsv() + "\n";
        }
        return result ;
    }
}
