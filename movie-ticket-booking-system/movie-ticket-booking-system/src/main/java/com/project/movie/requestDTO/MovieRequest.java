package com.project.movie.requestDTO;

import java.sql.Date;

import com.project.movie.enums.Genre;
import com.project.movie.enums.Language;

import lombok.Data;

@Data
public class MovieRequest {
	private String movieName;
	private Integer duration;
	private Double rating;
	private Date releaseDate;
	private Genre genre;
	private Language language;
	
}
