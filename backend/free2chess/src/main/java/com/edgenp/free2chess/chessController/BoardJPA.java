package com.edgenp.free2chess.chessController;

import com.edgenp.free2chess.chessGame.Board;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author eugeniolr
 */
public interface BoardJPA extends JpaRepository<Board, Integer> {
    
}
