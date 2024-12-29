package com.project.movie.requestDTO;

import java.sql.Date;
import java.sql.Time;

import lombok.Data;

@Data
public class ShowRequest {
    private Time showStartTime;
    private Date showDate;
    private Integer theaterId;
    private Integer movieId;
}
