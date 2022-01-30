/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edgenp.free2chess.userController;

import com.edgenp.free2chess.user.UserFactory;
import com.edgenp.free2chess.chessController.GameService;
import com.edgenp.free2chess.chessGame.Game;
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
    
    //@Autowired
    //private ProductService prodServ;
    
    @Autowired
    private PendingGameService pendGameServ;
    
    @Autowired
    private GameService gameServ;

    /**
     *
     * @return
     */
    @GetMapping("/users")
    public List<User> getAll(){
        return userServ.getAll();
    }
    
    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/users/{id}/products")
    public List<Product> getUserProducts(@PathVariable("id") String id){
        User user = userServ.getById(id);
        return new ArrayList<>(user.getPurchasedProducts());
    }
    
    /**
     *
     * @param id
     */
    @PostMapping("/users/{id}/subscribe")
    public void subscribeUser(@PathVariable("id") String id){
        User user = userServ.getById(id);
        user.subscribe();
        userServ.create(user);
    }
    
    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    public User getById(@PathVariable("id") String id){
        return userServ.getById(id);
    }
    
    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/users/{id}/games/recieved")
    public List<PendingGame> getRecievedChallenges(@PathVariable("id") String id){
        User user = userServ.getById(id);
        return new ArrayList<>(user.getChallenges_opened());        
    }
    
    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/users/{id}/games/emited")
    public List<PendingGame> getEmitedChallenges(@PathVariable("id") String id){
        User user = userServ.getById(id);
        return new ArrayList<>(user.getChallenges_issued());        
    }
    
    /**
     *
     * @param emiter_name
     * @param oponent
     * @return
     */
    @PostMapping("/users/{id}/games")
    public String issueChallenge(@PathVariable("id") String emiter_name, @RequestParam String oponent){
        String status = "ok";
        User emiter = userServ.getById(emiter_name);
        User reciever = userServ.getById(oponent);
        if(emiter != null && reciever != null){
            PendingGame pendGame = new PendingGame(emiter, reciever);
            Game game = new Game(pendGame.getEmiter(), pendGame.getReciever()); 
            pendGame.setGame(game);
            gameServ.create(game);
            pendGameServ.create(pendGame);
        }else{        
            status = "error";
        }
        return status;        
    }
    
    /**
     *
     * @param name_user
     * @param id
     * @param accept
     * @return 
     */
    @PostMapping("/users/{id}/games/recieved")
    public String acceptChallenge(@PathVariable("id") String name_user, @RequestParam int id, @RequestParam boolean accept){
        String status = "declined";
        PendingGame pendGame = pendGameServ.getById(id);
        if(accept){
            pendGame.acept();
            pendGameServ.update(pendGame);
            status = Integer.toString(pendGame.getGame().getId());
        }else{
            pendGame.decline();
        }      
        return status;
    }
    
    
    /**
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/users", produces = MediaType.TEXT_PLAIN_VALUE)
    public String create(@RequestBody User user){
        String status = "ok";
        if(this.getById(user.getName()) == null){
            UserFactory userFactory = new UserFactory();
            User userFinal = userFactory.createUser(user.getName(), user.getEmail(), user.getPassword(), user.getPaypal_id());

            System.out.println(userFinal.toString());
            
            userServ.create(userFinal);
        }else{
            status = "error";
        }
        return status;
    }
}
