package com.project.movie.convertor;

import com.project.movie.entity.User;
import com.project.movie.requestDTO.UserRequest;
import com.project.movie.responseDTO.UserResponse;

public class UserConverter {
	
	public static User userDtoToEntity(UserRequest userRequest) {
		User user = User.builder()
				.name(userRequest.getName())
				.age(userRequest.getAge())
				.address(userRequest.getAddress())
				.gender(userRequest.getGender())
				.mobileNo(userRequest.getMobileNo())
				.emailId(userRequest.getEmailId())
				.role(userRequest.getRole())
				.password(userRequest.getPassword())
				.build();
		
		return user;
	}
	
	public static UserResponse UserEntityToDto(User user) {
		UserResponse userResponse = UserResponse.builder()
				.name(user.getName())
				.age(user.getAge())
				.addres(user.getAddress())
				.gender(user.getGender())
				.build();
		
		return userResponse;
	}
	
}
