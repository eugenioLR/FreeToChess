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
    
    /**
     * Obtiene las propiedades del juego con la id "id"
     * @param id
     * @return
     */
    @GetMapping("/game/{id}")
    public Game getGame(@PathVariable("id") Integer id){
        Game game = gameServ.getById(id);
        return game;
    }
    
    /**
     * Obtiene el estado actual de la partida de ajedrez con id "id"
     * @param id
     * @return
     */
    @GetMapping("/game/{id}/board")
    public Board getState(@PathVariable("id") Integer id){
        System.out.println(id);
        Game game = gameServ.getById(id);
        System.out.println("number of states: " + game.getGameStates().size());
        return game.getCurrentBoard();
    }
    
    /**
     * Realiza un movimiento en el tablero con id "id" y devuelve "error" si el movimiento es invalido
     * @param id
     * @param name
     * @param move
     * @return
     */
    @PostMapping(value = "/game/{id}/board", produces = MediaType.TEXT_PLAIN_VALUE)
    public String performMove(@PathVariable("id") Integer id, @RequestParam String name, @RequestBody MoveObj move){
        String status;
        Game game = gameServ.getById(id);
        User user = userServ.getById(name);
        System.out.println("API move: " + move.getInit()[0] + ", " + move.getInit()[1] + " -> " + move.getLast()[0] + ", " + move.getLast()[1]);
        GameCommandInterf command = new BoardMove(game, move.getInit(), move.getLast());
        
        if(command.canMove(user)){
            System.out.println(game.getGameStates().size());
            command.performMove(user);
            System.out.println("Epic move bro");
            boardServ.create(game.getCurrentBoard());
            gameServ.create(game);
            
            if(game.getCurrentBoard().getWinner(game.getNext_player()) == game.getNext_player()){
                status = "you win";
                user.addCoins(200);
                userServ.update(user);
            }else if(game.getCurrentBoard().getWinner(game.getNext_player()) == 2){
                status = "draw";
                user.addCoins(10);
                userServ.update(user);
            }else if(game.getCurrentBoard().getWinner(game.getNext_player()) == -1){
                status = "ok";
            }else{
                status = "you lose";
            }
            
            System.out.println(game.getGameStates().size());
        }else{
            status = "error";
        }
        
        return status;
    }
    
    /**
     * Crea un juego entre dos jugadores
     * @param w_name
     * @param b_name
     * @return
     */
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
