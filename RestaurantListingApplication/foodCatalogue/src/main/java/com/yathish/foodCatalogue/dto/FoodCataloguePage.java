package com.yathish.foodCatalogue.dto;

import java.util.List;

import com.yathish.foodCatalogue.entity.FoodItemEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodCataloguePage {

    private List<FoodItemEntity> foodItemsList;
    private Restaurant restaurant;

}
