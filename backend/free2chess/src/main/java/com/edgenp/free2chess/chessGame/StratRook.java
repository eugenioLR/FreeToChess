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

    /**
     *
     */
    public StratRook() {
        this.moved = false;
    }

    /**
     * Verifica si el movimiento que se quiere realizar es validos
     * @param board
     * @param initPos
     * @param newPos
     * @return
     */
    @Override
    public boolean verifyMove(Board board, int[] initPos, int[] newPos) {
        boolean valid;
        int delta_y = newPos[0] - initPos[0];
        int delta_x = newPos[1] - initPos[1];
        int[] auxPos = initPos.clone();
        if(valid = !Arrays.equals(initPos, newPos) && (delta_x == 0 || delta_y == 0)){
            do{
                auxPos[0] += Math.signum(delta_y);
                auxPos[1] += Math.signum(delta_x);
            }while(!Arrays.equals(auxPos, newPos) && board.getPiece(auxPos).getColor() == -1);
            
            valid = board.getPiece(newPos).getColor() != board.getPiece(initPos).getColor();
        }
        return valid;
    }

    /**
     *
     */
    @Override
    public void confirmMove() {
    }   

    /**
     * Obtiene el nombre de la pieza 
     * @return
     */
    @Override
    public char getName() {
        return 'R';
    }
    
    /**
     *
     * @param board
     * @param initPos
     * @return
     */
    @Override
    public boolean canMove(Board board, int[] initPos) {
        boolean canMove = false;
        int[][] offsets = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int[] auxPos;
        int oponent = board.getPiece(initPos).getColor() == 0 ? 1 : 0;
        
        for (int i = 0; i < offsets.length && !canMove; i++) {
            auxPos = initPos.clone();
            auxPos[0] += offsets[i][0];
            auxPos[1] += offsets[i][1];
            canMove = board.getPiece(auxPos).getColor() == -1 || board.getPiece(auxPos).getColor() == oponent;
        }
        return canMove;
    }

    /**
     *
     * @param board
     * @param initPos
     * @return
     */
    @Override
    public boolean isInDanger(Board board, int[] initPos) {
        return false;
    }   
}
