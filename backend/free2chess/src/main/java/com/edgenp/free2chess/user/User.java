/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.user;

import com.edgenp.free2chess.chessGame.Game;
import com.edgenp.free2chess.product.*;
import com.edgenp.free2chess.store.Store;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String name;
    private String email;
    private String password;
    private String salt;
    private int elo;
    private int level;
    private int exp;
    private boolean subscribed;
    private Date subs_date;
    private int paypal_id;
    private int coins;
    private int diamonds;
    
    
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "\"PurchasedProduct\"", 
               joinColumns = @JoinColumn(name = "\"name_User\"", referencedColumnName = "name"), 
               inverseJoinColumns = @JoinColumn(name = "\"id_Product\"", referencedColumnName = "id"))
    private Set<Product> purchasedProducts = new HashSet<>();

    @JsonIgnore
    @OneToMany
    @JoinColumns({@JoinColumn(name="reciever_name", referencedColumnName="name")})
    private Set<PendingGame> challenges_opened = new HashSet<>();
    
    @JsonIgnore
    @OneToMany
    @JoinColumns({@JoinColumn(name="emiter_name", referencedColumnName="name")})
    private Set<PendingGame> challenges_issued = new HashSet<>();
    
    
    
    /**
     *
     */
    public User() {
    }

    /**
     *
     * @param name
     * @param email
     * @param password
     * @param salt
     * @param paypal_id
     */
    public User(String name, String email, String password, String salt, int paypal_id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.paypal_id = paypal_id;
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
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public String getSalt() {
        return salt;
    }

    /**
     *
     * @param salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     *
     * @return
     */
    public int getElo() {
        return elo;
    }

    /**
     *
     * @param elo
     */
    public void setElo(int elo) {
        this.elo = elo;
    }

    /**
     *
     * @return
     */
    public int getLevel() {
        return level;
    }

    /**
     *
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     *
     * @return
     */
    public int getExp() {
        return exp;
    }

    /**
     *
     * @param exp
     */
    public void setExp(int exp) {
        this.exp = exp;
    }
    
    /**
     *
     * @param expGain
     */
    public void addExp(int expGain){
        if(this.subscribed){
            expGain *= 1.2;
        }
        
        this.exp += expGain;
        
        if(this.exp > 100){
            this.level += this.exp/100;
            this.exp = this.exp % 100;
        }
    }

    /**
     *
     * @return
     */
    public boolean isSubscribed() {
        return subscribed;
    }

    /**
     * Subscribe a un usuario y le cobra el dinero correspondiente, actualiza
     * tambien la fecha de subscripcion a la fecha actual
     */
    public void subscribe() {
        Store store = Store.getInstance();
        store.doPayment(this, 5.99);
        Date now = new Date();
        this.setSubs_date(now);
        this.subscribed = true;
    }
    
    /**
     *
     */
    public void unsubscribe(){
        this.subscribed = false;
    }

    /**
     *
     * @return
     */
    public Date getSubs_date() {
        return subs_date;
    }

    /**
     *
     * @param subs_date
     */
    public void setSubs_date(Date subs_date) {
        this.subs_date = subs_date;
    }

    /**
     *
     * @return
     */
    public int getPaypal_id() {
        return paypal_id;
    }

    /**
     *
     * @param paypal_id
     */
    public void setPaypal_id(int paypal_id) {
        this.paypal_id = paypal_id;
    }

    /**
     *
     * @return
     */
    public int getCoins() {
        return coins;
    }

    /**
     *
     * @param coins
     */
    public void spendCoins(int coins) {
        this.coins -= coins;
    }
    
    /**
     *
     * @param coins
     */
    public void addCoins(int coins){
        this.coins += coins;
    }

    /**
     *
     * @return
     */
    public int getDiamonds() {
        return diamonds;
    }

    /**
     *
     * @param diamonds
     */
    public void spendDiamonds(int diamonds) {
        this.diamonds -= diamonds;
    }
    
    /**
     *
     * @param diamonds
     */
    public void addDiamonds(int diamonds){
        this.diamonds += diamonds;
    }
    
    /**
     * Comprueba si un usuario puede o no gastar una cantidad de monedas y diamantes
     * @param coins
     * @param diamonds
     * @return
     */
    public boolean canBuy(int coins, int diamonds){
        return this.coins >= coins && this.diamonds >= diamonds;
    }

    /**
     *
     * @return
     */
    public Set<Product> getPurchasedProducts() {
        return purchasedProducts;
    }
    
    /**
     *
     * @param prod
     */
    public void addPurchasedProducts(ProductInterf prod){
        Product p = (Product) prod;
        this.purchasedProducts.add(p);
    }

    /**
     *
     * @return
     */
    public Set<PendingGame> getChallenges_issued() {
        return challenges_issued;
    }

    /**
     *
     * @return
     */
    public Set<PendingGame> getChallenges_opened() {
        return challenges_opened;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "User{" + ", name=" + name + ", password=" + password + ", salt=" + salt + ", elo=" + elo + ", level=" + level + ", exp=" + exp + ", subscribed=" + subscribed + ", subs_date=" + subs_date + ", paypal_id=" + paypal_id + ", coins=" + coins + ", diamonds=" + diamonds + '}';
    }
    
    
}
