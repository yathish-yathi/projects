package com.yathish.order_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yathish.order_management_system.entity.Product;
import com.yathish.order_management_system.repository.ProductRepository;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductRepository productRepository;
    
    @PostMapping("/addOrUpdateProduct")
    public ResponseEntity<String> addOrUpdateProduct(@RequestBody Product product) {
        
        productRepository.findByName(product.getName())
            .ifPresentOrElse(
                existingProduct -> {
                    existingProduct.setStock(product.getStock());
                    existingProduct.setPrice(product.getPrice());
                    productRepository.save(existingProduct);
                },
                () -> productRepository.save(product)
            );
        return ResponseEntity.ok("Product added or updated successfully");
    }
}
