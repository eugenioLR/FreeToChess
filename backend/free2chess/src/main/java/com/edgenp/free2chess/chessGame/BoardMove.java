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
public class BoardMove implements GameCommandInterf{

    private Game game;
    private int[] init;
    private int[] last;

    public BoardMove(Game game, int[] init, int[] last) {
        this.game = game;
        this.init = init;
        this.last = last;
    }
    
    @Override
    public boolean canMove() {
        return game.canMovePiece(init, last);
    }

    @Override
    public void performMove() {
        if(this.canMove()){
            if(game.movePiece(init, last)){
                System.out.println(":)");
            }else{
                System.out.println(":)");
            }
        }else{
            System.out.println("oof");
        }
    }

    @Override
    public void undoMove() {
        game.forceMove(last, init);
    }

}
