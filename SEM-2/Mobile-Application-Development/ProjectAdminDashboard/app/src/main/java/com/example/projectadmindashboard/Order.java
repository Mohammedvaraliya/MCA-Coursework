package com.example.projectadmindashboard;

import java.util.List;

public class Order {
    private String id;
    private String userEmail;
    private String tableName;
    private List<OrderItem> items;
    private int totalAmount;
    private String status;
    private long timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Order() {
        // Required for Firebase
    }

    public Order(String userEmail, String tableName, List<OrderItem> items,
                 int totalAmount, String status, long timestamp) {
        this.userEmail = userEmail;
        this.tableName = tableName;
        this.items = items;
        this.totalAmount = totalAmount;
        this.status = status;
        this.timestamp = timestamp;
    }

    // Getters and setters
    public String getUserEmail() {
        return userEmail;
    }

    public String getTableName() {
        return tableName;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public long getTimestamp() {
        return timestamp;
    }
}