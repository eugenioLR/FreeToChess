/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.product;

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
public class ProductPack extends Product{
            
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "\"PackContents\"", 
               joinColumns = @JoinColumn(name = "\"id_Product\"", referencedColumnName = "id"), 
               inverseJoinColumns = @JoinColumn(name = "\"id_ProductPack\"", referencedColumnName = "id"))
    private Set<Product> contents = new HashSet<>();
    
    public ProductPack() {
    }

    public Set<Product> getContents() {
        return contents;
    }
    
    @Override
    public void increasePurchases(){
        this.purchases++;
        for(Product p : this.contents){
           p.increasePurchases();
        }
    }    
}
