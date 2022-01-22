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
public class BoardSkinService {
    
    @Autowired
    private BoardSkinJPA boardSkinRepo;
    
    public List<BoardSkin> getAll(){
        return boardSkinRepo.findAll();
    }
    
    public void update(BoardSkin prod){
        boardSkinRepo.save(prod);
    }
}