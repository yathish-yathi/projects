package com.yathish.order_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yathish.order_management_system.dto.OrderRequest;
import com.yathish.order_management_system.entity.Order;
import com.yathish.order_management_system.entity.User;
import com.yathish.order_management_system.repository.UserRepository;
import com.yathish.order_management_system.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest orderRequest){

        // Retrieve the authenticated user's username from the security context, 
        //if we use order id from request Then anyone can place an order as any user by just changing the userId in Postman or browser tools, which is security vulnerability
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(userName).get();
        orderRequest.setUserId(user.getId());
        //now order is placed by the authenticated user
        Order order = orderService.placeOrder(orderRequest);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/getOrders")
    public ResponseEntity<List<Order>> getOrdersByUserId() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        //System.out.println("Authenticated Username: " + userName);
        SecurityContext securityContext = SecurityContextHolder.getContext();
       // System.out.println("securityContext => "+securityContext);
        User user = userRepository.findByUsername(userName).get();
        Long userId = user.getId();
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }
}
