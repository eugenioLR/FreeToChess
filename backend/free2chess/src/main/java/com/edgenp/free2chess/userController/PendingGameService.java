/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edgenp.free2chess.userController;

import com.edgenp.free2chess.chessController.GameJPA;
import com.edgenp.free2chess.user.*;
import java.util.*;
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
     * Obtiene una peticion de juego dado un ID.
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
     * Obtiene las peticiones de juego que involucren al usuario indicado
     * @param name
     * @return
     */
    public List<PendingGame> getByUser(String name){
        
        Set<PendingGame> openGames = pendGameRepo.findByEmiter_name(name);
        openGames.addAll(pendGameRepo.findByReceiver_name(name));
        
        Iterator<PendingGame> iter = openGames.iterator();
        while(iter.hasNext()){
            if(!iter.next().isAcepted()){
                iter.remove();
            }
            System.out.println("challenge found");
        }
        
        return new ArrayList<>(openGames);       
    }
    
    /**
     * Crea una peticion de juego en la base de datos
     * @param pendGame
     */
    public void create(PendingGame pendGame){
        pendGameRepo.save(pendGame);
    }
    
    /**
     * Crea una peticion de juego en la base de datos
     * @param pendGame
     */
    public void update(PendingGame pendGame){
        pendGameRepo.save(pendGame);
    }

    /**
     * Elimina una peticion de juego junto al juego asociado.
     * @param pendGame
     */
    public void removeCascade(PendingGame pendGame) {
        gameRepo.delete(pendGame.getGame());
        pendGameRepo.delete(pendGame);
    }
    
    /**
     * Elimina una peticion de juego.
     * @param pendGame
     */
    public void remove(PendingGame pendGame) {
        pendGameRepo.delete(pendGame);
    }
}
