/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.chessGame;

/**
 *
 * @author eugeniolr
 */
public class BoosterMove implements GameCommandInterf{

    private Board board;

    public BoosterMove(Board board) {
        this.board = board;
    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public void performMove() {
    }

    @Override
    public void undoMove() {
    }

    

}
