package com.yathish.foodCatalogue.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.yathish.foodCatalogue.dto.FoodItemDTO;
import com.yathish.foodCatalogue.entity.FoodItemEntity;

@Mapper
public interface FoodItemMapper {

    FoodItemMapper INSTANCE = Mappers.getMapper(FoodItemMapper.class);

    FoodItemDTO foodItemEntityToDTO(FoodItemEntity foodItemEntity);

    FoodItemEntity foodItemDTOToEntity(FoodItemDTO foodItemDTO);

}
