/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.product;

import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author eugeniolr
 */
@Entity
@Table(name = "\"PieceSkinSet\"")
public class PieceSkinSet extends Product{
    /*
    @OneToMany(targetEntity = PieceSkin.class)
    private Set<PieceSkin> pieceSkins;

    public Set<PieceSkin> getPieceSkins() {
        return pieceSkins;
    }

    public void setPieceSkins(Set<PieceSkin> pieceSkins) {
        this.pieceSkins = pieceSkins;
    }
    */
}
