/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.store;

import com.edgenp.free2chess.user.User;

/**
 *
 * @author eugeniolr
 */
public class Store {

    private static final PaySysAbs paySystem = new PaySysProxy();
     
    private Store() {
    }

    public static Store getInstance() {
        return new Store();
    }

    public void doPayment(User user, double price) {
        paySystem.makePurchase(user.getPaypal_id(), price);
    }

}
