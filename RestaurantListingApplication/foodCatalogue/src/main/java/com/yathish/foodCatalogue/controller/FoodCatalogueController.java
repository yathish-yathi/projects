package com.yathish.foodCatalogue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yathish.foodCatalogue.dto.FoodCataloguePage;
import com.yathish.foodCatalogue.dto.FoodItemDTO;
import com.yathish.foodCatalogue.service.FoodCatalogueService;

@RestController
@RequestMapping("/FoodCatalogue")
@CrossOrigin
public class FoodCatalogueController {

    @Autowired
    private FoodCatalogueService foodCatalogueService;
    
    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
        //System.out.println("Received request to add food item: " + foodItemDTO);
        FoodItemDTO savedFoodItem = foodCatalogueService.addFoodItem(foodItemDTO);
        return ResponseEntity.ok(savedFoodItem);
    }

    @GetMapping("/getFoodCatalogue/{restaurantId}")
    public ResponseEntity<FoodCataloguePage> getFoodCatalogueForRestaurant(@PathVariable Integer restaurantId) {
        System.out.println("Received request to get food catalogue");
        FoodCataloguePage foodCataloguePage = foodCatalogueService.getFoodCatalogueForRestaurant(restaurantId);
        System.out.println("Food Catalogue for restaurant ID " + restaurantId + ": " + foodCataloguePage);
        return ResponseEntity.ok(foodCataloguePage);
    }

    
}
