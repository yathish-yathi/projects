package com.project.movie.exceptions;

public class UserDoesNotExistException extends RuntimeException {
	
	public UserDoesNotExistException() {
		super("User does not exsits");
	}
	
}
