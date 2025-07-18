package com.yathish.order_management_system.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class OrderItems {
    @Id
    @GeneratedValue
    private Long id;

    private Long productId;
    private Integer quantity;
    private BigDecimal price;

}
