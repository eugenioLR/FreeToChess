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
public class StratKnight implements PieceStrat{

    /**
     * Verifica si el movimiento que se quiere realizar es validos
     * @param board
     * @param initPos
     * @param newPos
     * @return
     */
    @Override
    public boolean verifyMove(Board board, int[] initPos, int[] newPos) {
        int delta_y = newPos[0] - initPos[0];
        int delta_x = newPos[1] - initPos[1];
        System.out.println(delta_x + "," + delta_y);
        System.out.println("color at [" + initPos[0] + "," + initPos[1] + "]: " + board.getPiece(initPos).getColor());
        System.out.println("color at [" + newPos[0] + "," + newPos[1] + "]: " + board.getPiece(newPos).getColor());
        return delta_x*delta_x + delta_y*delta_y == 5 && board.getPiece(initPos).getColor() != board.getPiece(newPos).getColor();
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
        return 'N';
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
        int[][] offsets = {{1, 2}, {2, 1}, {-1, 2}, {-2, 1}, {-1, -2}, {-2, -1}, {1, -2}, {2, -1}};
        int[] aux;
        
        int oponent = board.getPiece(initPos).getColor() == 0 ? 1 : 0;
        
        for (int i = 0; i < offsets.length && !canMove; i++) {
            aux = initPos;
            aux[0] += offsets[i][0];
            aux[1] += offsets[i][1];
            canMove = board.getPiece(aux).getColor() == -1 || board.getPiece(aux).getColor() == oponent;
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
