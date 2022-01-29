package com.edgenp.free2chess.chessController;

import com.edgenp.free2chess.chessGame.*;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author eugeniolr
 */
@Service
public class GameService {
    @Autowired
    private GameJPA gameRepo;
    
    /**
     * Obtiene el juego con id "id"
     * @param id
     * @return
     */
    public Game getById(Integer id){
        Optional<Game> optional = gameRepo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
    
    /**
     * Crea un juego en la base de datos
     * @param game
     */
    public void create(Game game){
        gameRepo.save(game);
    }
}
