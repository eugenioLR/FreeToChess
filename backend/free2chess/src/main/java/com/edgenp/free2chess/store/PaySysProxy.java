/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.store;

/**
 *
 * @author eugeniolr
 */
public class PaySysProxy implements PaySysAbs{

    /**
     * Realiza una compra a traves de un proxy
     * @param paypal_id
     * @param amount
     * @return
     */
    @Override
    public boolean makePurchase(int paypal_id, double amount) {
        PaySysPaypal realSystem = new PaySysPaypal();
        return realSystem.makePurchase(paypal_id, amount);
    }

}
