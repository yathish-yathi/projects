package com.project.movie.responseDTO;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponse {
	private Time time;
	private Date date;
	private String movieName;
	private String theaterName;
	private String address;
	private String bookedSeats;
	private Integer totalPrice;
}
