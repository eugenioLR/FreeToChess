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
public class PaySysPaypal implements PaySysAbs{

    /**
     *
     */
    public PaySysPaypal() {
    }
    
    private boolean paypalId_is_valid(int paypalId){
        return paypalId >= 0;
    }
    
    private void doPayment(int paypalId, double amount){
        System.out.println("User with paypalID [" + paypalId + "] made a transaction of " + amount + " $");
    }

    /**
     * Realiza una transaccion a traves de paypal (simulada con un print)
     * @param paypalId
     * @param amount
     * @return
     */
    @Override
    public boolean makePurchase(int paypalId, double amount) {
        boolean successful;
        if(successful = paypalId_is_valid(paypalId)){
            doPayment(paypalId, amount);
        }
        return successful;
    }    
}
