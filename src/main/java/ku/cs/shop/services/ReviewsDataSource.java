package ku.cs.shop.services;

import com.opencsv.CSVReader;
import ku.cs.shop.models.Review;
import ku.cs.shop.models.ReviewList;

import java.io.*;

public class ReviewsDataSource implements DataSource<ReviewList> {

    private String filename ;

    public ReviewsDataSource(String filename){
        this.filename = filename;
    }

    @Override
    public ReviewList readData() {
        ReviewList reviewsList = new ReviewList();

        try{
            FileReader file = new FileReader(filename);
            CSVReader reader = new CSVReader(file);
            String[] data = null;

            while ((data = reader.readNext()) != null) {
                String bookName = data[0].trim();
                String bookShop = data[1].trim();
                String userName = data[2].trim();
                String imageName = data[3].trim();
                String comment = data[4].trim();
                int bookRating =  Integer.parseInt(data[5].trim());

                Review review = new Review(bookName, bookShop, userName, imageName, comment, bookRating);
                reviewsList.addReviews(review);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return reviewsList;
    }

    @Override
    public void writeData(ReviewList reviewsList) {
        String path = filename;
        File file = new File(path);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            buffer.write(reviewsList.toCsv());

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                buffer.close();
                writer.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
