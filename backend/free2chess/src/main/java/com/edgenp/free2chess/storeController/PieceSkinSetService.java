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
public class PieceSkinSetService {
    
    @Autowired
    private PieceSkinSetJPA pieceSkinSetRepo;
    
    /**
     * Obtiene todas las skins del tablero
     * @return
     */
    public List<PieceSkinSet> getAll(){
        return pieceSkinSetRepo.findAll();
    }
    
    /**
     * Actualiza las propiedades de un set de skins de piezas
     * @param prod
     */
    public void update(PieceSkinSet prod){
        pieceSkinSetRepo.save(prod);
    }
}