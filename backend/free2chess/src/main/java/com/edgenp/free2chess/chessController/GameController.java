/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edgenp.free2chess.chessController;

import com.edgenp.free2chess.chessGame.*;
import com.edgenp.free2chess.user.User;
import com.edgenp.free2chess.userController.UserService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author eugeniolr
 */
@RestController
public class GameController {
    private Map<Integer, MoveInvoker> openGames = new HashMap<>();
    
    @Autowired
    private GameService gameServ;
    
    @Autowired
    private BoardService boardServ;
    
    @Autowired
    private UserService userServ;
    
    @GetMapping("/game/{id}/board")
    public Board getState(@PathVariable("id") Integer id){
        System.out.println(id);
        Game game = gameServ.getById(id);
        System.out.println("number of states: " + game.getGameStates().size());
        return game.getCurrentBoard();
    }
    
    @PostMapping(value = "/game/{id}/board", produces = MediaType.TEXT_PLAIN_VALUE)
    public String performMove(@PathVariable("id") Integer id, @RequestBody MoveObj move){
        String status = "ok";
        Game game = gameServ.getById(id);
        System.out.println("API move: " + move.getInit()[0] + ", " + move.getInit()[1] + " -> " + move.getLast()[0] + ", " + move.getLast()[1]);
        GameCommandInterf command = new BoardMove(game, move.getInit(), move.getLast());
        
        if(command.canMove()){
            System.out.println(game.getGameStates().size());
            command.performMove();
            System.out.println("Epic move bro");
            boardServ.create(game.getCurrentBoard());
            gameServ.create(game);
            
            System.out.println(game.getGameStates().size());
        }else{
            status = "error";
        }
        
        return status;
    }
    
    @PostMapping(value = "/game", produces = MediaType.TEXT_PLAIN_VALUE)
    public String createGame(@RequestParam String w_name, @RequestParam String b_name){
        User w_user = userServ.getById(w_name);
        User b_user = userServ.getById(b_name);
        Game game = new Game(w_user, b_user);
        System.out.println("game id: " + game.getId());
        
        openGames.put(game.getId(), new MoveInvoker());
        
        gameServ.create(game);
        
        return Integer.toString(game.getId());
    }
    
    
}
