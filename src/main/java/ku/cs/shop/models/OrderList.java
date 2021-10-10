package ku.cs.shop.models;

import ku.cs.shop.services.BookSortByTimeComparator;
import ku.cs.shop.services.OrderSortByTimeComparator;

import java.util.*;

public class OrderList {

    private Set<String> customerName;
    private ArrayList<Order> orders;

    public OrderList() {
        orders = new ArrayList<>();
        customerName = new HashSet<>();
    }

    public void addOrder(Order order){
        orders.add(order);
        customerName.add(order.getCustomerName());
        sort();
    }

    public Order getOrder(int index) { return orders.get(index); }

    public ArrayList<Order> getOrderByCustomerName(String name) {
        ArrayList<Order> orderByName = new ArrayList<>();
        for(Order order : orders) {
            if (order.getCustomerName().equals(name)) {
                orderByName.add(order);
            }
        }
        return orderByName;
    }

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
