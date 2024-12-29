package com.project.movie.exceptions;

public class TheaterIsExistException extends RuntimeException {
	
	private static final long serialVersionUID = 6386810783666583528L;
	
	public TheaterIsExistException() {
		super("Theater is already Present on this Address");
	}
}
