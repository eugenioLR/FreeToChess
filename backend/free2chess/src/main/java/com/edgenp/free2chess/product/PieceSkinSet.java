/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.product;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author eugeniolr
 */
@Entity
@Table(name = "\"PieceSkinSet\"")
public class PieceSkinSet extends Product{
    
    @OneToMany
    private final Set<PieceSkin> pieceSkins = new HashSet<>();
    
    /*
    public Set<PieceSkin> obtainContents(){
        return pieceSkins;
    }
    */
    
    @Override
    public void increasePurchases(){
        this.purchases++;
        for(Product p : this.pieceSkins){
           p.increasePurchases();
        }
    }
    
}
