/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.user;

import com.edgenp.free2chess.product.*;
import com.edgenp.free2chess.store.Store;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.Instant;
import java.util.*;
import javax.persistence.*;

/**
 *
 * @author eugeniolr
 */
@Entity
@Table(name = "\"User\"")
public class User implements UserInterf{
    @Id
    protected String name;
    protected String password;
    protected String salt;
    protected int elo;
    protected int level;
    protected int exp;
    protected boolean subscribed;
    protected Date subs_date;
    protected int paypal_id;
    protected int coins;
    protected int diamonds;
    
    
    
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "\"PurchasedProduct\"", 
               joinColumns = @JoinColumn(name = "\"name_User\"", referencedColumnName = "name"), 
               inverseJoinColumns = @JoinColumn(name = "\"id_Product\"", referencedColumnName = "id"))
    private Set<Product> purchasedProducts = new HashSet<>();

    
    public User() {
    }
    
    public User(String name, int paypal_id) {
    }
    
    public User(String name, String password, String salt, int paypal_id) {
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.paypal_id = paypal_id;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getElo() {
        return elo;
    }

    public void setElo(int elo) {
        this.elo = elo;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void subscribe() {
        Store store = Store.getInstance();
        store.doPayment(this, 5.99);
        Date now = new Date();
        this.setSubs_date(now);
        this.subscribed = true;
    }
    
    public void unsubscribe(){
        this.subscribed = false;
    }

    public Date getSubs_date() {
        return subs_date;
    }

    public void setSubs_date(Date subs_date) {
        this.subs_date = subs_date;
    }

    public int getPaypal_id() {
        return paypal_id;
    }

    public void setPaypal_id(int paypal_id) {
        this.paypal_id = paypal_id;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getDiamonds() {
        return diamonds;
    }

    public void setDiamonds(int diamonds) {
        this.diamonds = diamonds;
    }    

    public Set<Product> getPurchasedProducts() {
        return purchasedProducts;
    }
    
    public void addPurchasedProducts(ProductInterf prod){
        Product p = (Product) prod;
        this.purchasedProducts.add(p);
    }

    @Override
    public String toString() {
        return "User{" + ", name=" + name + ", password=" + password + ", salt=" + salt + ", elo=" + elo + ", level=" + level + ", exp=" + exp + ", subscribed=" + subscribed + ", subs_date=" + subs_date + ", paypal_id=" + paypal_id + ", coins=" + coins + ", diamonds=" + diamonds + '}';
    }
    
    
}
