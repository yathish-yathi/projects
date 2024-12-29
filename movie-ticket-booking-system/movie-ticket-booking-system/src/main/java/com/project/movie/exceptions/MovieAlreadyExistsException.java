package com.project.movie.exceptions;

public class MovieAlreadyExistsException extends RuntimeException{
	
	private static final long serialVersion = 987654321000000000L;
	
	public MovieAlreadyExistsException() {
		super("Movie with this name and language is already present");
	}
}
