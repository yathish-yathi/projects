package com.project.movie.exceptions;

public class UserExistException extends RuntimeException{
	
	public UserExistException() {
		super("User is already present");
	}
	
}
