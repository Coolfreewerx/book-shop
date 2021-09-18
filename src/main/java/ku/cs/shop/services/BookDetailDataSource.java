package ku.cs.shop.services;

import java.io.*;
import java.util.ArrayList;

import com.opencsv.CSVReader;
import ku.cs.shop.models.Book;
import ku.cs.shop.models.BookList;

public class BookDetailDataSource implements DataSource<BookList> {
    private String filename;


    public BookDetailDataSource(String filename) { this.filename = filename; }

    public BookList readData()
    {
        BookList bookList = new BookList(); // ไม่ควรใช้ arraylist

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
                String bookImg = data[7].trim();
                int bookStock = Integer.parseInt(data[8].trim());
                String bookPage = data[9].trim();
                int leastStock = Integer.parseInt(data[10].trim());
                double bookPrice = Double.parseDouble(data[11].trim());

                Book bookInformation = new Book(bookName,bookShop,bookAuthor,bookISBN,bookType,bookDetail,bookPublisher,bookImg,bookStock,bookPage,leastStock,bookPrice);
                bookList.addBook(bookInformation);
            }

        } catch (FileNotFoundException e) {
            System.err.println("Cannot read information in file " + filename);
        } catch (IOException e) {
            System.err.println("Error reading from file");
        }

        return bookList;
    }

    public void writeData(BookList bookList){
        String path = filename;
        File file = new File(path);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            buffer.write(bookList.toCSV());

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
