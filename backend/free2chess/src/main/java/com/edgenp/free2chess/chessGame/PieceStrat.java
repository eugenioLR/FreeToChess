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
    public boolean verifyMove(Board board, int[] pos_init, int[] pos_new);
    public void confirmMove();
    public char getName();
}
