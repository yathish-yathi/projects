package com.project.movie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.movie.requestDTO.MovieRequest;
import com.project.movie.service.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {
	
	@Autowired
	MovieService movieService;

	@PostMapping("/addNew")
	public ResponseEntity<String> addMovie(@RequestBody MovieRequest movieRequest){
		
		try {
			String result = movieService.addMovie(movieRequest);
			return new ResponseEntity<>(result, HttpStatus.CREATED);
			
		}catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
			
	
}
