package com.yathish.foodCatalogue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemDTO {

    private Integer id;
    private String itemName;
    private String itemDescription;
    private Boolean isVeg;
    private Double price;
    private Integer restaurantId;
    private Integer quantity;

}
