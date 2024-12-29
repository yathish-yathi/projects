package com.project.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.movie.convertor.MovieConvertor;
import com.project.movie.entity.Movie;
import com.project.movie.exceptions.MovieAlreadyExistsException;
import com.project.movie.repository.MovieRepository;
import com.project.movie.requestDTO.MovieRequest;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository; 
	
	public String addMovie(MovieRequest movieRequest) {
		
		Movie movieByName = movieRepository.findByMovieName(movieRequest.getMovieName());
		
		if(movieByName != null && movieByName.getMovieName().equals(movieRequest.getMovieName())) {
			throw new MovieAlreadyExistsException();
		}
		
		Movie movie = MovieConvertor.MovieDtoToEntity(movieRequest);
		
		movieRepository.save(movie);
		
		return "The movie \""+movieRequest.getMovieName()+"\" has been added successfully";
	}
	
}
