/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.user;

import com.edgenp.free2chess.chessGame.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author eugeniolr
 */
@Entity
@Table(name = "\"PendingGames\"")
public class PendingGame implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="pending_game_id_generator")
    private int id;
    
    private boolean acepted;
    
    @ManyToOne
    @JoinColumns({@JoinColumn(name="emiter_name", referencedColumnName="name")})
    private User emiter;
    
    @ManyToOne
    @JoinColumns({@JoinColumn(name="reciever_name", referencedColumnName="name")})
    private User reciever;
    
    @JsonIgnore
    @OneToOne
    @JoinColumns({@JoinColumn(name="\"id_Game\"", referencedColumnName="id")})
    private Game game;

    public PendingGame() {
    }

    public PendingGame(User emiter, User reciever) {
        this.emiter = emiter;
        this.reciever = reciever;
        this.acepted = false;
    }

    public int getId() {
        return id;
    }

    public boolean isAcepted() {
        return acepted;
    }

    public void acept() {
        this.acepted = true;
    }
    
    public void decline() {
        this.acepted = false;
    }

    public User getEmiter() {
        return emiter;
    }

    public User getReciever() {
        return reciever;
    }

    public Game getGame() {
        return game;
    }
    
    public void setGame(Game game) {
        this.game = game;
    }
    
    
    
}
