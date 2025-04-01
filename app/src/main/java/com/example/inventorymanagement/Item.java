package com.example.inventorymanagement;

public class Item {
    private String id;
    private String name;
    private int quantity;
    private double price;
    private String userId; // Add this field

    public Item() {}

    public Item(String id, String name, int quantity, double price, String userId) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.userId = userId;
    }

    // Add getter for userId
    public String getUserId() {
        return userId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
