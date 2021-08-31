package ku.cs.shop.controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ku.cs.shop.models.Book;
import ku.cs.shop.models.BookList;

public class BookDetailDataSource {
    private String filename;


    public BookDetailDataSource(String filename) { this.filename = filename; }
    public ArrayList<Book> readData()
    {
        ArrayList<Book> bookInformations = new ArrayList<>();

        try
        {
            FileReader file = new FileReader(filename);
            BufferedReader buffer = new BufferedReader(file);
            String line = null;

            while ((line = buffer.readLine()) != null)
            {
                String[] data = line.split(",");
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
                bookInformations.add(bookInformation);
            }

        } catch (FileNotFoundException e) {
            System.err.println("Cannot read information in file " + filename);
        } catch (IOException e) {
            System.err.println("Error reading from file");
        }

        return bookInformations;
    }

}
