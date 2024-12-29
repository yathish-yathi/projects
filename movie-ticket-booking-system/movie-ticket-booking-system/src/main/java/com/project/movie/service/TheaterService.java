package com.project.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.movie.convertor.TheaterConverter;
import com.project.movie.entity.Theater;
import com.project.movie.entity.TheaterSeat;
import com.project.movie.enums.SeatType;
import com.project.movie.exceptions.TheaterDoesNotExistException;
import com.project.movie.exceptions.TheaterIsExistException;
import com.project.movie.repository.TheaterRepository;
import com.project.movie.requestDTO.TheaterRequest;
import com.project.movie.requestDTO.TheaterSeatRequest;

@Service
public class TheaterService {

	@Autowired
	private TheaterRepository theaterRepository;

	public String addTheater(TheaterRequest theaterRequest) {

		if (theaterRepository.findByAddress(theaterRequest.getAddress()) != null) {
			throw new TheaterIsExistException();
		}

		Theater theater = TheaterConverter.theaterDtoToEntity(theaterRequest);

		theaterRepository.save(theater);

		return "Theater has been saved successfully";
	}

	public String addTheaterSeat(TheaterSeatRequest theaterSeatRequest) {

		Theater theater = theaterRepository.findByAddress(theaterSeatRequest.getAddress());

		if (theater == null) {
			throw new TheaterDoesNotExistException();
		}

		Integer noOfSeatsInRow = theaterSeatRequest.getNoOfSeatsInRow();
		Integer noOfClassicSeats = theaterSeatRequest.getNoOfClassicSeats();
		Integer noOfPremiumSeats = theaterSeatRequest.getNoOfPremiumSeats();

		List<TheaterSeat> seatList = theater.getTheaterSeatList();

		int counter = 1;
		int fill = 0;
		char ch = 'A';

		// Filling classic seats
		for (int i = 0; i < noOfClassicSeats; i++) {

			TheaterSeat theaterSeat = new TheaterSeat();

			// Generate seat no
			String seatNo = Integer.toString(counter) + ch;

			ch++;
			fill++;
			if (fill == noOfSeatsInRow) {
				fill = 0;
				ch = 'A';
				counter++;
			}

			// set seat details
			theaterSeat.setSeatNo(seatNo);
			theaterSeat.setSeatType(SeatType.CLASSIC);
			theaterSeat.setTheater(theater);

			// add seat to seat list
			seatList.add(theaterSeat);

		}

		// Filling Premium seats
		for (int i = 0; i < noOfPremiumSeats; i++) {

			TheaterSeat theaterSeat = new TheaterSeat();

			// Generate seat no
			String seatNo = Integer.toString(counter) + ch;

			ch++;
			fill++;
			if (fill == noOfSeatsInRow) {
				fill = 0;
				ch = 'A';
				counter++;
			}

			// set seat details
			theaterSeat.setSeatNo(seatNo);
			theaterSeat.setSeatType(SeatType.PREMIUM);
			theaterSeat.setTheater(theater);

			// add seat to seat list
			seatList.add(theaterSeat);

		}
		
		theaterRepository.save(theater);

		return "Theater seats have been added successfully";
	}

}
