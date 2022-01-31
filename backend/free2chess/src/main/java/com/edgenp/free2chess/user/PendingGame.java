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
    @JoinColumns({@JoinColumn(name="receiver_name", referencedColumnName="name")})
    private User receiver;
    
    @OneToOne
    @JoinColumns({@JoinColumn(name="\"id_Game\"", referencedColumnName="id")})
    private Game game;

    /**
     *
     */
    public PendingGame() {
    }

    /**
     *
     * @param emiter
     * @param reciever
     */
    public PendingGame(User emiter, User reciever) {
        this.emiter = emiter;
        this.receiver = reciever;
        this.acepted = false;
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
    public boolean isAcepted() {
        return acepted;
    }

    /**
     * Acepta la peticion
     */
    public void acept() {
        this.acepted = true;
    }
    
    /**
     * Rechaza la peticion
     */
    public void decline() {
        this.acepted = false;
    }

    /**
     *
     * @return
     */
    public User getEmiter() {
        return emiter;
    }

    /**
     *
     * @return
     */
    public User getReceiver() {
        return receiver;
    }

    /**
     *
     * @return
     */
    public Game getGame() {
        return game;
    }
    
    /**
     *
     * @param game
     */
    public void setGame(Game game) {
        this.game = game;
    }
    
    
    
}
