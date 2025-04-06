package com.project.landValuePredictor.MapRestClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.project.landValuePredictor.configurations.ProjectConfigurations;
import com.project.landValuePredictor.model.GeoCodeResponse;
import com.project.landValuePredictor.model.PlacesResponse;

@Component
public class MapApi {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private final String apiKey;
	private final String API_getNearbyPlaces;
	private final String API_getLatAndLng;
		
	
	@Autowired
	public MapApi(ProjectConfigurations projectConfigurations) {
		this.apiKey = projectConfigurations.apiKey;
		this.API_getNearbyPlaces = projectConfigurations.API_getNearbyPlaces;
		this.API_getLatAndLng = projectConfigurations.API_getLatAndLng+apiKey;
	}
	
		
	//This API will pull nearby value points for a given lat and lng
	public PlacesResponse getNearbyPlaces(String includedType, Integer maxResultCount, Double latitude, Double longitude, Double radius) {
		
		String requestBody = String.format("{\n" +
			    "  \"includedTypes\": [\"%s\"],\n" +
			    "  \"rankPreference\": \"DISTANCE\",\n" +
			    "  \"maxResultCount\": %d,\n" +
			    "  \"locationRestriction\": {\n" +
			    "    \"circle\": {\n" +
			    "      \"center\": {\n" +
			    "        \"latitude\": %.15f,\n" +
			    "        \"longitude\": %.15f\n" +
			    "      },\n" +
			    "      \"radius\": %.2f\n" +
			    "    }\n" +
			    "  }\n" +
			    "}", includedType,maxResultCount, latitude, longitude, radius);
		
		System.out.println(requestBody);

		
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Goog-Api-Key", "AIzaSyBMBDygDQCrAa0_iaXqZsYV75CXqniVeOA");
        headers.set("X-Goog-FieldMask", "places.displayName,places.location,places.id");
        
		HttpEntity<String> HttpEntity = new HttpEntity<>(requestBody, headers);
		
		PlacesResponse placesResponse  = restTemplate.postForObject(API_getNearbyPlaces, HttpEntity, PlacesResponse.class);
		
		if(placesResponse.getPlaces()==null)System.out.println("placesResponse is null");
		if(placesResponse.getPlaces()!=null)System.out.println("From API for(mapAPI) "+includedType+" : "+placesResponse.toString());
		
        return placesResponse;
		
	}
	
	//This API will find lat and lng of given value point
	public GeoCodeResponse getLatAndLog(){
		
		final String API_final = API_getLatAndLng.replaceFirst("place_name", "Sri Narayana Guru Eng Med School").replaceFirst("apiKey", apiKey);
		System.out.println(API_final);
		GeoCodeResponse geoCodeResponse  = restTemplate.getForObject(API_final, GeoCodeResponse.class);

        return geoCodeResponse;
	}
}
