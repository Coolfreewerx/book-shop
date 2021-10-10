package ku.cs.shop.models;

import java.util.*;

public class ProvideTypeBookList {
    private ArrayList<ProvideTypeBook> typeBooks;
    private Set<String> superTypeBook;

    public ProvideTypeBookList(){
        typeBooks = new ArrayList<>();
        superTypeBook = new HashSet<>();
    }

    public void addTypeBook(ProvideTypeBook provideTypeBook){
        typeBooks.add(provideTypeBook);
        superTypeBook.add(provideTypeBook.getSuperTypeBook());
    }

    public ArrayList<ProvideTypeBook> findSubTypeBook(String typeBook){
        ArrayList<ProvideTypeBook> provideTypeBookArrayList =new ArrayList<>();
        for(ProvideTypeBook provideTypeBook : typeBooks){
            if (provideTypeBook.getSuperTypeBook().equals(typeBook)){
                provideTypeBookArrayList.add(provideTypeBook);
            }
        }
        return provideTypeBookArrayList;
    }

    public int numOfSubTypeBook(String typeBook){
        ArrayList<ProvideTypeBook> provideTypeBookArrayList =new ArrayList<>();
        for(ProvideTypeBook provideTypeBook : typeBooks){
            if (provideTypeBook.getSuperTypeBook().equals(typeBook)){
                provideTypeBookArrayList.add(provideTypeBook);
            }
        }
        return provideTypeBookArrayList.size();
    }

    public int maxSizeSubTypeBook(){
        int max = 0;
        for(ProvideTypeBook provideTypeBook : typeBooks){
            if (max == 0){
                max = numOfSubTypeBook(provideTypeBook.getSuperTypeBook());
            }
            else if (numOfSubTypeBook(provideTypeBook.getSuperTypeBook()) > max){
                max = numOfSubTypeBook(provideTypeBook.getSuperTypeBook());
            }
        }
        return max;
    }

//    public int numSubTypeBook(String typeBook){
//        return this.typeBookMap.get(typeBook).size();
//    }

    public boolean checkNewTypeBookHaveUsed(String typeBook){
        for(ProvideTypeBook provideTypeBook : this.typeBooks) {
            if (provideTypeBook.getSuperTypeBook().equals(typeBook)) {
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
