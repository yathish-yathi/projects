package com.project.movie.exceptions;

public class MovieDoesNotExistsException extends RuntimeException {

private static final long serialVersion = 887654321000000000L;
	
	public MovieDoesNotExistsException() {
		super("Movie does not exist");
	}
	
}
