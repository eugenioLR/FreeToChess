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

    /**
     *
     */
    public StratPawn() {
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
        boolean valid, vertLim;
        int delta_y = newPos[0] - initPos[0];
        int delta_x = newPos[1] - initPos[1];
        
        if(valid = !Arrays.equals(initPos, newPos) && Math.abs(delta_x) < 2){
            int limit = moved ? 1 : 2;
            
            if(board.getPiece(initPos).getColor() == 0){
                vertLim = delta_y <= 0 && delta_y >= -limit;
            }else{
                vertLim = delta_y >= 0 && delta_y <= limit;
            }           
            
            valid = vertLim && board.getPiece(initPos).getColor() != board.getPiece(newPos).getColor();
            if(board.getPiece(newPos).getColor() == -1){
                valid = valid && delta_x == 0;
            }else{
                valid = valid && Math.abs(delta_x) == 1;
            } 
        }
        return valid;
    }

    /**
     *
     */
    @Override
    public void confirmMove() {
        moved = true;
    }   
    
    /**
     * Obtiene el nombre de la pieza 
     * @return
     */
    @Override
    public char getName() {
        return 'P';
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
        int oponent = board.getPiece(initPos).getColor() == 0 ? 1 : 0;
        int[][] offsets = {{1, 1}, {1, -1}};
        int[] aux;
        
        if(board.getPiece(initPos).getColor() == 0){
            for (int[] offset : offsets) {
                offset[0] = -1;
            }
        }
        
        for (int i = 0; i < offsets.length && !canMove; i++) {
            aux = initPos;
            aux[0] += offsets[i][0];
            aux[1] += offsets[i][1];
            canMove = board.getPiece(aux).getColor() == oponent;
        }
        
        aux = initPos;
        aux[0] += offsets[0][0];
        
        canMove = !canMove && board.getPiece(aux).getColor() == -1;
        
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
