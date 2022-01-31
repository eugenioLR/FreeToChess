/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.product;

import com.edgenp.free2chess.store.Store;
import com.edgenp.free2chess.user.User;
import java.io.Serializable;
import javax.persistence.*;
/**
 *
 * @author eugeniolr
 */
@Entity
@Table(name = "\"Product\"")
@Inheritance(strategy = InheritanceType.JOINED)
public class Product implements ProductInterf, Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="product_id_generator")
    private int id;
    private String name;
    private String description;
    private int c_price;
    private int d_price;
    private char rarity;

    /**
     *
     */
    protected long purchases;
    private float discount_perc;
    private boolean for_sale;

    /**
     *
     */
    public Product() {
    }

    /**
     *
     * @param name
     * @param description
     * @param c_price
     * @param d_price
     * @param rarity
     * @param purchases
     * @param discount_perc
     */
    public Product(String name, String description, int c_price, int d_price, char rarity, long purchases, float discount_perc) {
        this.name = name;
        this.description = description;
        this.c_price = c_price;
        this.d_price = d_price;
        this.rarity = rarity;
        this.purchases = purchases;
        this.discount_perc = discount_perc;
    }

    /**
     *
     * @param name
     * @param description
     * @param c_price
     * @param d_price
     * @param rarity
     */
    public Product(String name, String description, int c_price, int d_price, char rarity) {
        this.name = name;
        this.description = description;
        this.c_price = c_price;
        this.d_price = d_price;
        this.rarity = rarity;
    }

    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }
    
    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return
     */
    public int getC_price() {
        return c_price;
    }

    /**
     *
     * @return
     */
    public int getD_price() {
        return d_price;
    }

    /**
     *
     * @return
     */
    public char getRarity() {
        return rarity;
    }

    /**
     *
     * @return
     */
    public long getPurchases() {
        return purchases;
    }

    /**
     *
     * @return
     */
    public float getDiscount_perc() {
        return discount_perc;
    }
    
    /**
     *
     * @return
     */
    public boolean isFor_sale() {
        return for_sale;
    }

    /**
     *
     * @param for_sale
     */
    public void setFor_sale(boolean for_sale) {
        this.for_sale = for_sale;
    }
    
    /**
     *
     */
    @Override
    public void increasePurchases(){
        this.purchases++;
    }

    /**
     * Compra un producto y cobra las monedas y diamantes del usuario
     * @param user
     * @return
     */
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

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", description=" + description + ", c_price=" + c_price + ", d_price=" + d_price + ", rarity=" + rarity + ", purchases=" + purchases + ", discount_perc=" + discount_perc + ", for_sale=" + for_sale + '}';
    }
    
    
}