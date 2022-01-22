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
public class EmojiService {
    
    @Autowired
    private EmojiJPA emojiRepo;
    
    public List<Emoji> getAll(){
        return emojiRepo.findAll();
    }
    
    public void update(Emoji prod){
        emojiRepo.save(prod);
    }
}