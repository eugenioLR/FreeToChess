/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.chessGame;

import com.edgenp.free2chess.user.User;
import java.util.*;

/**
 *
 * @author eugeniolr
 */
public class MoveInvoker {
    private final List<GameCommandInterf> moveList = new ArrayList<>();
    
    /**
     * Añade un movimiento a la lista de comandos y lo ejecuta
     * @param user
     * @param command
     */
    public void addMove(User user, GameCommandInterf command){
        command.performMove(user);
        moveList.add(command);
    }
    
    /**
     * 
     */
    public void undoMove(){
        moveList.get(moveList.size()-1).undoMove();
        moveList.remove(moveList.size()-1);
    }

}
