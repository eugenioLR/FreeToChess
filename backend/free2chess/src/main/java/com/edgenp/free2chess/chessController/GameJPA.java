/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edgenp.free2chess.chessController;

import com.edgenp.free2chess.chessGame.Game;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author eugeniolr
 */
public interface GameJPA extends JpaRepository<Game, Integer> {
    
}
