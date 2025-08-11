package com.yathish.restaurantListing.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yathish.restaurantListing.dto.RestaurantDTO;
import com.yathish.restaurantListing.entity.RestaurantEntity;
import com.yathish.restaurantListing.mapper.RestaurantMapper;
import com.yathish.restaurantListing.repository.RestaurantRepository;

@Service
public class RestaurantService {
    
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<RestaurantDTO> findAllRestaurants() {
        List<RestaurantEntity> restaurantEntities = restaurantRepository.findAll();
        // Convert RestaurantEntity to RestaurantDTO
        List<RestaurantDTO> restaurantDTOs = restaurantEntities.stream()
            .map( resEntity -> RestaurantMapper.INSTANCE.restaurantEntityToRestaurantDTO(resEntity))
            .collect(Collectors.toList());
        
        return restaurantDTOs;
    }

    public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO) {
        // Convert RestaurantDTO to RestaurantEntity
        RestaurantEntity restaurantEntity = RestaurantMapper.INSTANCE.restaurantDTOToRestaurantEntity(restaurantDTO);
        // Save the entity
        RestaurantEntity savedEntity = restaurantRepository.save(restaurantEntity);
        // Convert back to RestaurantDTO
        return RestaurantMapper.INSTANCE.restaurantEntityToRestaurantDTO(savedEntity);
    }

}
