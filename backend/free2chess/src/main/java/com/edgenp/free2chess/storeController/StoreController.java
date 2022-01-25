/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edgenp.free2chess.storeController;

import com.edgenp.free2chess.product.*;
import com.edgenp.free2chess.user.*;
import com.edgenp.free2chess.userController.UserService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author eugeniolr
 */
@RestController
@CrossOrigin(origins = "*")
public class StoreController {
    
    @Autowired
    private ProductService prodServ;
    
    @Autowired
    private EmojiService emojiServ;
    
    @Autowired
    private BoosterService boosterServ;
    
    @Autowired
    private BoardSkinService boardSkinServ;
    
    @Autowired
    private PieceSkinSetService pieceSkinSetServ;
    
    @Autowired
    private ProductPackService prodPackServ;
    
    @Autowired
    private UserService userServ;
    
    @GetMapping("/store/{id}")
    public Product getProducts(@PathVariable("id") Integer id){
        return prodServ.getById(id);
    }
    
    @GetMapping("/store")
    public List<Product> getProducts(){
        return prodServ.getAll();
    }
    
    @GetMapping("/store/emojis")
    public List<Emoji> getEmojis(){
        return emojiServ.getAll();
    }
    
    @GetMapping("/store/boosters")
    public List<Booster> getBoosters(){
        return boosterServ.getAll();
    }
    
    @GetMapping("/store/boardSkins")
    public List<BoardSkin> getBoardSkins(){
        return boardSkinServ.getAll();
    }
    
    @GetMapping("/store/pieceSkinSets")
    public List<PieceSkinSet> getPieceSkinSets(){
        return pieceSkinSetServ.getAll();
    }
    
    @GetMapping("/store/packs")
    public List<ProductPack> getProductPacks(){
        return prodPackServ.getAll();
    }
    
    @GetMapping("/store/packs/{id}/contents")
    public List<Product> getProductPacks(@PathVariable("id") Integer id){
        return new ArrayList<>(prodPackServ.getById(id).getContents());
    }
    
    /*
    @PostMapping(value = "/store/dummy", produces = MediaType.TEXT_PLAIN_VALUE)
    public void updateDummyProduct(@RequestBody Product prod){
        Product prodReal = this.getProducts(prod.getId());
        prodReal.buyItem(new User("none", "none", "none", 0));
        prodServ.update(prodReal);
    }*/
    
    @PostMapping(value = "/store/buy", produces = MediaType.TEXT_PLAIN_VALUE)
    public void updateProduct(@RequestParam String name, @RequestBody Product prod){
        User user = this.userServ.getById(name);
        if(user != null){
            Product prodReal = this.getProducts(prod.getId());

            prodReal.buyItem(user);
            prodServ.update(prodReal);
        }else{
            System.out.println("The user with name \"" + name + "\" was not found.");
        }
    }
}
