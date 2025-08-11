package com.yathish.foodCatalogue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.yathish.foodCatalogue.dto.FoodCataloguePage;
import com.yathish.foodCatalogue.dto.FoodItemDTO;
import com.yathish.foodCatalogue.dto.Restaurant;
import com.yathish.foodCatalogue.entity.FoodItemEntity;
import com.yathish.foodCatalogue.mapper.FoodItemMapper;
import com.yathish.foodCatalogue.repository.FoodCatalogueRepository;

@Service
public class FoodCatalogueService {

    @Autowired
    private FoodCatalogueRepository foodCatalogueRepository;

    @Autowired
    private RestTemplate restTemplate;

    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
        FoodItemEntity foodItemEntity = FoodItemMapper.INSTANCE.foodItemDTOToEntity(foodItemDTO);
        foodItemEntity = foodCatalogueRepository.save(foodItemEntity);
        return FoodItemMapper.INSTANCE.foodItemEntityToDTO(foodItemEntity);
    }

    public FoodCataloguePage getFoodCatalogueForRestaurant(Integer restaurantId) {
        //get food items for the given restaurant
        List<FoodItemEntity> foodItemEntities = getFoodItemsForRestaurant(restaurantId);
        
        //get restaurant details for given restaurantId
        Restaurant restaurant = getRestaurantForId(restaurantId);

        //Combine food items and restaurant details into FoodCataloguePage
        FoodCataloguePage foodCataloguePage = createFoodCataloguePage(restaurant, foodItemEntities);

        return foodCataloguePage;

    }

    private FoodCataloguePage createFoodCataloguePage(Restaurant restaurant, List<FoodItemEntity> foodItemEntities) {
        // Create and return a FoodCataloguePage object
        return new FoodCataloguePage(foodItemEntities, restaurant);
    }

    private Restaurant getRestaurantForId(Integer restaurantId) {
       //make a call to RestaurantListng microservice through Euraka and hit it API to get restaurant details
        String url = "http://RESTAURANTLISTING/restaurant/findById/"+restaurantId;
        Restaurant restaurant = restTemplate.getForObject(url, Restaurant.class);
        return restaurant;
    }

    private List<FoodItemEntity> getFoodItemsForRestaurant(Integer restaurantId) {
        // Fetch food items for the given restaurant from the repository
        return foodCatalogueRepository.findByRestaurantId(restaurantId);
    }



}
