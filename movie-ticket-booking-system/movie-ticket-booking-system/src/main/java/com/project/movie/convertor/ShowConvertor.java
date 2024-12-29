package com.project.movie.convertor;

import com.project.movie.entity.Show;
import com.project.movie.requestDTO.ShowRequest;

public class ShowConvertor {
	
	public static Show showDtoToEntity(ShowRequest showRequest) {
		Show show = Show.builder()
				.time(showRequest.getShowStartTime())
				.date(showRequest.getShowDate())
				.build();
		
		return show;
	}

}
