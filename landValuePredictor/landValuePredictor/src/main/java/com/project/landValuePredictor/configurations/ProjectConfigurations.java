package com.project.landValuePredictor.configurations;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProjectConfigurations {

	@Value("${coordinate.lat}")
	public Double latConst;
	
	@Value("${coordinate.lng}")
	public Double lngConst;
	
	@Value("${value.points.include}")
	public String valuePoints;
	
	@Value("${value.points.maxResultCount}")
	public Integer maxResultCount;
	
	@Value("${land.area.cents.minmum}")
	public Integer minimumAcceptedLand;
	
	@Value("${mapApi.apiKey}")
	public String apiKey;
	
	@Value("${mapApi.url.API_getNearbyPlaces}")
	public String API_getNearbyPlaces;
	
	@Value("${mapApi.url.API_getLatAndLng}")
	public String API_getLatAndLng;

	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	@ConfigurationProperties(prefix = "value.point.range")
	public Map<String, Double> valuePointRange(){
		return new HashMap<String, Double>();
	}
	
	@Bean
	@ConfigurationProperties(prefix = "price.multiplier.cents")
	public Map<Double, Double> priceChangeWithAreaData(){
		return new HashMap<Double, Double>();
	}
	

}
