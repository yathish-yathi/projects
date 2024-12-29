package com.project.movie.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.movie.convertor.UserConverter;
import com.project.movie.entity.Theater;
import com.project.movie.entity.User;
import com.project.movie.exceptions.UserExistException;
import com.project.movie.repository.TheaterRepository;
import com.project.movie.repository.TicketRepository;
import com.project.movie.repository.UserRepository;
import com.project.movie.requestDTO.UserRequest;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TheaterRepository theaterRepository; 
	
	public String addNewUser(UserRequest userRequest) {
		
		Optional<Theater> theater = theaterRepository.findById(1);
		System.out.println("Hi");
		Theater th = theater.get();
		System.out.println("Hello");
		System.out.println( th.getShowList().get(0).getShowId());
		
		if(userRepository.findByEmailId(userRequest.getEmailId()) != null) {
			throw new UserExistException();
		}
		
		User user = UserConverter.userDtoToEntity(userRequest);
		
		userRepository.save(user);
		
		return "User saved successfully";
	}
	
	
}
