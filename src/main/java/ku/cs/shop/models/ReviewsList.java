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
    }

    public ArrayList<Reviews> getReviewsByBookName(String bookName) {
        ArrayList<Reviews> reviewsByBookName = new ArrayList<>();
        for(Reviews reviews : this.reviews){
            if(reviews.getBookName().equals(bookName)){
                reviewsByBookName.add(reviews);
            }
        }
        return reviewsByBookName;
    }

    public int getCountBookByNameAndShop(String bookName) {
        int count = 0;
        ArrayList<Reviews> reviewsByBookName = new ArrayList<>();
        for(Reviews reviews : this.reviews){
            if(reviews.getBookName().equals(bookName)){
                reviewsByBookName.add(reviews);
                count++;
            }
        }
        return count;
    }

    public String toCsv() {
        String result = "" ;
        for (Reviews reviews : this.reviews) {
            result += reviews.toCsv() + "\n";
        }
        return result ;
    }
}
