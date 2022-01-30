/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.chessGame;

import com.edgenp.free2chess.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 *
 * @author eugeniolr
 */
@Entity
@Table(name = "\"Game\"")
public class Game implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="game_id_generator")
    private int id;
    
    @ManyToOne
    @JoinColumns({@JoinColumn(name="w_player_name", referencedColumnName="name")})
    private User w_player;
    
    @ManyToOne
    @JoinColumns({@JoinColumn(name="b_player_name", referencedColumnName="name")})
    private User b_player;
    
    @JsonIgnore
    @OneToMany
    @JoinColumns({@JoinColumn(name="\"id_Game\"", referencedColumnName="\"id\"")})
    private final List<Board> gameStates = new ArrayList<>();
    
    @Transient
    private int next_player = 0;
    
    @Transient
    private String state = "playing";
    
    /**
     *
     */
    public Game() {
    }

    /**
     *
     * @param w_user
     * @param b_user
     */
    public Game(User w_user, User b_user) {
        this.w_player = w_user;
        this.b_player = b_user;
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
    public User getW_user() {
        return w_player;
    }

    /**
     *
     * @return
     */
    public User getB_user() {
        return b_player;
    }

    private void orderStates(){
        this.gameStates.sort((Board o1, Board o2) -> {
            return Integer.compare(o1.getMove_order(), o2.getMove_order());
        });
    }
    
    /**
     *
     * @return
     */
    public List<Board> getGameStates() {
        this.orderStates();
        return gameStates;
    }
    
    /**
     *
     * @return
     */
    public Board getCurrentBoard(){
        Board board;
        this.orderStates();
        if(this.gameStates.isEmpty()){
            board = new Board(this, 0);
        }else{
            board = this.gameStates.get(this.gameStates.size() - 1);
        }
        return board;
    }

    public int getNext_player() {
        return next_player;
    }
    
    
    
    /**
     * Verifica si el movimiento que se quiere realizar es validos
     * @param user
     * @param pos
     * @param target
     * @return
     */
    public boolean canMovePiece(User user, int[] pos, int[] target){
        boolean valid = this.getCurrentBoard().canMovePiece(pos, target);
        boolean white_turn = user.getName().equals(w_player.getName()) && next_player == 0;
        boolean black_turn = user.getName().equals(b_player.getName()) && next_player == 1;
        
        valid = valid && (white_turn || black_turn);
        
        return valid;
    }
    
    /**
     * Realiza un movimiento sobre el tablero
     * @param pos
     * @param target
     * @return
     */
    public boolean movePiece(int[] pos, int[] target){
        boolean success;
        Board current = this.getCurrentBoard();
        Board next = current.clone();
        System.out.println(next.getBoardStr());
        if(success = next.movePiece(pos, target)){
            System.out.println(next.getBoardStr());
            next.incrementMove_order();
            gameStates.add(next);
            next_player = next_player == 0 ? 1 : 0; 
        }else{
            System.out.println("oof");
        }
        return success;
    }

    /**
     * Mueve una pieza a una posicion sin comprobar si este movimiento es legal
     * @param pos
     * @param target
     */
    public void forceMove(int[] pos, int[] target) {
        Board current = this.getCurrentBoard();
        Board next = current.clone();
        next.forceMovePiece(pos, target);
        gameStates.add(next);
    }
    
    
    
}
