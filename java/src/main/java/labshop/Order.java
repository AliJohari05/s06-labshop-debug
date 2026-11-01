package labshop;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final int id;
    private final String customer;
    private final String couponCode;
    private final List<Item> items = new ArrayList<>();

    public Order(int id, String customer, String couponCode) {
        this.id = id;
        this.customer = customer;
        this.couponCode = couponCode;
    }

    public int getId() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public List<Item> getItems() {
        return items;
    }
}
