/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edgenp.free2chess.Product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author eugeniolr
 */
@RestController
@RequestMapping(path = "/store/v1/Product")
public class ProductController {
    
    @Autowired
    private ProductService prodServ;
    
    @GetMapping
    public List<Product> getProducts(){
        return prodServ.getProducts();
    }
}
