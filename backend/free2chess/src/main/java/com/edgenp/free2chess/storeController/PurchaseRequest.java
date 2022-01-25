/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edgenp.free2chess.storeController;

import com.edgenp.free2chess.product.Product;
import com.edgenp.free2chess.user.User;

/**
 *
 * @author eugeniolr
 */
public class PurchaseRequest {
    private User user = new User();
    private Product product = new Product();

    public PurchaseRequest() {
    }

    public PurchaseRequest(User buyer, Product prod) {
        this.user = buyer;
        this.product = prod;
    }

    public User getBuyer() {
        return user;
    }

    public Product getProd() {
        return product;
    }   
}
