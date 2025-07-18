package com.yathish.order_management_system.config;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.yathish.order_management_system.entity.Product;
import com.yathish.order_management_system.repository.ProductRepository;

@Configuration
public class DataInitializer {
    
   // @Bean
    public CommandLineRunner loadProducts(ProductRepository productRepository) {
        return args -> {
            productRepository.saveAll(List.of(
                new Product("HP Laptop", 100, BigDecimal.valueOf(1500)),
                new Product("Samsung Smartphone", 200, BigDecimal.valueOf(800)),
                new Product("Apple Tablet", 150, BigDecimal.valueOf(600))
            ));
        };
    }
}
