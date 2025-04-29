package com.example.project.hotel.normalSystem;

public class Item {
    private String name;
    private int price;

    public Item() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
}
