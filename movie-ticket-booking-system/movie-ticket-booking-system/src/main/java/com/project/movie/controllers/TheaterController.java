package com.project.movie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.movie.requestDTO.TheaterRequest;
import com.project.movie.requestDTO.TheaterSeatRequest;
import com.project.movie.service.TheaterService;

@RestController
@RequestMapping("/theater")
public class TheaterController {
	
	@Autowired
	private TheaterService theaterService; 
	
	@PostMapping("/addNew")
	public ResponseEntity<String> addTheater(@RequestBody TheaterRequest theaterRequest){
		try {
			String result = theaterService.addTheater(theaterRequest);
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/addTheaterSeat")
	public ResponseEntity<String> addTheaterSeat(@RequestBody TheaterSeatRequest theaterSeatRequest){
		try {
			String result = theaterService.addTheaterSeat(theaterSeatRequest);
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	
}
