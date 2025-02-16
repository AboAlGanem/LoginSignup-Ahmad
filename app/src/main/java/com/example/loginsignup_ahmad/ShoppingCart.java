package com.example.loginsignup_ahmad;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Car> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addItem(Car item) {
        items.add(item);
    }

    public void removeItem(Car item) {
        items.remove(item);
    }



    public List<Car> getItems() {
        return items;
    }

}


