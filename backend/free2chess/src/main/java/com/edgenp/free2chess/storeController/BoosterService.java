/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edgenp.free2chess.storeController;

import com.edgenp.free2chess.product.Booster;
import com.edgenp.free2chess.product.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author eugeniolr
 */


@Service
public class BoosterService {
    
    @Autowired
    private BoosterJPA boosterRepo;
    
    public List<Booster> getAll(){
        return boosterRepo.findAll();
    }
    
    public void update(Booster prod){
        boosterRepo.save(prod);
    }
}