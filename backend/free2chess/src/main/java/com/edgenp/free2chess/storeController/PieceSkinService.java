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
public class PieceSkinService {
    
    @Autowired
    private PieceSkinJPA pieceSkinRepo;
    
    public List<PieceSkin> getAll(){
        return pieceSkinRepo.findAll();
    }
    
    public void update(PieceSkin prod){
        pieceSkinRepo.save(prod);
    }
}