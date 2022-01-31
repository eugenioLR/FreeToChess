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
    
    /**
     * Obtiene todos los emojis
     * @return
     */
    public List<Emoji> getAll(){
        return emojiRepo.findAll();
    }
    
    /**
     * Actualiza las propiedades de un emoji
     * @param prod
     */
    public void update(Emoji prod){
        emojiRepo.save(prod);
    }
}