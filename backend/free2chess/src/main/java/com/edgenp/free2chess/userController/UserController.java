/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edgenp.free2chess.userController;

import com.edgenp.free2chess.user.*;
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

    @GetMapping("/users")
    public List<User> getAll(){
        return userServ.getAll();
    }
    
    @GetMapping("/users/{id}")
    public User getById(@PathVariable("id") Integer id){
        return userServ.getById(id);
    }
    
    @PostMapping(value = "/users", produces = MediaType.TEXT_PLAIN_VALUE)
    public void create(@RequestBody User user){
        UserFactory userFactory = new UserFactory();
        User userFinal = userFactory.createUser(user.getName(), user.getPassword(), user.getPaypal_id());
        
        System.out.println(userFinal.toString());
        userServ.create(userFinal);
    }
}
