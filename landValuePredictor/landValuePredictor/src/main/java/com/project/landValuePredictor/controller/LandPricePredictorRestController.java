package com.project.landValuePredictor.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.landValuePredictor.DTO.LandValueDataDTO;
import com.project.landValuePredictor.MapRestClient.MapApi;
import com.project.landValuePredictor.configurations.ProjectConfigurations;
import com.project.landValuePredictor.model.PlacesResponse;
import com.project.landValuePredictor.service.FindNearbyLandService;

@Controller
class LandPricePredictorController{
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	
}

@RestController
public class LandPricePredictorRestController {
	
	@Autowired
	private FindNearbyLandService findNearbyLandService;
	
	@Autowired
	private ProjectConfigurations projectConfigurations; 
	
	@GetMapping("/price")
	public String gePrice(@RequestParam Double latitude, @RequestParam Double longitude, @RequestParam Double area) {
		
		Integer minimumArea = projectConfigurations.minimumAcceptedLand;
		if(area<minimumArea) return "Minimum area must be greater than or equal to "+minimumArea;
		
		Double price = findNearbyLandService.predictLandPrice(latitude, longitude, area);
		
		return "Predicted price is = "+price;
	}
	
	@GetMapping("/place")
	public ResponseEntity<String> getPlace() {
		List<PlacesResponse> places = findNearbyLandService.getNearByValuePoints(13.046489897687106, 74.83300872983679);
		
		LandValueDataDTO land = new LandValueDataDTO(1000.0f, 13.046489897687106,74.83300872983679);
		Map<String, Double> map1 = findNearbyLandService.getDistanceMapFromLand(places,land);
		
		System.out.println("map1");
		map1.forEach((k,v)-> System.out.println(k+"->"+v));
		
		List<Map<String,Double>> list = findNearbyLandService.convertDistanceMapToPercentageAndPriceMap(map1,1000.0f);
		
		System.out.println("per");
		list.get(0).forEach((k,v)-> System.out.println(k+"->"+v));
		
		System.out.println("price");
		list.get(1).forEach((k,v)-> System.out.println(k+"->"+v));
		
		return new ResponseEntity<>(places.toString(), HttpStatus.OK);
	}
	
	@GetMapping("/lands")
	public String getLands(@RequestParam Double latitude, @RequestParam Double longitude) {
		return findNearbyLandService.getNearbyLands(latitude, longitude).toString();
		
	}
	
	@GetMapping("/price1")
	public String gePriceTest() {
		Double price = findNearbyLandService.predictLandPrice(12.98895, 74.9118, 100.0);
		
		LandValueDataDTO land = new LandValueDataDTO(50000f, 12.98895, 74.9118);
		List<PlacesResponse> list = findNearbyLandService.getNearByValuePoints(12.98895, 74.9118);
		Map<String, Double> distMap = findNearbyLandService.getDistanceMapFromLand(list, land);
		List<Map<String, Double>> perAndPrice = findNearbyLandService.convertDistanceMapToPercentageAndPriceMap(distMap, 50000f);
		Map<String, Double> priceMap = perAndPrice.get(1);
		Map<String, Double> perMap = perAndPrice.get(0);
		System.out.println("Printing Sample map data");
		Double tempPrice = 0.0;
		System.out.println("Printing test Map");
		for(Entry<String, Double> entry : priceMap.entrySet()) {
			tempPrice += entry.getValue();
			System.out.println(entry.getKey()+"---->"+entry.getValue()+"---->"+perMap.get(entry.getKey())+" %");
		}
		System.out.println("test Map price = "+tempPrice);
		
		
		return "Predicted price is = "+price;
	}
	
	@GetMapping("/test")
	public void test() {
		
		
		LandValueDataDTO land = new LandValueDataDTO(50000f, 13.04967, 74.82282);
		List<PlacesResponse> list = findNearbyLandService.getNearByValuePoints(13.04967, 74.82282);
		Map<String, Double> distMap = findNearbyLandService.getDistanceMapFromLand(list, land);
		List<Map<String, Double>> perAndPrice = findNearbyLandService.convertDistanceMapToPercentageAndPriceMap(distMap, 50000f);
		Map<String, Double> priceMap = perAndPrice.get(1);
		Map<String, Double> perMap = perAndPrice.get(0);
		System.out.println("Printing Sample map data");
		Double tempPrice = 0.0;
		System.out.println("Printing test Map");
		for(Entry<String, Double> entry : priceMap.entrySet()) {
			tempPrice += entry.getValue();
			System.out.println(entry.getKey()+"---->"+entry.getValue()+"---->"+perMap.get(entry.getKey())+" %");
		}
		System.out.println("test Map price = "+tempPrice);
		
		
		Map<String, Double> perMap2 = findNearbyLandService.converDistanceMapToPercentageMap(distMap);
		System.out.println("Printing test Map2---");
		for(Entry<String, Double> entry : perMap2.entrySet()) {
			tempPrice += entry.getValue();
			System.out.println(entry.getKey()+"---->"+entry.getValue()+" %");
		}

	}

}
