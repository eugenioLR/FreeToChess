/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.chessGame;

import java.util.Arrays;

/**
 *
 * @author eugeniolr
 */
public class StratPawn implements PieceStrat{
    
    private boolean moved;

    public StratPawn() {
        this.moved = false;
    }

    @Override
    public boolean verifyMove(Board board, int[] pos_init, int[] pos_new) {
        boolean valid;
        int delta_y = pos_new[0] - pos_init[0];
        int delta_x = pos_new[1] - pos_init[1];
        if(valid = !Arrays.equals(pos_init, pos_new) && Math.abs(delta_x) < 2 && delta_y > 0){
            int limit = moved ? 1 : 2;
            if(board.getPiece(pos_init).getColor() == 0){
                limit += -1;
            }
            
            valid = delta_y <= limit && board.getPiece(pos_init).getColor() != board.getPiece(pos_new).getColor();
            if(board.getPiece(pos_new).getColor() == '-'){
                valid = valid && delta_x == 0;
            }else{
                valid = valid && Math.abs(delta_x) == 1;
            } 
        }
        return valid;
    }

    @Override
    public void confirmMove() {
        moved = true;
    }   
    
    @Override
    public char getName() {
        return 'P';
    }
}
