package ku.cs.shop.services;

import ku.cs.shop.models.Book;
import ku.cs.shop.models.BookInventory;

import java.io.*;

public class BookInventoryFileDataSource implements DataSource<BookInventory> {

    private String directoryName;
    private String filename;

    public BookInventoryFileDataSource() {
        this("csv-data", "bookDetail.csv");
    }

    public BookInventoryFileDataSource(String directoryName, String filename) {
        this.directoryName = directoryName;
        this.filename = filename;
        initialFileIfNotExist();
    }

    private void initialFileIfNotExist() {
        File file = new File(directoryName);
        if (!file.exists()) {
            file.mkdir();
        }

        String path = directoryName + File.separator + filename;
        file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public BookInventory readData() {
        BookInventory inventory = new BookInventory();

        String path = directoryName + File.separator + filename;
        File file = new File(path);

        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            while ( (line = buffer.readLine()) != null ) {
                String[] data = line.split(",");
                Book book = new Book(data[0], data[1],
                        data[2],
                        data[3],
                        data[4],
                        data[5],
                        data[6],
                        data[7],
                        Integer.parseInt(data[8]),
                        data[9],
                        Integer.parseInt(data[10]),
                        Double.parseDouble(data[11]));
                inventory.addBook(book);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return inventory;
    }

    @Override
    public void writeData(BookInventory bookInventory) {
        String path = directoryName + File.separator + filename;
        File file = new File(path);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            buffer.write(bookInventory.toCsv());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}