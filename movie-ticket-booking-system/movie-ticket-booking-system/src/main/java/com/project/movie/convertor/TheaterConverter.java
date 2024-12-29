package com.project.movie.convertor;

import com.project.movie.entity.Theater;
import com.project.movie.requestDTO.TheaterRequest;

public class TheaterConverter {

	public static Theater theaterDtoToEntity(TheaterRequest theaterRequest) {
		
		Theater theater = Theater.builder()
				.name(theaterRequest.getName())
				.address(theaterRequest.getAddress())
				.build();
		
		return theater;
	}
	
}
