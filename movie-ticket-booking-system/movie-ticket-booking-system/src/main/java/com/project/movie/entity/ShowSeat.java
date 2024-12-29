package com.project.movie.entity;

import java.util.List;

import com.project.movie.enums.SeatType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SHOW_SEATS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String seatNo;

	@Enumerated(value = EnumType.STRING)
	private SeatType seatType;

	private Integer price;

	private Boolean isAvailable;

	private Boolean isFoodContains;

	@ManyToOne
	@JoinColumn
	private Show show;
}
