/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edgenp.free2chess.storeController;

import com.edgenp.free2chess.product.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author eugeniolr
 */
@RestController
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
    private PieceSkinService pieceSkinServ;
    
    @Autowired
    private PieceSkinSetService pieceSkinSetServ;
    
    @Autowired
    private ProductPackService prodPackServ;
    
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
    
    @GetMapping("/store/pieceSkins")
    public List<PieceSkin> getPieceSkins(){
        return pieceSkinServ.getAll();
    }
    
    @GetMapping("/store/pieceSkinSets")
    public List<PieceSkinSet> getPieceSkinSets(){
        return pieceSkinSetServ.getAll();
    }
    
    @GetMapping("/store/productPacks")
    public List<ProductPack> getProductPacks(){
        return prodPackServ.getAll();
    }
    
    @PutMapping("/store")
    public void updateProduct(@RequestBody Product prod){
        prodServ.update(prod);
    }
    
    
}
