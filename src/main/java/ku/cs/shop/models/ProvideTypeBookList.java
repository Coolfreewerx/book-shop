package ku.cs.shop.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProvideTypeBookList {
    private ArrayList<ProvideTypeBook> typeBooks;
    private Map<String, ArrayList<ProvideTypeBook>> typeBookMap;
    public void ProvideTypeBookList(){
        typeBookMap = new HashMap<>();
    }

    public void addTypeBook(ProvideTypeBook provideTypeBook){
        typeBooks.add(provideTypeBook);
    }

    public ArrayList<ProvideTypeBook> findSubTypeBook(String typeBook){
        return typeBookMap.get(typeBook);
    }

    public Boolean checkNewTypeBook(String typeBook){
        for(ProvideTypeBook provideTypeBook : typeBooks){
            if (provideTypeBook.equals(typeBook)) {
                return true;
            }
        }
        return false;
    }

    public String toCSV() {
        String csv = "" ;
        for(ProvideTypeBook provideTypeBook: typeBooks) {
            csv += provideTypeBook.toCsv();
        }
        return csv;
    }

}
