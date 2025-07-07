package com.yathish.order_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yathish.order_management_system.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Custom query methods can be defined here if needed
    // For example, to find products by name or stock availability

    // Example:
    // List<Product> findByName(String name);
    // List<Product> findByStockGreaterThan(Integer stock); 
    
}
