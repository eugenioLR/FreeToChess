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
public class BoardMove implements GameCommandInterf{

    private Game game;
    private int[] init;
    private int[] last;

    /**
     *
     * @param game
     * @param init
     * @param last
     */
    public BoardMove(Game game, int[] init, int[] last) {
        this.game = game;
        this.init = init;
        this.last = last;
    }
    
    /**
     * Verifica si el movimiento que se va a realizar es valido
     * @param user
     * @return
     */
    @Override
    public boolean canMove(User user) {
        return game.canMovePiece(user, init, last);
    }

    /**
     * Realiza un movimiento en el tablero
     * @param user
     */
    @Override
    public void performMove(User user) {
        game.movePiece(init, last);
            
        System.out.println("previous next_player: " + game.getNext_player());
        game.swapNextPlayer();
        System.out.println("previous next_player: " + game.getNext_player());
    }

    @Override
    public boolean verifyMove(User user) {
        return !this.game.wasInCheck();
    }
    
    

    /**
     * Deshace un movimiento en el tablero
     */
    @Override
    public void undoMove() {
        game.forceMove(last, init);
    }

}
