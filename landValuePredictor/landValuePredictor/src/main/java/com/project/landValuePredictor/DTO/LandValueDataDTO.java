package com.project.landValuePredictor.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LandValueDataDTO {
	
	public LandValueDataDTO(Float price, Double lat, Double lng) {
		this.price=price;
		this.lat=lat;
		this.lng=lng;
	}
	
	private Float price;
	private Double lat;
	private Double lng;
}
