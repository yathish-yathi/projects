package com.yathish.Order.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO {
    private Integer OrderId;
    private UserDTO user;
    private RestaurantDTO restaurant;
    private List<FoodItemDTO> foodItems;
}
