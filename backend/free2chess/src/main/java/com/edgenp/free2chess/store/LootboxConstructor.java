/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.store;

import com.edgenp.free2chess.product.*;
import com.edgenp.free2chess.storeController.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author eugeniolr
 */
@Service
public class LootboxConstructor extends ProductConstructor{
    
    @Autowired
    private ProductJPA prodRepo;

    @Override
    public void wipePack() {
        this.packContents = new ArrayList<>();
        this.pack = null;
    }
    
    
    @Override
    public void selectByRarity(char rarity) {
        List<Product> prod;
        
        // Add the rarity selected and all the previous ones
        switch(rarity){
            case 'S':
                prod = prodRepo.findByRarity('S');
                this.packContents.addAll(prod);
            case 'A':
                prod = prodRepo.findByRarity('A');
                this.packContents.addAll(prod);
            case 'B':
                prod = prodRepo.findByRarity('B');
                this.packContents.addAll(prod);
            case 'C':
                prod = prodRepo.findByRarity('C');
                this.packContents.addAll(prod);
            case 'D':
                prod = prodRepo.findByRarity('D');
                this.packContents.addAll(prod);
            default:
                prod = prodRepo.findByRarity('F');
                this.packContents.addAll(prod);
        }
    }

    @Override
    public void selectAmount(int amount) {
        Collections.shuffle(packContents);
        
        Iterator<Product> iterator = packContents.iterator();
        int excessElements = packContents.size()-amount;
        for(int i = 0; i < excessElements && iterator.hasNext(); i++){
            iterator.next();
            iterator.remove();
            
        }
    }

    @Override
    public ProductPack getPack() {
        this.pack = new ProductPack(this.packContents, false);
        return this.pack;
    }
    
    
    
}
