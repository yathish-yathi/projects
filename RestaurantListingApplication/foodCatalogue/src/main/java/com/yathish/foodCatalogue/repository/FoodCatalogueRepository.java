package com.yathish.foodCatalogue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yathish.foodCatalogue.entity.FoodItemEntity;

@Repository
public interface FoodCatalogueRepository extends JpaRepository<FoodItemEntity, Integer>{

    List<FoodItemEntity> findByRestaurantId(Integer restaurantId);

}
