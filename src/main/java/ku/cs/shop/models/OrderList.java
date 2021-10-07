package ku.cs.shop.models;

import ku.cs.shop.services.BookSortByTimeComparator;
import ku.cs.shop.services.OrderSortByTimeComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OrderList {
    private ArrayList<Order> orders;

    public OrderList() {
        orders = new ArrayList<>();
    }

    public void addOrder(Order order){
        orders.add(order);
        sort();
    }

    public Order getOrder(int index) { return orders.get(index); }

    public String toCSV() {
        String csv = "" ;
        for(Order order : orders) {
            csv += order.toCsv();
        }
        return csv;
    }

    public void sort(Comparator<Order> orderComparator) {
        Collections.sort(this.orders, orderComparator);
    }

    public void sort(){
        OrderSortByTimeComparator orderSortByTimeComparator = new OrderSortByTimeComparator();
        sort(orderSortByTimeComparator);
    }
    public int getOrderListCount(){ return this.orders.size(); }
}
