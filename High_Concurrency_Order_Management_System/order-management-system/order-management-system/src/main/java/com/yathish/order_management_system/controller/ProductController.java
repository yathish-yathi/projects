package com.yathish.order_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yathish.order_management_system.entity.Product;
import com.yathish.order_management_system.repository.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;
    
    @GetMapping("/all")
    public List<Product> getAllProducts() {

        return productRepository.findAll(); 
    }
}
