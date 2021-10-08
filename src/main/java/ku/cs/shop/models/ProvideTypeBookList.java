package ku.cs.shop.models;

import java.util.HashMap;
import java.util.Map;

public class ProvideTypeBookList {
    private Map<String, String> typeBookMap;
    public void ProvideTypeBookList(){
        typeBookMap = new HashMap<>();
    }

    public void addTypeBook(ProvideTypeBook provideTypeBook){
        typeBookMap.keySet().add(provideTypeBook.getSuperTypeBook());
        typeBookMap.values().add(provideTypeBook.getSubTypeBook());
    }

    public String findSubTypeBook(String typeBook){
        return typeBookMap.get(typeBook);
    }

}
