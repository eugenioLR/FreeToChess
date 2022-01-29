/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.store;

import com.edgenp.free2chess.product.*;
import java.util.*;

/**
 *
 * @author eugeniolr
 */
public abstract class ProductConstructor {

    /**
     *
     */
    protected List<Product> packContents = new ArrayList<>();

    /**
     *
     */
    protected ProductPack pack = null;
    
    /**
     *
     */
    public abstract void wipePack();
    
    /**
     *
     * @param rarity
     */
    public abstract void selectByRarity(char rarity);
    
    /**
     *
     * @param amount
     */
    public abstract void selectAmount(int amount);
    
    /**
     *
     * @return
     */
    public abstract ProductPack getPack();
    
}
