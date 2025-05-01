package com.example.projectadmindashboard;

public class OrderItem {
    private String name;
    private int price;
    private int quantity;
    private int totalPrice;

    public OrderItem() {
        // Required for Firebase
    }

    public OrderItem(String name, int price, int quantity, int totalPrice) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}