package com.yathish.Order.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTOFromFE {
    
    private Integer userId;
    private RestaurantDTO restaurant;
    private List<FoodItemDTO> foodItemsList;

}
