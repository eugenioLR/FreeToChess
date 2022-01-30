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
public interface PieceStrat {

    /**
     * Verifica si el movimiento que se quiere realizar es validos
     * @param board
     * @param pos_init
     * @param pos_new
     * @return
     */
    public boolean verifyMove(Board board, int[] pos_init, int[] pos_new);

    /**
     * 
     */
    public void confirmMove();

    /**
     * Obtiene el nombre de la pieza 
     * @return
     */
    public char getName();

    public boolean canMove(Board board, int[] pos_init);

    public boolean isInDanger(Board board, int[] pos_init);
}
