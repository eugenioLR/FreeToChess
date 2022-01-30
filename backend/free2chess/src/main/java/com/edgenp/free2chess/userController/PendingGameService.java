/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edgenp.free2chess.userController;

import com.edgenp.free2chess.chessController.GameJPA;
import com.edgenp.free2chess.user.*;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author eugeniolr
 */
@Service
public class PendingGameService {
    @Autowired
    private PendingGameJPA pendGameRepo;
    
    @Autowired
    private GameJPA gameRepo;
    
    /**
     *
     * @param id
     * @return
     */
    public PendingGame getById(Integer id){
        Optional<PendingGame> optional = pendGameRepo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
    
    /**
     *
     * @param pendGame
     */
    public void create(PendingGame pendGame){
        pendGameRepo.save(pendGame);
    }
    
    /**
     *
     * @param pendGame
     */
    public void update(PendingGame pendGame){
        pendGameRepo.save(pendGame);
    }

    /**
     *
     * @param pendGame
     */
    public void removeCascade(PendingGame pendGame) {
        gameRepo.delete(pendGame.getGame());
        pendGameRepo.delete(pendGame);
    }
    
    /**
     *
     * @param pendGame
     */
    public void remove(PendingGame pendGame) {
        pendGameRepo.delete(pendGame);
    }
}
