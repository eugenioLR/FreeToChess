/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edgenp.free2chess.storeController;

import com.edgenp.free2chess.product.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eugeniolr
 */
@Repository
public interface ProductJPA extends JpaRepository<Product, Integer>{   

    /**
     *
     * @param rarity
     * @return
     */
    public List<Product> findByRarity(char rarity);
    
}
