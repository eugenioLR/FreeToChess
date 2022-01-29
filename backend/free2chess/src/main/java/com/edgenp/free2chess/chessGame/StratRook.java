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
public class StratRook implements PieceStrat{
    
    private boolean moved;

    public StratRook() {
        this.moved = false;
    }

    @Override
    public boolean verifyMove(Board board, int[] pos_init, int[] pos_new) {
        boolean valid;
        int delta_y = pos_new[0] - pos_init[0];
        int delta_x = pos_new[1] - pos_init[1];
        if(valid = !Arrays.equals(pos_init, pos_new) && (delta_x == 0 || delta_y == 0)){
            int[] aux_pos = pos_init;
            aux_pos[0] += Math.signum(delta_y);
            aux_pos[1] += Math.signum(delta_x);
            for(int i = 0; !Arrays.equals(aux_pos, pos_init) && valid; i++){
                valid = board.getPiece(aux_pos).getType() == '-';
                aux_pos[0] += Math.signum(delta_y);
                aux_pos[1] += Math.signum(delta_x);
            }
            valid = valid && board.getPiece(pos_new).getColor() != board.getPiece(pos_init).getColor();
        }
        return valid;
    }

    @Override
    public void confirmMove() {
    }   

    @Override
    public char getName() {
        return 'R';
    }
}
