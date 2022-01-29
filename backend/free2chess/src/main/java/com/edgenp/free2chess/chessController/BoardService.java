package com.edgenp.free2chess.chessController;

import com.edgenp.free2chess.chessGame.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author eugeniolr
 */
@Service
public class BoardService {
    @Autowired
    private BoardJPA boardRepo;

    /**
     * Crea una entidad de tipo "GameState" en la base de datos
     * @param board
     */
    public void create(Board board) {
        boardRepo.save(board);
    }
    
    
}
