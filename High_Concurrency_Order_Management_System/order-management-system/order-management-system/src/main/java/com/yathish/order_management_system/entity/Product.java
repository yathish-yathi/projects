package com.yathish.order_management_system.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;  
      
    private Integer stock;
    private BigDecimal price;

     public Product(String string, int stock, BigDecimal price) {
        this.name = string;
        this.stock = stock;
        this.price = price;
    }
}
