package com.yathish.userInformationService.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.yathish.userInformationService.dto.UserDTO;
import com.yathish.userInformationService.entity.UserEntity;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userEntityToDTO(UserEntity userEntity);

    UserEntity userDTOToEntity(UserDTO userDTO);
}
