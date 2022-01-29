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
    
    public void addMove(User user, GameCommandInterf command){
        moveList.add(command);
        command.performMove(user);
    }
    
    public void undoMove(){
        moveList.get(moveList.size()-1).undoMove();
    }

}
