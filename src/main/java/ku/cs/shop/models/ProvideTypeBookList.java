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
        System.out.println(typeBook);
        ArrayList<ProvideTypeBook> provideTypeBookArrayList = new ArrayList<>();
        for(ProvideTypeBook provideTypeBook : typeBooks){
            if (provideTypeBook.getSuperTypeBook().equals(typeBook)){
                provideTypeBookArrayList.add(provideTypeBook);
            }
        }
        System.out.println(provideTypeBookArrayList);
        return provideTypeBookArrayList;
    }

    public int numOfSubTypeBook(String typeBook){
        ArrayList<String> provideSubTypeBookArrayList = new ArrayList<>();
        for(ProvideTypeBook provideTypeBook : typeBooks){
            if (provideTypeBook.getSuperTypeBook().equals(typeBook)){
                provideSubTypeBookArrayList.add(provideTypeBook.getSubTypeBook());
            }
        }
        return provideSubTypeBookArrayList.size();
    }

    public int maxSizeSubTypeBook(){
        int max = 0;
        for(String superType : superTypeBook){
            if ( max == 0 ){
                max = 2;
            }
            else if ( numOfSubTypeBook(superType) > max ){
                max = numOfSubTypeBook(superType);
            }
        }
        return max;
    }

    public Set<String> getSuperTypeBook() { return superTypeBook; }

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

    @Override
    public String toString() {
        return "ProvideTypeBookList{" +
                ", superTypeBook=" + superTypeBook +
                '}';
    }
}
