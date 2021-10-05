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
        for(Review review: reviews){
            if(review.getBookName().equals(bookName)){

            }
        }
        return reviews;
    }

    public String toCsv() {
        String result = "" ;
        for (Review review: this.reviews) {
            result += review.toCsv() + "\n";
        }
        return result ;
    }
}
