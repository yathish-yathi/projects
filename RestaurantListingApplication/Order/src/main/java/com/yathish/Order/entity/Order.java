package com.yathish.Order.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.yathish.Order.dto.FoodItemDTO;
import com.yathish.Order.dto.RestaurantDTO;
import com.yathish.Order.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    private Integer OrderId;
    private UserDTO user;
    private RestaurantDTO restaurant;
    private List<FoodItemDTO> foodItems;
}
