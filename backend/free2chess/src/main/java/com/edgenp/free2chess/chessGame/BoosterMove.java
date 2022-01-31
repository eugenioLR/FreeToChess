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
public class BoosterMove implements GameCommandInterf{

    private Board board;

    /**
     *
     * @param board
     */
    public BoosterMove(Board board) {
        this.board = board;
    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public boolean canMove(User user) {
        return false;
    }

    /**
     *
     * @param user
     */
    @Override
    public void performMove(User user) {
    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public boolean verifyMove(User user) {
        return false;
    }
    

    /**
     *
     */
    @Override
    public void undoMove() {
    }

    

}
