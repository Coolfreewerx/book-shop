package ku.cs.shop.services;

import java.io.*;
import java.util.ArrayList;

import com.opencsv.CSVReader;
import ku.cs.shop.models.Book;

public class BookDetailDataSource {
    private String filename;


    public BookDetailDataSource(String filename) { this.filename = filename; }

    public ArrayList<Book> readData()
    {
        ArrayList<Book> bookList = new ArrayList<>();

        try
        {
            FileReader file = new FileReader(filename);
            CSVReader reader = new CSVReader(file);
            String[] data = null;

            while ((data = reader.readNext()) != null)
            {
                String bookName = data[0].trim();
                String bookShop = data[1].trim();
                String bookAuthor = data[2].trim();
                String bookISBN = data[3].trim();
                String bookType = data[4].trim();
                String bookDetail = data[5].trim();
                String bookPublisher = data[6].trim();
                String bookStatus = data[7].trim();
                String bookImg = data[8].trim();
                int bookStock = Integer.parseInt(data[9].trim());
                String bookPage = data[10].trim();
                int leastStock = Integer.parseInt(data[11].trim());
                double bookPrice = Double.parseDouble(data[12].trim());

                Book bookInformation = new Book(bookName,bookShop,bookAuthor,bookISBN,bookType,bookDetail,bookPublisher,bookStatus,bookImg,bookStock,bookPage,leastStock,bookPrice);
                bookList.add(bookInformation);
            }

        } catch (FileNotFoundException e) {
            System.err.println("Cannot read information in file " + filename);
        } catch (IOException e) {
            System.err.println("Error reading from file");
        }

        return bookList;
    }

    public void writeData(ArrayList<Book> bookArrayList){
        String path = filename;
        File file = new File(path);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);


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
