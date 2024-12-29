package com.project.movie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.movie.requestDTO.UserRequest;
import com.project.movie.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService; 
	
	@PostMapping("/addNew")
	public ResponseEntity<String> addNewUser(UserRequest userRequest){
		try {
			System.out.println(userRequest.getEmailId());
			String result = userService.addNewUser(userRequest);
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		}catch(Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
}
