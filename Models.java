package com.example.start;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

class Models {

}

class User{
    private int id;
    private String name;
    private String password;

    public User() {
        Object[] things = new Object[] {1, "name", 89, true};
    }

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

class Item{
    private int id;
    private String name;
    private double buyPrice;
    private int quantity;
    private double salePrice;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;

    public Item() {
    }

    public Item(int id, String name, double buyPrice, int quantity, double salePrice, Timestamp dateCreated, Timestamp dateUpdated) {
        this.id = id;
        this.name = name;
        this.buyPrice = buyPrice;
        this.quantity = quantity;
        this.salePrice = salePrice;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Timestamp getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Timestamp dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", buyPrice=" + buyPrice +
                ", quantity=" + quantity +
                ", salePrice=" + salePrice +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                '}';
    }
}

class Purchases{
    private int id;
    private String description;
    private Item item;
    private int quantity;
    private double purchasePrice;
    private Timestamp datePurchased;

    public Purchases() {
    }

    public Purchases(int id, String description, Item item, int quantity, double purchasePrice, Timestamp datePurchased) {
        this.id = id;
        this.description = description;
        this.item = item;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.datePurchased = datePurchased;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Timestamp getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(Timestamp datePurchased) {
        this.datePurchased = datePurchased;
    }

    @Override
    public String toString() {
        return "Purchases{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", item=" + item +
                ", quantity=" + quantity +
                ", purchasePrice=" + purchasePrice +
                ", datePurchased=" + datePurchased +
                '}';
    }
}

class Sales {
    private int id;
    private Item item;
    private Timestamp dateSold;
    private int quantity;

    public Sales() {
    }

    public Sales(int id, Item item, Timestamp dateSold, int quantity) {
        this.id = id;
        this.item = item;
        this.dateSold = dateSold;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Timestamp getDateSold() {
        return dateSold;
    }

    public void setDateSold(Timestamp dateSold) {
        this.dateSold = dateSold;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "id=" + id +
                ", item=" + item +
                ", dateSold=" + dateSold +
                ", quantity=" + quantity +
                '}';
    }
}