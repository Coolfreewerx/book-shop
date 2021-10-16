package ku.cs.shop.models;

import ku.cs.shop.services.ReviewsSortByTimeComparator;
import java.util.*;

public class ReviewsList {
    private ArrayList<Reviews> reviews;

    public ReviewsList(){
        reviews = new ArrayList<>();
    }

    public void addReviews(Reviews reviews){
        this.reviews.add(reviews);
        sort();
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

    public double averageRating(String bookName, String bookShop){
        double averageRating = 0;
        int count = 0;
       for(Reviews reviews : reviews){
           if(reviews.getBookName().equals(bookName) && reviews.getBookShop().equals(bookShop)){
               averageRating += reviews.getBookRating();
               count++;
           }
       }
       return averageRating/count;
    }

    public void sort(Comparator<Reviews> reviewsComparator) {
        Collections.sort(this.reviews, reviewsComparator);
    }

    public void sort() {
        ReviewsSortByTimeComparator reviewsSortByTimeComparator = new ReviewsSortByTimeComparator();
        sort(reviewsSortByTimeComparator);
    }

    public String toCsv() {
        String result = "" ;
        for (Reviews reviews : this.reviews) {
            result += reviews.toCsv() + "\n";
        }
        return result ;
    }
}
