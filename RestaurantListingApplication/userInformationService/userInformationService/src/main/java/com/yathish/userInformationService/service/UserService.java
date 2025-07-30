package com.yathish.userInformationService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yathish.userInformationService.dto.UserDTO;
import com.yathish.userInformationService.entity.UserEntity;
import com.yathish.userInformationService.mapper.UserMapper;
import com.yathish.userInformationService.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public UserDTO addUser(UserDTO userDTO) {
        // Convert UserDTO to UserInformationEntity
        //System.out.println("Saving userDTO: " + userDTO.getUserName());
        UserEntity userEntity = UserMapper.INSTANCE.userDTOToEntity(userDTO);
        // Save the entity to the database
        //System.out.println("Saving user: " + userEntity.getUserName());
        UserEntity savedEntity = userRepository.save(userEntity);
        // Convert the saved entity back to UserDTO
        return UserMapper.INSTANCE.userEntityToDTO(savedEntity);
    }

    public ResponseEntity<UserDTO> getUser(Integer userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);

        if(userEntity.isPresent()) {
            UserDTO userDTO = UserMapper.INSTANCE.userEntityToDTO(userEntity.get());
            return ResponseEntity.ok(userDTO);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


}
