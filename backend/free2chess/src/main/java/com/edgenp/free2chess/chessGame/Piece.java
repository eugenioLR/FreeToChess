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

    /**
     *
     * @param color
     * @param pos
     * @param pieceType
     */
    public Piece(int color, int[] pos, PieceStrat pieceType) {
        this.color = color;
        this.pos = pos;
        this.pieceType = pieceType;
    }
    
    /**
     *
     * @param color
     * @param pos
     * @param type
     */
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

    /**
     *
     * @return
     */
    public int getColor() {
        return color;
    }
    
    /**
     *
     * @return
     */
    public char getType() {
        char result = '-';
        if(pieceType != null){
            result = pieceType.getName();
        }
        return result;
    }

    /**
     *
     * @return
     */
    public int[] getPos() {
        return pos;
    }
    
    /**
     * Verifica si el movimiento que se quiere realizar es validos
     * @param board
     * @param posNew
     * @return
     */
    public boolean verifyMove(Board board, int[] posNew){
        boolean valid = false;
        if(this.pieceType != null && this.pieceType.canMove(board, this.pos.clone())){
            System.out.println("Yes, this piece of type " + this.getType() + " can move");
            valid = this.pieceType.verifyMove(board, this.pos.clone(), posNew);
        }else{
            System.out.println("This piece of type " + this.getType() + " cannot move");
        }
        return valid;
    }
    
    /**
     * Realiza un movimiento sobre el tablero
     * @param board
     * @param posNew
     * @return
     */
    public void move(Board board, int[] posNew){
        this.pos = posNew;
    }
    
    /**
     * Mueve una pieza a una posicion sin comprobar si este movimiento es legal
     * @param posNew
     */
    public void forceMove(int[] posNew){
        this.pos = posNew;
    }

    /**
     *
     * @param board
     * @return
     */
    public boolean canMove(Board board) {
        boolean check = false;
        if(pieceType != null){
            check = pieceType.canMove(board, this.pos.clone());
        }
        return check;
    }

    /**
     *
     * @param board
     * @return
     */
    public boolean isInDanger(Board board) {
        boolean check = false;
        if(pieceType != null){
            check = pieceType.isInDanger(board, this.pos.clone());
        }
        return check;
    }
    
    /**
     *
     * @return
     */
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
