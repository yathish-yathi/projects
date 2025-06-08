package com.SpringData.JPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringData.JPA.entity.Order;
import com.SpringData.JPA.entity.embedded.OrderId;

@Repository
public interface OrderRepository extends JpaRepository<Order, OrderId> {

    Order findByIdUserName(String userName);
    
}
