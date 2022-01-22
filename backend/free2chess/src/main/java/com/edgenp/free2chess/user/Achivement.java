/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.user;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author eugeniolr
 */
@Entity
@Table(name = "\"Achivement\"")
public class Achivement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private int exp_reward;
    private int coin_reward;

    public Achivement() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExp_reward() {
        return exp_reward;
    }

    public void setExp_reward(int exp_reward) {
        this.exp_reward = exp_reward;
    }

    public int getCoin_reward() {
        return coin_reward;
    }

    public void setCoin_reward(int coin_reward) {
        this.coin_reward = coin_reward;
    }
}
