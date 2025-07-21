package com.yathish.order_management_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yathish.order_management_system.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);
    
    // List<Product> findByStockGreaterThan(Integer stock); 
    
}
