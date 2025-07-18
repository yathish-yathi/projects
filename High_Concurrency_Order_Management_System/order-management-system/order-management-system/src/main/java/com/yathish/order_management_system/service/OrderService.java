package com.yathish.order_management_system.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yathish.order_management_system.dto.OrderRequest;
import com.yathish.order_management_system.entity.Order;
import com.yathish.order_management_system.entity.OrderItems;
import com.yathish.order_management_system.entity.Product;
import com.yathish.order_management_system.repository.OrderRepository;
import com.yathish.order_management_system.repository.ProductRepository;

@Service
public class OrderService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    public Order placeOrder(OrderRequest orderRequest){

        Order order = new Order();
        
        order.setUserId(orderRequest.getUserId());
        order.setOrderDate(LocalDateTime.now());

        final Map<Long, ReentrantLock> productLocks = new ConcurrentHashMap<>();

        List<OrderItems> orderItemsList = new ArrayList<>();

        for(OrderRequest.OrderLine orderLine : orderRequest.getOrderLine()){
            Lock lock = productLocks.computeIfAbsent(orderLine.getProductId(), arg -> new ReentrantLock());

            lock.lock();
            try{
                Product product = productRepository.findById(orderLine.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));

                if(product.getStock() < orderLine.getQuantity()){
                    throw new RuntimeException("Insufficient stock for product: " + product.getName());
                }

                product.setStock(product.getStock() - orderLine.getQuantity());
                productRepository.save(product);

                OrderItems oi = new OrderItems();
                oi.setProductId(orderLine.getProductId());
                oi.setQuantity(orderLine.getQuantity());
                oi.setPrice(product.getPrice());

                orderItemsList.add(oi);

            }finally{
                lock.unlock();
            }
        }

        
        order.setItems(orderItemsList);
        order.setStatus("processed");
        orderRepository.save(order);

        return order;
    } 
    
}
