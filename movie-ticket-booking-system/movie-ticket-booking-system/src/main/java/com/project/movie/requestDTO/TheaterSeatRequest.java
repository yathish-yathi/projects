package com.project.movie.requestDTO;

import lombok.Data;

@Data
public class TheaterSeatRequest {
	private String address;
	private Integer noOfSeatsInRow;
	private Integer noOfClassicSeats;
	private Integer noOfPremiumSeats;
}
