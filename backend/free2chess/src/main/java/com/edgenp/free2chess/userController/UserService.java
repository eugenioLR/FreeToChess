/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edgenp.free2chess.userController;

import com.edgenp.free2chess.user.User;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author eugeniolr
 */
@Service
public class UserService {
    @Autowired
    private UserJPA userRepo;
    
    /**
     *
     * @return
     */
    public List<User> getAll(){
        return userRepo.findAll();
    }
    
    /**
     *
     * @param id
     * @return
     */
    public User getById(String id){
        Optional<User> optional = userRepo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
    
    /**
     *
     * @param user
     */
    public void create(User user){
        userRepo.save(user);
    }

    /**
     *
     * @param user
     */
    public void update(User user) {
        userRepo.save(user);
    }

    public List<User> getAllOrdered() {
        List<User> allUsers = userRepo.findAll();
        
        allUsers.sort((User o1, User o2) -> {
            return Integer.compare(o1.getElo(), o2.getElo());
        });
        
        return allUsers.subList(0, Math.min(allUsers.size(), 10));
    }
}
