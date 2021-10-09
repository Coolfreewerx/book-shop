package ku.cs.shop.services;

import com.opencsv.CSVReader;
import ku.cs.shop.models.ProvideTypeBook;
import ku.cs.shop.models.ProvideTypeBookList;

import java.io.*;

public class ProvideTypeBookDataSource implements DataSource<ProvideTypeBookList>{
    private String filename;
    public ProvideTypeBookDataSource(){}
    public ProvideTypeBookDataSource(String filename){ this.filename = filename; }
    @Override
    public ProvideTypeBookList readData() {
        ProvideTypeBookList provideTypeBookList = new ProvideTypeBookList();

        try{
            FileReader file = new FileReader(filename);
            CSVReader reader = new CSVReader(file);
            String[] data = null;

            while ((data = reader.readNext()) != null){
                String superTypeBook = data[0].trim();
                String subTypeBook = data[1].trim();

                ProvideTypeBook provideTypeBook = new ProvideTypeBook(superTypeBook,subTypeBook);
                provideTypeBookList.addTypeBook(provideTypeBook);
            }

        }catch (FileNotFoundException e) {
            System.err.println("Cannot read information in file " + filename);
        } catch (IOException e) {
            System.err.println("Error reading from file");
        }
        return provideTypeBookList;
    }

    @Override
    public void writeData(ProvideTypeBookList provideTypeBookList) {
        String path = filename;
        File file = new File(path);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            buffer.write(provideTypeBookList.toCSV());

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
