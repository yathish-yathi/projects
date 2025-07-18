package com.yathish.order_management_system.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.Cascade;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "orders") // Using a non-reserved name to avoid conflicts
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;
    private LocalDateTime orderDate;
    private String status;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItems> items;

}
