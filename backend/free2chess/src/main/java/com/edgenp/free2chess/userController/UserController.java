/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edgenp.free2chess.userController;

import com.edgenp.free2chess.chessController.GameController;
import com.edgenp.free2chess.user.UserFactory;
import com.edgenp.free2chess.chessController.GameService;
import com.edgenp.free2chess.chessGame.Game;
import com.edgenp.free2chess.product.Product;
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
     * Obtiene todos los usuarios
     * @return
     */
    @GetMapping("/users")
    public List<User> getAll(){
        return userServ.getAll();
    }
    
    
    /**
     * Obtiene los 10 usuarios con mejor elo de la base de datos
     * @return
     */
    @GetMapping("/users/ranking")
    public List<User> getRanging(){
        return userServ.getAllOrdered();
    }
    
    /**
     * Obtiene los productos comprados por un usuario
     * @param id
     * @return
     */
    @GetMapping("/users/{id}/products")
    public List<Product> getUserProducts(@PathVariable("id") String id){
        User user = userServ.getById(id);
        return new ArrayList<>(user.getPurchasedProducts());
    }
    
    /**
     * Realiza la subcripcion de un usuario
     * @param id
     */
    @PostMapping("/users/{id}/subscribe")
    public void subscribeUser(@PathVariable("id") String id){
        User user = userServ.getById(id);
        user.subscribe();
        userServ.create(user);
    }
    
    /**
     * Obtiene la informacion de un usuario
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    public User getById(@PathVariable("id") String id){
        return userServ.getById(id);
    }
    
    /**
     * Muestra las peticiones de juegos recibidos
     * @param id
     * @return
     */
    @GetMapping("/users/{id}/games/received")
    public List<PendingGame> getRecievedChallenges(@PathVariable("id") String id){
        User user = userServ.getById(id);
        return new ArrayList<>(user.getChallenges_opened());        
    }
    
    /**
     * Muestra las peticions de juegos enviados
     * @param id
     * @return
     */
    @GetMapping("/users/{id}/games/emited")
    public List<PendingGame> getEmitedChallenges(@PathVariable("id") String id){
        User user = userServ.getById(id);
        return new ArrayList<>(user.getChallenges_issued());        
    }
    
    /**
     * Muestra los juegos abiertos
     * @param id
     * @return
     */
    @GetMapping("/users/{id}/games/openGames")
    public List<PendingGame> getOpenChallenges(@PathVariable("id") String id){        
        return pendGameServ.getByUser(id);        
    }
    
    
    /**
     * Crea un juego manualmente entre dos jugadores, solo se deberia usar para hacer
     * debug.
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
            Game game = new Game(pendGame.getEmiter(), pendGame.getReceiver()); 
            pendGame.setGame(game);
            gameServ.create(game);
            GameController.addOpenGame(game.getId());
            pendGameServ.create(pendGame);
        }else{        
            status = "error";
        }
        return status;        
    }
    
    /**
     * Acepta o rechaza una invitación a un juego con otro usuario
     * @param name_user
     * @param id
     * @param accept
     * @return 
     */
    @PostMapping("/users/{id}/games/received")
    public String acceptChallenge(@PathVariable("id") String name_user, @RequestParam int id, @RequestParam boolean accept){
        String status = "declined";
        PendingGame pendGame = pendGameServ.getById(id);
        if(accept){
            pendGame.acept();
            pendGameServ.update(pendGame);
            status = Integer.toString(pendGame.getGame().getId());
        }else{
            pendGame.decline();
            pendGameServ.removeCascade(pendGame);
        }      
        return status;
    }
    
    
    /**
     * Crea un usuario dado el nombre, email, contraseña y ID de paypal.
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
