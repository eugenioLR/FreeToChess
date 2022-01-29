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
public class Piece {
    private int color; // 0 white, 1 black
    private int[] pos;
    private PieceStrat pieceType;

    public Piece(int color, int[] pos, PieceStrat pieceType) {
        this.color = color;
        this.pos = pos;
        this.pieceType = pieceType;
    }
    
    public Piece(int color, int[] pos, char type){
        this.color = color;
        this.pos = pos;
        switch(type){
            case 'K' -> {
                this.pieceType = new StratKing();
            }
            
            case 'Q' -> {
                this.pieceType = new StratQueen();
            }
            
            case 'N' -> {
                this.pieceType = new StratKnight();
            }
            
            case 'B' -> {
                this.pieceType = new StratBishop();
            }
            
            case 'R' -> {
                this.pieceType = new StratRook();
            }
            
            case 'P' -> {
                this.pieceType = new StratPawn();
            }
            
            default -> {
                this.pieceType = null;
            }
        }
    }

    public int getColor() {
        return color;
    }
    
    public char getType() {
        char result = '-';
        if(pieceType != null){
            result = pieceType.getName();
        }
        return result;
    }

    public int[] getPos() {
        return pos;
    }
    
    public boolean verifyMove(Board board, int[] posNew){
        return this.pieceType != null && this.pieceType.verifyMove(board, this.pos, posNew);
    }
    
    public boolean move(Board board, int[] posNew){
        boolean valid;
        
        if(valid = this.pieceType != null && this.pieceType.verifyMove(board, this.pos, posNew)){
            this.pos = posNew;
        }
        return valid;
    }
    
    public void forceMove(int[] posNew){
        this.pos = posNew;
    }

    @Override
    public String toString() {
        char colorChar;
        char typeChar;
        colorChar = switch (color) {
            case 0 -> 'w';
            case 1 -> 'b';
            default -> '-';
        };
        
        if(pieceType == null){
            typeChar = '-';
        }else{
            typeChar = pieceType.getName();
        }
        
        
        return "Piece{" + colorChar + typeChar + "}";
    }    
}
