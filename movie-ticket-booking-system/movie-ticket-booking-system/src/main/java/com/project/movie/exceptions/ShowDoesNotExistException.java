package com.project.movie.exceptions;

public class ShowDoesNotExistException extends RuntimeException {
	
	private static final long serialVersionUID = -4436119261176031165L;

	public ShowDoesNotExistException() {
		super("Show does not exists");
	}
	
}
