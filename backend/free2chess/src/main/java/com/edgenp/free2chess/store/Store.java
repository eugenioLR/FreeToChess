/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.store;

import com.edgenp.free2chess.product.*;
import com.edgenp.free2chess.user.*;

/**
 *
 * @author eugeniolr
 */
public class Store {

    private static PaySysAbs paySystem;
    private static Store instance = null;
     
    private Store() {
        Store.paySystem = new PaySysProxy();
    }

    public static Store getInstance() {
        if(Store.instance == null){
            Store.instance = new Store();
        }
        return Store.instance;
    }

    private double priceByLootbox(char rarity){
        double price;
        switch(rarity){
            case 'S' -> {
                price = 0.5;
            }
            
            case 'A' -> {
                price = 0.35;
            }
            
            case 'B' -> {
                price = 0.2;
            }
            
            case 'C' -> {
                price = 0.1;
            }
            
            case 'D' -> {
                price = 0.05;
            }
            
            case 'F' -> {
                price = 0.01;
            }
            
            default -> {
               price = 0.0;
            }
        }
        return price;
    }
    
    public ProductPack generateLootBox(User user, char rarity, int amount){
        ProductConstructor prodBuilder = new LootboxConstructor();
        double price = this.priceByLootbox(rarity);
        
        this.doPayment(user, price);
        prodBuilder.selectByRarity(rarity);
        prodBuilder.selectAmount(amount);
        return prodBuilder.getPack();
    }

    public void doPayment(User user, double price) {
        paySystem.makePurchase(user.getPaypal_id(), price);
    }

}
