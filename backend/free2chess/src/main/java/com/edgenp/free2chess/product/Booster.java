/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.product;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author eugeniolr
 */
@Entity
@Table(name = "\"Booster\"")
public class Booster extends Product{

    /**
     *
     */
    public Booster() {
        super();
    }

}
