package com.yathish.Order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yathish.Order.dto.OrderDTO;
import com.yathish.Order.dto.OrderDTOFromFE;
import com.yathish.Order.service.OrderService;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTOFromFE orderDTOFromFE){
        System.out.println("Received OrderDTOFromFE: " + orderDTOFromFE);
        OrderDTO orderDTO = orderService.createOrder(orderDTOFromFE);
        return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
    }

}
