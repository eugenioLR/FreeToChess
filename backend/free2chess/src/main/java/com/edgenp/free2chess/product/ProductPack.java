/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.product;

import com.edgenp.free2chess.store.Store;
import com.edgenp.free2chess.user.User;
import com.fasterxml.jackson.annotation.*;
import java.util.*;
import javax.persistence.*;

/**
 *
 * @author eugeniolr
 */
@Entity
@Table(name = "\"ProductPack\"")
public class ProductPack implements ProductInterf{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="product_id_generator")
    private int id;
    private String name;
    private String description;
    private int c_price;
    private int d_price;
    private char rarity;
    protected long purchases;
    private float discount_perc;
    private boolean for_sale;
    
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "\"PackContents\"", 
               joinColumns = @JoinColumn(name = "\"id_ProductPack\"", referencedColumnName = "id"), 
               inverseJoinColumns = @JoinColumn(name = "\"id_Product\"", referencedColumnName = "id"))
    private List<Product> contents = new ArrayList<>();
    
    public ProductPack() {
    }

    public ProductPack(List<Product> contents, boolean for_sale) {
        this.contents = contents;
        this.for_sale = for_sale;
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

    public boolean isFor_sale() {
        return for_sale;
    }

    public void setFor_sale(boolean for_sale) {
        this.for_sale = for_sale;
    }
    
    public List<Product> getContents() {
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
    public boolean buyItem(User user) {
        boolean canBuy;
        if(canBuy = user.canBuy(this.c_price, this.d_price)){
            user.spendCoins(this.c_price);
            user.spendDiamonds(this.d_price);
            this.increasePurchases();
        }
        return canBuy;
    }
}
