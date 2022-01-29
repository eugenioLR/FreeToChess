/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edgenp.free2chess.chessController;

import com.edgenp.free2chess.chessGame.Board;
import java.util.Optional;
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

    void create(Board board) {
        boardRepo.save(board);
    }
    
    
}
