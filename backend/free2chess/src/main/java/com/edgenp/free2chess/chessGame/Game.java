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
    @JoinColumns({
        @JoinColumn(name="w_player_name", referencedColumnName="name")
    })
    private User w_player;
    
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="b_player_name", referencedColumnName="name")
    })
    private User b_player;
    
    @JsonIgnore
    @OneToMany
    @JoinColumns({
        @JoinColumn(name="\"id_Game\"", referencedColumnName="\"id\"")
    })
    private final List<Board> gameStates = new ArrayList<>();
    
    @Transient
    private int next_player = 0;
    

    public Game() {
    }

    public Game(User w_user, User b_user) {
        this.w_player = w_user;
        this.b_player = b_user;
    }

    public int getId() {
        return id;
    }

    public User getW_user() {
        return w_player;
    }

    public User getB_user() {
        return b_player;
    }

    private void orderStates(){
        this.gameStates.sort((Board o1, Board o2) -> {
            return Integer.compare(o1.getMove_order(), o2.getMove_order());
        });
    }
    
    public List<Board> getGameStates() {
        this.orderStates();
        return gameStates;
    }
    
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
    
    public boolean canMovePiece(int[] pos, int[] target){
        return this.getCurrentBoard().canMovePiece(pos, target);
    }
    
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

    public void forceMove(int[] pos, int[] target) {
        Board current = this.getCurrentBoard();
        Board next = current.clone();
        next.forceMovePiece(pos, target);
        gameStates.add(next);
    }
    
    
}
