package com.yathish.order_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yathish.order_management_system.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Additional query methods can be defined here if needed
    
    List<Order> findByUserId(Long userId);
    
}
