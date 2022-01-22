/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edgenp.free2chess.storeController;

import com.edgenp.free2chess.product.*;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author eugeniolr
 */
@Service
public class ProductService {
    
    @Autowired
    private ProductJPA prodRepo;
    
    public List<Product> getAll(){
        return prodRepo.findAll();
    }
    
    public Product getById(Integer id){
        Optional<Product> optional = prodRepo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
    
    public void update(Product prod){
        prodRepo.save(prod);
    }
}
