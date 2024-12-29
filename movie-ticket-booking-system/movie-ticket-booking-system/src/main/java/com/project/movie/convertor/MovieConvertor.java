package com.project.movie.convertor;

import com.project.movie.entity.Movie;
import com.project.movie.requestDTO.MovieRequest;

public class MovieConvertor {

	public static Movie MovieDtoToEntity(MovieRequest movieRequest) {
		
		Movie movie = Movie.builder()
				.movieName(movieRequest.getMovieName())
				.duration(movieRequest.getDuration())
				.genre(movieRequest.getGenre())
				.language(movieRequest.getLanguage())
				.releaseDate(movieRequest.getReleaseDate())
				.rating(movieRequest.getRating())
				.build();
		
		return movie;
	}
	
}
