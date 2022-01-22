/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edgenp.free2chess.Product;

import javax.persistence.*;

/**
 *
 * @author eugeniolr
 */
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private double price;
    private char rarity;
    private long purchases;
    private float discount_perc;

    public Product() {
    }

    public Product(String name, String description, double price, char rarity, long purchases, float discount_perc) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rarity = rarity;
        this.purchases = purchases;
        this.discount_perc = discount_perc;
    }

    public Product(String name, String description, double price, char rarity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rarity = rarity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public char getRarity() {
        return rarity;
    }

    public void setRarity(char rarity) {
        this.rarity = rarity;
    }

    public long getPurchases() {
        return purchases;
    }

    public void setPurchases(long purchases) {
        this.purchases = purchases;
    }

    public float getDiscount_perc() {
        return discount_perc;
    }

    public void setDiscount_perc(float discount_perc) {
        this.discount_perc = discount_perc;
    }

    @Override
    public String toString() {
        return "Product{" + ", name=" + name + ", description=" + description + ", price=" + price + ", rarity=" + rarity + ", purchases=" + purchases + ", discount_perc=" + discount_perc + '}';
    }
    
    
}
