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
public class StratKing implements PieceStrat{
    
    private boolean moved;

    public StratKing() {
        this.moved = false;
    }

    @Override
    public boolean verifyMove(Board board, int[] pos_init, int[] pos_new) {
        int delta_y = pos_new[0] - pos_init[0];
        int delta_x = pos_new[1] - pos_init[1];
        return (Math.abs(delta_x) == 1 || Math.abs(delta_y) == 1) && board.getPiece(pos_init).getColor() != board.getPiece(pos_new).getColor();
    }
    
    @Override
    public void confirmMove() {
        moved = true;
    }   
    
    @Override
    public char getName() {
        return 'K';
    }
}
