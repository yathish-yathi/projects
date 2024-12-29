package com.project.movie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.movie.convertor.ShowConvertor;
import com.project.movie.entity.Movie;
import com.project.movie.entity.Show;
import com.project.movie.entity.ShowSeat;
import com.project.movie.entity.Theater;
import com.project.movie.entity.TheaterSeat;
import com.project.movie.enums.SeatType;
import com.project.movie.exceptions.MovieDoesNotExistsException;
import com.project.movie.exceptions.ShowDoesNotExistException;
import com.project.movie.exceptions.TheaterDoesNotExistException;
import com.project.movie.repository.MovieRepository;
import com.project.movie.repository.ShowRepository;
import com.project.movie.repository.TheaterRepository;
import com.project.movie.requestDTO.ShowRequest;
import com.project.movie.requestDTO.ShowSeatRequest;

@Service
public class ShowService {

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	TheaterRepository theaterRepository;

	@Autowired
	ShowRepository showRepository;

	public String addShow(ShowRequest showRequest) {

		Optional<Movie> movieOp = movieRepository.findById(showRequest.getMovieId());
		if (movieOp.isEmpty()) {
			throw new MovieDoesNotExistsException();
		}

		Optional<Theater> theatorOp = theaterRepository.findById(showRequest.getTheaterId());
		if (theatorOp.isEmpty()) {
			throw new TheaterDoesNotExistException();
		}

		Show show = ShowConvertor.showDtoToEntity(showRequest);

		Theater theater = theatorOp.get();
		Movie movie = movieOp.get();

		show.setMovie(movie);
		show.setTheater(theater);

		show = showRepository.save(show);

		movie.getShows().add(show);
		theater.getShowList().add(show);

		movieRepository.save(movie);
		theaterRepository.save(theater);

		return "show has been added successfully";
	}

	public String associateShowSeats(ShowSeatRequest showSeatRequest) {

		Optional<Show> showOp = showRepository.findById(showSeatRequest.getShowId());
		if (showOp.isEmpty()) {
			throw new ShowDoesNotExistException();
		}

		Show show = showOp.get();
		Theater theater = show.getTheater();
		
		List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();
		List<ShowSeat> showSeatList = show.getShowSeatList();
		
		for(TheaterSeat theaterSeat : theaterSeatList) {
			ShowSeat showSeat = new ShowSeat();
			showSeat.setSeatNo(theaterSeat.getSeatNo());
			showSeat.setSeatType(theaterSeat.getSeatType());
			
			if(showSeat.getSeatType().equals(SeatType.CLASSIC)) {
				showSeat.setPrice(showSeatRequest.getPriceOfClassicSeat());
			}else {
				showSeat.setPrice(showSeatRequest.getPriceOfPremiumSeat());
			}
			
			showSeat.setShow(show);
			showSeat.setIsAvailable(Boolean.TRUE);
			showSeat.setIsFoodContains(Boolean.FALSE);
			
			showSeatList.add(showSeat);
		}
		
		showRepository.save(show);

		return "show has been added successfully";
	}

}
