package com.yathish.Order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.yathish.Order.dto.OrderDTO;
import com.yathish.Order.dto.OrderDTOFromFE;
import com.yathish.Order.dto.UserDTO;
import com.yathish.Order.entity.Order;
import com.yathish.Order.orderMapper.OrderMapper;
import com.yathish.Order.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SequenceGenerator sequenceGenerator;

    public OrderDTO createOrder(OrderDTOFromFE orderDTOFromFE) {

        //get Full user details from User Service using userId
        UserDTO user = getUserDetailsForUserId(orderDTOFromFE.getUserId());

        //MongoDB will not auto generate the OrderId, so we need to generate it manually
        Integer orderId = sequenceGenerator.generateNextSequence();
        
        Order orderEntity = new Order(orderId, user, orderDTOFromFE.getRestaurant(), orderDTOFromFE.getFoodItemsList());

        Order SavedOrder = orderRepository.save(orderEntity);

        //Convert Order Entity to OrderDTO
        OrderDTO orderDTO = OrderMapper.INSTANCE.orderEntityToDTO(SavedOrder);
        
        return orderDTO;
    }

    private UserDTO getUserDetailsForUserId(Integer userId) {
        return restTemplate.getForObject("http://USERINFORMATIONSERVICE/user/get/"+userId, UserDTO.class);
    }



}
