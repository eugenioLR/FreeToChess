/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edgenp.free2chess.userController;

import com.edgenp.free2chess.user.PendingGame;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author eugeniolr
 */
public interface PendingGameJPA extends JpaRepository<PendingGame, Integer> {

    /**
     *
     * @param name
     * @return
     */
    public Set<PendingGame> findByReceiver_name(String name);

    /**
     *
     * @param name
     * @return
     */
    public Set<PendingGame> findByEmiter_name(String name);
    
}
