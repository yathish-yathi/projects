package com.project.movie.convertor;

import com.project.movie.entity.Show;
import com.project.movie.entity.Ticket;
import com.project.movie.responseDTO.TicketResponse;

public class TicketConverter {
	
	public static TicketResponse ticketEntityToResponse(Show show, Ticket ticket) {
		TicketResponse ticketResponse = TicketResponse.builder()
				.bookedSeats(ticket.getBookedSeats())
				.address(show.getTheater().getAddress())
				.theaterName(show.getTheater().getName())
				.movieName(show.getMovie().getMovieName())
				.date(show.getDate())
				.time(show.getTime())
				.totalPrice(ticket.getTotalTicketPrice())
				.build();
		
		return ticketResponse;
	}
	
}
