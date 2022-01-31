/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.product;

import com.edgenp.free2chess.store.Store;
import com.edgenp.free2chess.user.User;
import com.fasterxml.jackson.annotation.*;
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 *
 * @author eugeniolr
 */
@Entity
@Table(name = "\"ProductPack\"")
public class ProductPack implements ProductInterf, Serializable{
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
    
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "\"PackContents\"", 
               joinColumns = @JoinColumn(name = "\"id_ProductPack\"", referencedColumnName = "id"), 
               inverseJoinColumns = @JoinColumn(name = "\"id_Product\"", referencedColumnName = "id"))
    private List<Product> contents = new ArrayList<>();
    
    /**
     *
     */
    public ProductPack() {
    }

    /**
     *
     * @param contents
     * @param for_sale
     */
    public ProductPack(List<Product> contents, boolean for_sale) {
        this.name = "generatedPack";
        this.description = "Pack generated automatically by the server.";
        this.c_price = 0;
        this.d_price = 20;
        this.rarity = 'F';
        this.purchases = 0;
        this.discount_perc = 0;
        this.for_sale = for_sale;
        this.contents = contents;
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
     * @return
     */
    public List<Product> getContents() {
        return contents;
    }
    
    /**
     * incrementa el numero de compras de los contenidos del pack recursivamente
     */
    @Override
    public void increasePurchases(){
        this.purchases++;
        for(ProductInterf p : this.contents){
           p.increasePurchases();
        }
    }    

    /**
     * Compra un pack de productos y cobra las monedas y diamantes del usuario
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
     * Obtiene las ids de los contenidos del pack
     * @return
     */
    public List<Integer> getIdContents() {
        List<Integer> ids = new ArrayList<>();
        
        for(Product p : contents){
            ids.add(p.getId());
        }
        
        return ids;
    }

    /**
     * Obtiene los nombres de los productos en el pack
     * @return
     */
    public List<String> getContentNames() {
        List<String> names = new ArrayList<>();
        
        for(Product p : contents){
            names.add(p.getName());
        }
        
        return names;
    }
}
