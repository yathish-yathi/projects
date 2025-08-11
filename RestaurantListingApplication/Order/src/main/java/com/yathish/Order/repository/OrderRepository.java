package com.yathish.Order.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.yathish.Order.entity.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, Integer> {
    // Additional query methods can be defined here if needed

}
