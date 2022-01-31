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
    
    /**
     * Obtiene todas las skins del tablero
     * @return
     */
    public List<Product> getAll(){
        return prodRepo.findAll();
    }
    
    /**
     * Obtiene los productos dada su rareza
     * @param rarity
     * @return
     */
    public List<Product> getByRarity(char rarity){
        return prodRepo.findByRarity(rarity);
    }
    
    /**
     * Obtiene el usuario dado su ID
     * @param id
     * @return
     */
    public Product getById(Integer id){
        Optional<Product> optional = prodRepo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
    
    /**
     * Actualiza las propiedades de un producto
     * @param prod
     */
    public void update(Product prod){
        prodRepo.save(prod);
    }
}
