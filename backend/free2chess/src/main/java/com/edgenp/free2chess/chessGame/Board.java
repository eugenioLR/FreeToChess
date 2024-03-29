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
        
        //DEBUG ONLY, DELETE WHEN DONE
        //this.board_str = "bR,--,bB,bQ,bK,bB,bN,bR;bP,bP,bP,bP,--,--,--,bP;--,--,bN,--,--,wQ,bP,--;--,--,--,--,bP,--,--,--;--,--,wB,--,wP,--,--,--;--,--,--,--,--,--,--,--;wP,wP,wP,wP,--,wP,wP,wP;wR,wN,wB,--,wK,--,wN,wR";
        
        
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
    public int getId() {
        return id;
    }
    
    
    /**
     *
     * @return
     */
    @JsonIgnore
    public Piece[][] getBoardPieces() {
        return boardPieces;
    }
    
    /**
     *
     * @param y
     * @param x
     * @return
     */
    @JsonIgnore
    public Piece getPiece(int y, int x){
        Piece result = null;
        if(x >= 0 && x < 8 && y >= 0 && y < 8){
            result = boardPieces[y][x];
        }else{
            result = new Piece(-2, new int[]{y,x}, null);
        }
        return result;
    }
    
    /**
     *
     * @param pos
     * @return
     */
    @JsonIgnore
    public Piece getPiece(int[] pos){
        return this.getPiece(pos[0], pos[1]);
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
     * @param color
     * @return
     */
    public boolean canMovePiece(int[] pos, int[] target, int color){
        if(this.boardPieces[0][0] == null){
            this.boardPieces = Board.strBoardToPieces(this.board_str);
        }
        
        return this.boardPieces[pos[0]][pos[1]].verifyMove(this, target);
    }
    
    /**
     * Realiza un movimiento sobre el tablero
     * @param pos
     * @param target
     */
    public void movePiece(int[] pos, int[] target){
        System.out.println(this.boardPieces[pos[0]][pos[1]].toString());
        System.out.println(this.boardPieces[target[0]][target[1]].toString());
        
        this.boardPieces[pos[0]][pos[1]].move(this, target);
        this.boardPieces[target[0]][target[1]] = this.boardPieces[pos[0]][pos[1]];
        this.boardPieces[pos[0]][pos[1]] = new Piece(-1, pos, null);
        
        this.board_str = Board.piecesToStrBoard(boardPieces);
    }
    
    /**
     * Mueve una pieza a una posicion sin comprobar si este movimiento es legal
     * @param pos
     * @param target
     */
    public void forceMovePiece(int[] pos, int[] target){
        this.boardPieces[pos[0]][pos[1]].forceMove(target);
    }
    
    private Piece findKing(int color){
        Piece king = null, aux;
        boolean kingFound = false;
        for(int i = 0; i < boardPieces.length && !kingFound; i++){
            for(int j = 0; j < boardPieces[0].length && !kingFound; j++){
                aux = boardPieces[i][j];
                if(kingFound = aux.getColor() == color && aux.getType() == 'K'){
                    king = aux;
                }
            }
        }
        return king;
    }
    
    private boolean kingCanMove(int color){
        boolean canMove = false;
        
        Piece king = this.findKing(color);
        
        if(king != null){
            canMove = king.canMove(this);
        }
        
        return canMove;
    }
    
    /**
     * Comprueba si el rey se encuentra en jaque
     * @param color
     * @return
     */
    public boolean inCheck(int color){
        boolean inCheck = false;
        
        Piece king = this.findKing(color);
        
        if(king != null){
            inCheck = king.isInDanger(this);
        }
        
        return inCheck;
    }
    
    
    /**
     * Devuelve el jugador que ha ganado la partida, si las blancas ganan se devolvera
     * un 0, si ganan las negras se devolvera un 1, si hay empate se devolvera un 2 y
     * si la partida no ha terminado todavia se devolvera un -1
     * @param player
     * @return
     */
    @JsonIgnore
    public int getWinner(int player){
        int winner = -1;
        int newPlayer = player == 0 ? 1 : 0;
        
        if(this.findKing(winner) == null) {
            winner = player;
        }else{
            // Checkmate test
            if(this.kingCanMove(newPlayer)){
                System.out.println("haha your king can't move");
                winner = this.inCheck(newPlayer) ? player : 2;
                
                if(this.inCheck(newPlayer)){
                    System.out.println("check mate :sunglasses:");
                }
                
                if(this.inCheck(player)){
                    System.out.println("check mate :sunglasses:");
                }
            }

            boolean movesLeft = false;
            // Draw test
            if(winner == 2){
                for(int i = 0; i < boardPieces.length && !movesLeft; i++){
                    for(int j = 0; j < boardPieces[0].length && !movesLeft; j++){
                        movesLeft = boardPieces[i][j].canMove(this);
                    }
                }

                if(movesLeft){
                    winner = -1;
                }
            }
        }
        return winner;
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
