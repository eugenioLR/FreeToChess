/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edgenp.free2chess.storeController;

import com.edgenp.free2chess.product.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author eugeniolr
 */


@Service
public class ProductPackService {
    
    @Autowired
    private ProductPackJPA prodPackRepo;
    
    public List<ProductPack> getAll(){
        return prodPackRepo.findAll();
    }
    
    public ProductPack getById(Integer id){
        Optional<ProductPack> optional = prodPackRepo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
    
    public void update(ProductPack prod){
        prodPackRepo.save(prod);
    }
}