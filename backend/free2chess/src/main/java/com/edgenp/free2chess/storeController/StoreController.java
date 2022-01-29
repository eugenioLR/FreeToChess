/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edgenp.free2chess.storeController;

import com.edgenp.free2chess.product.*;
import com.edgenp.free2chess.store.*;
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

    @Autowired
    private LootboxConstructor lootConst = new LootboxConstructor();
    
    @GetMapping("/store/{id}")
    public Product getProductById(@PathVariable("id") Integer id){
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
    
    @GetMapping("/store/packs/{id}")
    public ProductPack getPackById(@PathVariable("id") Integer id){
        return prodPackServ.getById(id);
    }
    
    
    @GetMapping("/store/packs/{id}/contents")
    public List<Product> getPackContents(@PathVariable("id") Integer id){
        return new ArrayList<>(prodPackServ.getById(id).getContents());
    }
    
    @PostMapping(value = "/store/buy", produces = MediaType.TEXT_PLAIN_VALUE)
    public String updateProduct(@RequestParam String name, @RequestBody Product prod){
        String status = "ok"; 
        User user = userServ.getById(name);
        if(user != null){
            if(prodPackServ.getById(prod.getId()) == null){
                Product prodReal = prodServ.getById(prod.getId());
                
                if(user.canBuy(prodReal.getC_price(), prodReal.getD_price())){
                    prodReal.buyItem(user);
                    user.addPurchasedProducts(prodReal);
                    System.out.println(prodReal.toString());
                    prodServ.update(prodReal);
                }else{
                    System.out.println(user.getCoins() + "," + user.getDiamonds());
                    System.out.println("not enough moneys, prod");
                    status = "error";
                }
            }else{
                ProductPack pack = prodPackServ.getById(prod.getId());
                if(user.canBuy(pack.getC_price(), pack.getD_price())){
                    pack.buyItem(user);
                    prodPackServ.update(pack);
                    for(Product p : pack.getContents()){
                        user.addPurchasedProducts(p);
                    }
                }else{
                    System.out.println(user.getCoins() + "," + user.getDiamonds());
                    System.out.println("not enough moneys, pack");
                    status = "error";
                }
            }
            userServ.update(user);
        }else{
            status = "error";
            System.out.println("The user with name \"" + name + "\" was not found.");
        }
        return status;
    }
    
    @PostMapping(value = "/store/lootbox", produces = MediaType.TEXT_PLAIN_VALUE)
    public String generateLootbox(@RequestBody User user, @RequestParam char rarity, @RequestParam int amount){
        lootConst.wipePack();
        ProductPack lootbox;
        List<Integer> result = new ArrayList<>();
        User userReal = userServ.getById(user.getName());
        
        if(userReal.canBuy(0, 20)){
            lootConst.selectByRarity(rarity);
            lootConst.selectAmount(amount);
            lootbox = lootConst.getPack();
            
            
            prodPackServ.update(lootbox);
            //result = lootbox.getContents();
            result = lootbox.getIdContents();
            
            
            userReal.spendDiamonds(20);
            userServ.update(userReal);
            System.out.println(result.toString());
            System.out.println(result.get(0).toString());
            
        }
        return result.toString();
    }
    
    @PostMapping(value = "/store/diamonds", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getDiamonds(@RequestBody User user, @RequestParam int amount){
        String status = "ok";
        User userReal = this.userServ.getById(user.getName());
        Store store = Store.getInstance();
        store.doPayment(user, 0.13*amount);
        System.out.println(userReal.getDiamonds());
        userReal.addDiamonds(amount);
        System.out.println(userReal.getDiamonds());
        userServ.update(userReal);
        return status;
    }
    
    @PostMapping(value = "/store/coins", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getCoins(@RequestBody User user, @RequestParam int amount){
        String status = "ok";
        User userReal = this.userServ.getById(user.getName());
        Store store = Store.getInstance();
        store.doPayment(user, 0.01*amount);
        userReal.addCoins(amount);
        System.out.println(userReal.getCoins());
        userServ.update(userReal);
        return status;
    }
}
