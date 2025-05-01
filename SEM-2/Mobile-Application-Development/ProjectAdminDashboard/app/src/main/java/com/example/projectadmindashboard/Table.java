package com.example.projectadmindashboard;

public class Table {
    private String status;
    private String tableName;  // This must match exactly with Firebase field name
    private int tableNumber;   // This must match exactly with Firebase field name

    public Table() {
        // Default constructor required for Firebase
    }

    public Table(String status, String tableName, int tableNumber) {
        this.status = status;
        this.tableName = tableName;
        this.tableNumber = tableNumber;
    }

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Change this to getTableName() to match the field name
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }
}