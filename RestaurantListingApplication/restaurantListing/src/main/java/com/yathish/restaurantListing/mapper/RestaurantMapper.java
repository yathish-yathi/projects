package com.yathish.restaurantListing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.yathish.restaurantListing.dto.RestaurantDTO;
import com.yathish.restaurantListing.entity.RestaurantEntity;

@Mapper
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    RestaurantDTO restaurantEntityToRestaurantDTO(RestaurantEntity restaurantEntity);

    RestaurantEntity restaurantDTOToRestaurantEntity(RestaurantDTO restaurantDTO);

}
