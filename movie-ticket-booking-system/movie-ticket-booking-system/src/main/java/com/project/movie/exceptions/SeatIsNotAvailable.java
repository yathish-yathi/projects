package com.project.movie.exceptions;

public class SeatIsNotAvailable extends RuntimeException {
	
	public SeatIsNotAvailable() {
		super("Requested seat is not available");
	}
	
}
