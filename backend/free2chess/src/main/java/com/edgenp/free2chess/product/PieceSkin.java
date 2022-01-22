/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.product;

import javax.persistence.*;

/**
 *
 * @author eugeniolr
 */
@Entity
@Table(name = "\"PieceSkin\"")
public class PieceSkin extends Skin{
    private String texture_path;
    
    //@ManyToOne
    //@JoinColumn(name = "id_PieceSkinSet")
    //private PieceSkinSet skinSet;
}
