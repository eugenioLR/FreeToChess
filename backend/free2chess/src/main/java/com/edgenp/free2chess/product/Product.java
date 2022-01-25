/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.product;

import com.edgenp.free2chess.store.Store;
import com.edgenp.free2chess.user.User;
import javax.persistence.*;
import java.io.Serializable;
/**
 *
 * @author eugeniolr
 */
@Entity
@Table(name = "\"Product\"")
@Inheritance(strategy = InheritanceType.JOINED)
public class Product implements ProductInterf, Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private int c_price;
    private int d_price;
    private char rarity;
    protected long purchases;
    private float discount_perc;

    public Product() {
    }

    public Product(String name, String description, int c_price, int d_price, char rarity, long purchases, float discount_perc) {
        this.name = name;
        this.description = description;
        this.c_price = c_price;
        this.d_price = d_price;
        this.rarity = rarity;
        this.purchases = purchases;
        this.discount_perc = discount_perc;
    }

    public Product(String name, String description, int c_price, int d_price, char rarity) {
        this.name = name;
        this.description = description;
        this.c_price = c_price;
        this.d_price = d_price;
        this.rarity = rarity;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getC_price() {
        return c_price;
    }

    public int getD_price() {
        return d_price;
    }

    public char getRarity() {
        return rarity;
    }

    public long getPurchases() {
        return purchases;
    }

    public float getDiscount_perc() {
        return discount_perc;
    }
    
    public void increasePurchases(){
        this.purchases++;
    }

    @Override
    public void buyItem(User user) {
        Store store = Store.getInstance();
        
        double realPrice = 0.001*this.c_price + 0.01*this.d_price;
        double finalPrice =  realPrice * (1 - this.discount_perc);
        finalPrice = ((double) Math.round(finalPrice*100))/100.00;
        store.doPayment(user, finalPrice);
        this.increasePurchases();
        System.out.println(this.purchases);
    }
}