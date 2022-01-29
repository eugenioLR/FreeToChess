/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.store;

import com.edgenp.free2chess.product.*;
import com.edgenp.free2chess.storeController.ProductService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author eugeniolr
 */
public class LootboxConstructor extends ProductConstructor{
    
    @Autowired
    private ProductService prodServ;

    public LootboxConstructor() {
    }

    @Override
    public void selectByRarity(char rarity) {
        List<Product> prod;
        
        // Add the rarity selected and all the previous ones
        switch(rarity){
            case 'S':
                prod = prodServ.getByRarity('S');
                this.packContents.addAll(prod);
            case 'A':
                prod = prodServ.getByRarity('A');
                this.packContents.addAll(prod);
            case 'B':
                prod = prodServ.getByRarity('B');
                this.packContents.addAll(prod);
            case 'C':
                prod = prodServ.getByRarity('C');
                this.packContents.addAll(prod);
            case 'D':
                prod = prodServ.getByRarity('D');
                this.packContents.addAll(prod);
            default:
                prod = prodServ.getByRarity('F');
                this.packContents.addAll(prod);
        }
    }

    @Override
    public void selectAmount(int amount) {
        Collections.shuffle(packContents);
        
        Iterator<Product> iterator = packContents.iterator();
        int excessElements = packContents.size()-amount;
        for(int i = 0; i < excessElements; i++){
            iterator.remove();
            iterator.next();
        }
    }

    @Override
    public ProductPack getPack() {
        this.pack = new ProductPack(this.packContents, false);
        return this.pack;
    }
    
    
    
}
