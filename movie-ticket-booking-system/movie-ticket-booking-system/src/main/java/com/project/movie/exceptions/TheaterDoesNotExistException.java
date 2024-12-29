package com.project.movie.exceptions;

public class TheaterDoesNotExistException extends RuntimeException {

private static final long serialVersion = 787654321000000000L;
	
	public TheaterDoesNotExistException() {
		super("Theater does not exist");
	}
	
}
