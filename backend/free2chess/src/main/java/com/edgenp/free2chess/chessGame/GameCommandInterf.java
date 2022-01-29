/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.chessGame;

import com.edgenp.free2chess.user.User;

/**
 *
 * @author eugeniolr
 */
public interface GameCommandInterf {

    /**
     * Verifica si el movimiento que se va a realizar es valido
     * @param user
     * @return
     */
    public boolean canMove(User user);

    /**
     * Realiza un movimiento en el tablero
     * @param user
     */
    public void performMove(User user);

    /**
     * Deshace un movimiento en el tablero
     */
    public void undoMove();
}
