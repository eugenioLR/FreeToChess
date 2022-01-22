/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.user;

/**
 *
 * @author eugeniolr
 */
public class UserBasic extends User{
    public UserBasic(String name, String password, String salt, int paypal_id) {
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.paypal_id = paypal_id;
    }
}
