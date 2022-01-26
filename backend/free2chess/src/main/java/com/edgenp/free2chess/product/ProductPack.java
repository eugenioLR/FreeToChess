/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.product;

import com.edgenp.free2chess.store.Store;
import com.edgenp.free2chess.user.User;
import com.fasterxml.jackson.annotation.*;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author eugeniolr
 */
@Entity
@Table(name = "\"ProductPack\"")
public class ProductPack implements ProductInterf{
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
    
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "\"PackContents\"", 
               joinColumns = @JoinColumn(name = "\"id_ProductPack\"", referencedColumnName = "id"), 
               inverseJoinColumns = @JoinColumn(name = "\"id_Product\"", referencedColumnName = "id"))
    private Set<Product> contents = new HashSet<>();
    
    public ProductPack() {
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

    public Set<Product> getContents() {
        return contents;
    }
    
    @Override
    public void increasePurchases(){
        this.purchases++;
        for(ProductInterf p : this.contents){
           p.increasePurchases();
        }
    }    

    @Override
    public void buyItem(User user) {
        Store store = Store.getInstance();
        
        double realPrice = 0.001*this.c_price + 0.01*this.d_price;
        double finalPrice =  realPrice * (1 - this.discount_perc);
        finalPrice = ((double) Math.round(finalPrice*100))/100.00;
        store.doPayment(user, finalPrice);
        this.increasePurchases();
    }
}
