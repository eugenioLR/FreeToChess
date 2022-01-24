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
@Table(name = "\"BoardSkin\"")
public class BoardSkin extends Skin{
    
    private String texture_path;
    
    public BoardSkin() {
        super();
    }
    
    public String getTexture_path() {
        return texture_path;
    }

    public void setTexture_path(String texture_path) {
        this.texture_path = texture_path;
    }
}
