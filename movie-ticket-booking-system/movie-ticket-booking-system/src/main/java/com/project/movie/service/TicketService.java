package com.project.movie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.movie.convertor.TicketConverter;
import com.project.movie.entity.Show;
import com.project.movie.entity.ShowSeat;
import com.project.movie.entity.Ticket;
import com.project.movie.entity.User;
import com.project.movie.exceptions.SeatIsNotAvailable;
import com.project.movie.exceptions.ShowDoesNotExistException;
import com.project.movie.exceptions.UserDoesNotExistException;
import com.project.movie.repository.ShowRepository;
import com.project.movie.repository.TicketRepository;
import com.project.movie.repository.UserRepository;
import com.project.movie.requestDTO.TicketRequest;
import com.project.movie.responseDTO.TicketResponse;

@Service
public class TicketService {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private ShowRepository showRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public TicketResponse ticketBooking(TicketRequest ticketRequest) {
		
		Optional<Show> showOp = showRepository.findById(ticketRequest.getShowId());
		
		if(showOp.isEmpty()) {
			throw new ShowDoesNotExistException();
		}
		
		Optional<User> userOp = userRepository.findById(ticketRequest.getUserId());
		
		if(userOp.isEmpty()) {
			throw new UserDoesNotExistException();
		}
		
		Show show = showOp.get();
		User user = userOp.get();
		
		Boolean isSeatsAvailable = isSeatAvailable(show.getShowSeatList(), ticketRequest.getRequestedSeats());
		
		if(!isSeatsAvailable) {
			throw new SeatIsNotAvailable();
		}
		
		Integer totalPrice = getPriceAndAssignSeats(show.getShowSeatList(), ticketRequest.getRequestedSeats());
		
		String seats = listToString(ticketRequest.getRequestedSeats());
		
		Ticket ticket = new Ticket();
		ticket.setTotalTicketPrice(totalPrice);
		ticket.setBookedSeats(seats);
		ticket.setUser(user);
		ticket.setShow(show);
		
		ticket = ticketRepository.save(ticket);
		
		user.getTicketList().add(ticket);
		userRepository.save(user);
		
		show.getTicketList().add(ticket);
		showRepository.save(show);
		
		return TicketConverter.ticketEntityToResponse(show, ticket);
	}
	
	
	private Boolean isSeatAvailable(List<ShowSeat> showSeatList, List<String> requestSeats) {
		
		for(ShowSeat showSeat : showSeatList) {
			
			String seatNo = showSeat.getSeatNo();

			if(requestSeats.contains(seatNo) && !showSeat.getIsAvailable()) {
				return false;
			}
		}
		return true;
	}
	
	private Integer getPriceAndAssignSeats(List<ShowSeat> showSeatList, List<String> requestSeats) {
		Integer totalAmount = 0;

		for(ShowSeat showSeat : showSeatList) {
			
			if(requestSeats.contains(showSeat.getSeatNo())) {
				totalAmount += showSeat.getPrice();
				showSeat.setIsAvailable(Boolean.FALSE);
			}
		}
		
		return totalAmount;
	}
	
	private String listToString(List<String> requestSeats) {
		StringBuilder sb = new StringBuilder();
		
		for(String seat : requestSeats) {
			sb = sb.append(seat);
		}
		
		return sb.toString();
	}
	
}
