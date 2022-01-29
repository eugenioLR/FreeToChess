/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.chessGame;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author eugeniolr
 */
@Entity
@Table(name = "\"GameState\"")
public class Board implements Serializable, Cloneable, Comparable<Board> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="gamestate_id_generator")
    private int id;
    
    @ManyToOne
    @JoinColumns({@JoinColumn(name="\"id_Game\"", referencedColumnName="id")})
    private Game game;
    
    private int move_order;
    
    private String board_str = "";
    
    @JsonIgnore
    @Transient
    private Piece[][] boardPieces = new Piece[8][8];

    /**
     *
     */
    public Board() {
    }

    /**
     *
     * @param game
     * @param move_order
     */
    public Board(Game game, int move_order) {
        this.board_str = "bR,bN,bB,bQ,bK,bB,bN,bR;"+
                         "bP,bP,bP,bP,bP,bP,bP,bP;"+
                         "--,--,--,--,--,--,--,--;"+
                         "--,--,--,--,--,--,--,--;"+
                         "--,--,--,--,--,--,--,--;"+
                         "--,--,--,--,--,--,--,--;"+
                         "wP,wP,wP,wP,wP,wP,wP,wP;"+
                         "wR,wN,wB,wQ,wK,wB,wN,wR";
        this.boardPieces = Board.strBoardToPieces(board_str);
        this.game = game;
        this.move_order = move_order;
    }
    
    /**
     *
     * @param boardPieces
     * @param game
     * @param move_order
     */
    public Board(Piece[][] boardPieces, Game game, int move_order){
        this.boardPieces = boardPieces;
        this.board_str = Board.piecesToStrBoard(boardPieces);
        this.game = game;
        this.move_order = move_order;
    }
    
    /**
     * Convierte una string representado las piezas de un tablero a un array de objetos de clase "Piece"
     * @param boardStr
     * @return
     */
    public static Piece[][] strBoardToPieces(String boardStr){
        Piece[][] result = new Piece[8][8];
        String[] rowArray = boardStr.split(";"), slotArray;
        String slot;
        int color;
        for(int i = 0; i < 8; i++){
            slotArray = rowArray[i].split(",");
            for(int j = 0; j < 8; j++){
                slot = slotArray[j];
                switch(slot.charAt(0)){
                    case 'b' -> {
                        color = 1;
                    }
                    
                    case 'w' -> {
                        color = 0;
                    }
                    
                    default -> {
                        color = -1;
                    }
                }
                //color = slot.charAt(0) == 'b' ? 1 : 0;
                result[i][j] = new Piece(color, new int[]{i, j}, slot.charAt(1));
            }
        }
        return result;
    }
    
    /**
     * Convierte un array de objetos de clase "Piece" a una string representado las piezas de un tablero
     * @param boardPieces
     * @return
     */
    public static String piecesToStrBoard(Piece[][] boardPieces){
        String result = "";
        char color;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                //color = boardPieces[i][j].getColor() == 0 ? 'w' : 'b';
                switch(boardPieces[i][j].getColor()){
                    case 0 -> {
                        color = 'w';
                    }
                    
                    case 1 -> {
                        color = 'b';
                    }
                    
                    default -> {
                        color = '-';
                    }
                }
                result += "" + color + boardPieces[i][j].getType();
                
                if(i!=7 || j!=7){
                    result += j==7 ? ";" : ",";
                }
            }
        }
        return result;
    }

    /**
     *
     * @return
     */
    public Piece[][] getBoardPieces() {
        return boardPieces;
    }
    
    /**
     *
     * @param y
     * @param x
     * @return
     */
    public Piece getPiece(int y, int x){
        Piece result = null;
        if(x >= 0 && x < 8 && y >= 0 && y < 8){
            result = boardPieces[y][x];
        }
        return result;
    }
    
    /**
     *
     * @param pos
     * @return
     */
    public Piece getPiece(int[] pos){
        Piece result = null;
        if(pos[0] >= 0 && pos[0] < 8 && pos[1] >= 0 && pos[1] < 8){
            return boardPieces[pos[0]][pos[1]];
        }
        return result;
    }

    /**
     *
     * @return
     */
    public int getMove_order() {
        return move_order;
    }
    
    /**
     *
     */
    public void incrementMove_order() {
        this.move_order++;
    }

    /**
     *
     * @return
     */
    public String getBoardStr() {
        return board_str;
    }
    
    /**
     * Verifica si el movimiento que se quiere realizar es validos
     * @param pos
     * @param target
     * @return
     */
    public boolean canMovePiece(int[] pos, int[] target){
        if(this.boardPieces[0][0] == null){
            this.boardPieces = Board.strBoardToPieces(this.board_str);
        }
        
        return this.boardPieces[pos[0]][pos[1]].verifyMove(this, target);
    }
    
    /**
     * Realiza un movimiento sobre el tablero
     * @param pos
     * @param target
     * @return
     */
    public boolean movePiece(int[] pos, int[] target){
        System.out.println(this.boardPieces[pos[0]][pos[1]].toString());
        System.out.println(this.boardPieces[target[0]][target[1]].toString());
        boolean valid = this.boardPieces[pos[0]][pos[1]].verifyMove(this, target);
        if(valid){
            this.boardPieces[pos[0]][pos[1]].move(this, target);
            this.boardPieces[target[0]][target[1]] = this.boardPieces[pos[0]][pos[1]];
            this.boardPieces[pos[0]][pos[1]] = new Piece(-1, pos, null);
            this.board_str = Board.piecesToStrBoard(boardPieces);
        }else{
            System.out.println("oof");
        }
        return valid;
    }
    
    /**
     * Mueve una pieza a una posicion sin comprobar si este movimiento es legal
     * @param pos
     * @param target
     */
    public void forceMovePiece(int[] pos, int[] target){
        this.boardPieces[pos[0]][pos[1]].forceMove(target);
    }
    
    /**
     *
     * @return
     */
    @Override
    public Board clone(){
        return new Board(this.boardPieces, this.game, this.move_order);
    }

    /**
     *
     * @param t
     * @return
     */
    @Override
    public int compareTo(Board t) {
        return (int) Math.signum(t.move_order - this.move_order);
    }
    
    
}
