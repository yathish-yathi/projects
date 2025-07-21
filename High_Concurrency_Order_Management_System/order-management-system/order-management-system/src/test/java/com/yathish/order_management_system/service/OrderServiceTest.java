package com.yathish.order_management_system.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yathish.order_management_system.dto.OrderRequest;
import com.yathish.order_management_system.dto.OrderRequest.OrderLine;
import com.yathish.order_management_system.entity.Order;
import com.yathish.order_management_system.entity.Product;
import com.yathish.order_management_system.repository.OrderRepository;
import com.yathish.order_management_system.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void placeOrder_whenSuffientQuantity_TheOrderShouldProcess_Test() {
        
        OrderLine orderLine = new OrderLine(2l, 5);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setUserId(1L);
        orderRequest.setOrderLine(List.of(orderLine));

        when(productRepository.findById(2l)).thenReturn(Optional.of(new Product("MacBooc", 50, BigDecimal.valueOf(100))));

        when(orderRepository.save(any(Order.class))).thenAnswer(i-> i.getArguments()[0]);
        
        Order order = orderService.placeOrder(orderRequest);

        assertEquals("processed", order.getStatus());

        verify(productRepository).findById(2l);
        verify(orderRepository).save(any(Order.class));

    }


}
