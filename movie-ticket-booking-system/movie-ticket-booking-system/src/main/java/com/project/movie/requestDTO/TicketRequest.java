package com.project.movie.requestDTO;

import java.util.List;

import lombok.Data;

@Data
public class TicketRequest {
	private Integer showId;
	private Integer userId;
	private List<String> requestedSeats;
}
