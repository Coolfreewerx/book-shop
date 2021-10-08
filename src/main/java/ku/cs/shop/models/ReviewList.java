package ku.cs.shop.models;

import java.util.ArrayList;

public class ReviewList {
    private ArrayList<Review> reviews;

    public ReviewList(){
        reviews = new ArrayList<>();
    }

    public void addReviews(Review review){
        reviews.add(review);
    }

    public ArrayList<Review> getReviewsByBookName(String bookName) {
        ArrayList<Review> reviewsByBookName = new ArrayList<>();
        for(Review review: reviews){
            if(review.getBookName().equals(bookName)){
                reviewsByBookName.add(review);
            }
        }
        return reviewsByBookName;
    }

    public int getCountBookByNameAndShop(String bookName) {
        int count = 0;
        ArrayList<Review> reviewsByBookName = new ArrayList<>();
        for(Review review: reviews){
            if(review.getBookName().equals(bookName)){
                reviewsByBookName.add(review);
                count++;
            }
        }
        return count;
    }

    public String toCsv() {
        String result = "" ;
        for (Review review: this.reviews) {
            result += review.toCsv() + "\n";
        }
        return result ;
    }
}
