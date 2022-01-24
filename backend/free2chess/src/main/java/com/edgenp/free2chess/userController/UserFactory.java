/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edgenp.free2chess.userController;

import com.edgenp.free2chess.user.*;
import java.util.Random;

import java.math.BigInteger; 
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author eugeniolr
 */
public class UserFactory {
    public UserFactory() {
    }
    
    public User createUser(String name, String password, int paypal_id){
        Random rand = new Random();
        
        byte[] randArray = new byte[10];
        rand.nextBytes(randArray);
        String salt = new String(randArray);
        
        String pass_hashed = encryptPass(password + salt);
        
        User user = new User(name, pass_hashed, salt, paypal_id);
        
        return user;
    }
    
    private String encryptPass(String input){
        String result;
        
        try {
            result = toHexString(getSHA(input));
        } catch (NoSuchAlgorithmException ex) {
            result = input;
        }
        
        return result;
    }
    
    private byte[] getSHA(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
    
    private String toHexString(byte[] hash){
        BigInteger number = new BigInteger(1, hash);
        
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while(hexString.length() < 32){
            hexString.insert(0, '0');
        }
        
        return hexString.toString();
    }
    
}
