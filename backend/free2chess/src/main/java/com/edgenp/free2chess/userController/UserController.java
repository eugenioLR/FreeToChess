/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edgenp.free2chess.userController;

import com.edgenp.free2chess.product.Product;
import com.edgenp.free2chess.storeController.ProductService;
import com.edgenp.free2chess.user.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author eugeniolr
 */
@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userServ;
    
    @Autowired
    private ProductService prodServ;

    @GetMapping("/users")
    public List<User> getAll(){
        return userServ.getAll();
    }
    
    @GetMapping("/users/{id}/products")
    public List<Product> getUserProducts(@PathVariable("id") String id){
        User user = userServ.getById(id);
        return new ArrayList<>(user.getPurchasedProducts());
    }
    
    @GetMapping("/users/{id}/subscribe")
    public void subscribeUser(@PathVariable("id") String id){
        User user = userServ.getById(id);
        user.subscribe();
        userServ.create(user);
    }
    
    @GetMapping("/users/{id}")
    public User getById(@PathVariable("id") String id){
        return userServ.getById(id);
    }
    
    @PostMapping(value = "/users", produces = MediaType.TEXT_PLAIN_VALUE)
    public void create(@RequestBody User user) throws ExistingUserException{
        if(this.getById(user.getName()) == null){
            UserFactory userFactory = new UserFactory();
            User userFinal = userFactory.createUser(user.getName(), user.getPassword(), user.getPaypal_id());

            System.out.println(userFinal.toString());
            userServ.create(userFinal);
        }else{
            throw new ExistingUserException(user.getName());
        }
    }
}


class ExistingUserException extends Exception { 
    public ExistingUserException(String username) {
        super("The user \"" + username + "\" already exists");
    }
}
