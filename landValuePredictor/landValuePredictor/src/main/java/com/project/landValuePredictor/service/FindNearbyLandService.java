package com.project.landValuePredictor.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.landValuePredictor.DTO.LandValueDataDTO;
import com.project.landValuePredictor.MapRestClient.MapApi;
import com.project.landValuePredictor.configurations.ProjectConfigurations;
import com.project.landValuePredictor.entity.LandValueDataEntity;
import com.project.landValuePredictor.model.PlacesResponse;
import com.project.landValuePredictor.model.PlacesResponse.Place;
import com.project.landValuePredictor.repository.LandDataRepository;

import org.apache.commons.math3.stat.regression.SimpleRegression;

@Service
public class FindNearbyLandService {

	@Autowired
	private LandDataRepository landDataRepository;

	@Autowired
	private ProjectConfigurations projectConfigurations;

	@Autowired
	private MapApi mapApi;
	

	public Double predictLandPrice(Double latitude, Double longitude, Double area) {

		// Fetch nearby value points from google map of GIVEN land
		List<PlacesResponse> nearByValuePointList = getNearByValuePoints(latitude, longitude);

		// compute and return map => "type:name -> Distance" of all nearbyLandList of
		// GIVEN land
		LandValueDataDTO givenLand = new LandValueDataDTO(0.0f, latitude, longitude);
		Map<String, Double> DistanceMapFromLand = getDistanceMapFromLand(nearByValuePointList, givenLand);

		// compute percentage map of land under interest "type:name ->
		// nuetralDisntace(percentage)" of GIVEN land
		Map<String, Double> MapDistanceToPercentageOfLandunderInterest = converDistanceMapToPercentageMap(
				DistanceMapFromLand);

		// Fetch land data from DB near to given land of GIVEN land
		List<LandValueDataDTO> nearbyLandList = getNearbyLands(latitude, longitude);

		// compute nearbyLandList and return list of percentage and price Maps
		List<Object> result = computeNearbyLandsAndReturnModels(nearbyLandList);

		// retrieve data from result
		Map<String, SimpleRegression> valueNameAndSimpleRegressionMap = (Map) result.get(0);
		Set<String> valueNameTrainedWithMoreThanOneDataSet = (Set) result.get(1);

		Map<String, SimpleRegression> valueTypeAndSimpleRegressionMap = (Map) result.get(2);
		Set<String> valueTypeTrainedWithMoreThanOneDataSet = (Set) result.get(3);

		Map<String, Double> valueNameAndCrossMultiplier = (Map) result.get(4);

		// Calculate land price using computeNearbyLandsAndReturnModels and
		// convertMapDistanceToPercentage
		Double price = calculatePrice(MapDistanceToPercentageOfLandunderInterest, valueNameAndSimpleRegressionMap,
				valueNameTrainedWithMoreThanOneDataSet, valueTypeAndSimpleRegressionMap,
				valueTypeTrainedWithMoreThanOneDataSet, valueNameAndCrossMultiplier);

		price = priceForGivenArea(price, area);

		return price;
	}

	public Double calculatePrice(Map<String, Double> MapDistanceToPercentageOfLandunderInterest,
			Map<String, SimpleRegression> valueNameAndSimpleRegressionMap,
			Set<String> valueNameTrainedWithMoreThanOneDataSet,
			Map<String, SimpleRegression> valueTypeAndSimpleRegressionMap,
			Set<String> valueTypeTrainedWithMoreThanOneDataSet, Map<String, Double> valueNameAndCrossMultiplier) {

		// if key is present in Set that mean model present in Map is trained with more
		// than one value, so it can be used

		Double price = 0.0;

		for (Entry<String, Double> entry : MapDistanceToPercentageOfLandunderInterest.entrySet()) {

			String typeAndName[] = entry.getKey().split(":");
			String type = typeAndName[0];
			String name = typeAndName[1];
			Double percentageInfluence = entry.getValue();

			// 1. check if value name is present in NameSet
			if (valueNameTrainedWithMoreThanOneDataSet.contains(name)) {
				price += valueNameAndSimpleRegressionMap.get(name).predict(percentageInfluence);
				System.out.println(
						"1] " + name + "-->" + valueNameAndSimpleRegressionMap.get(name).predict(percentageInfluence)
								+ "-->" + percentageInfluence + " %");
			}
			// 2. check if value type is present in TypeSet
			else if (valueTypeTrainedWithMoreThanOneDataSet.contains(type)) {
				price += valueTypeAndSimpleRegressionMap.get(type).predict(percentageInfluence);
				System.out.println(
						"2] " + name + "-->" + valueTypeAndSimpleRegressionMap.get(type).predict(percentageInfluence)
								+ "-->" + percentageInfluence + " %");
			}
			// 3. check value name is present is map if yes use cross multiplication because
			// value is not present in set that
			// means its a single value
			else if (valueNameAndSimpleRegressionMap.containsKey(name)) {
				Double crossMultiplier = valueNameAndCrossMultiplier.get(name);
				price += (crossMultiplier * percentageInfluence);
				Double temp = (crossMultiplier * percentageInfluence);
				System.out.println("3] " + name + "-->" + temp + "-->" + percentageInfluence + " %");
			}
			// 4. if no data available on landValue point then return message to user to
			// include its value manually on top of predicted price
			else {
				System.out.println("4] " + "No suffient data availble for : " + name
						+ ", Please include its price manually on top of predicted price");
			}
		}

		return price;
	}

	// API will retrieve land data from DB within a configured distance
	public List<LandValueDataDTO> getNearbyLands(Double latitude, Double longitude) {

		Double latFrom = latitude - (projectConfigurations.latConst / 2);
		Double latTo = latitude + (projectConfigurations.latConst / 2);
		Double lngFrom = longitude - (projectConfigurations.lngConst / 2);
		Double lngTo = longitude + (projectConfigurations.lngConst / 2);

		System.out.println(latFrom + "," + latTo);
		System.out.println(lngFrom + "," + lngTo);

		List<LandValueDataEntity> lands = landDataRepository.findByLatBetweenAndLngBetween(latFrom, latTo, lngFrom,
				lngTo);

		// System.out.println(lands.toString());

		List<LandValueDataDTO> landsDTO = new ArrayList<>();

		for (LandValueDataEntity land : lands) {
			LandValueDataDTO landValueDataDTO = LandValueDataDTO.builder().price(land.getPrice()).lat(land.getLat())
					.lng(land.getLng()).build();
			landsDTO.add(landValueDataDTO);
		}

		return landsDTO;
	}

	// This API will return List of nearby value point around given lat and lng as
	// per configuration
	public List<PlacesResponse> getNearByValuePoints(Double latitude, Double longitude) {

		// Value points from configuration 'value.points'
		String valuePointsIncluded = projectConfigurations.valuePoints;
		String valuePointsArr[] = valuePointsIncluded.split(",");

		List<PlacesResponse> places = new ArrayList<>();
		for (String val : valuePointsArr) {
			// separation value points and its radius
			String PlaceTypeAndRadius[] = val.split("-");

			// converting string to Double and KM to M
			Double radius = (Double.parseDouble(PlaceTypeAndRadius[1])) * 1000;

			PlacesResponse placesResponse = mapApi.getNearbyPlaces(PlaceTypeAndRadius[0],
					projectConfigurations.maxResultCount, latitude, longitude, radius);

			if (placesResponse.getPlaces() == null)
				continue;

			placesResponse.setType(PlaceTypeAndRadius[0]);
			// System.out.println(placesResponse.getType());

			places.add(placesResponse);
		}

		// System.out.println(places.toString());

		return places;
	}

	// This API takes 2 coordinates and return distance between them
	public Double findDistaceBetweenCoOrdinates(Double FromLatitude, Double FromLongitude, Double ToLatitude,
			Double ToLongitude) {

		final double R = 6371; // Radius of Earth in km

		double lat_Rad1 = Math.toRadians(FromLatitude);
		double lat_Rad2 = Math.toRadians(ToLatitude);
		double lng_Rad1 = Math.toRadians(FromLongitude);
		double lng_Rad2 = Math.toRadians(ToLongitude);

		// Differences
		double deffLat = lat_Rad2 - lat_Rad1;
		double deffLng = lng_Rad2 - lng_Rad1;

		// Haversine formula
		double a = Math.pow(Math.sin(deffLat / 2), 2)
				+ Math.cos(lat_Rad1) * Math.cos(lat_Rad2) * Math.pow(Math.sin(deffLng / 2), 2);
		double distace = R * 2 * Math.asin(Math.sqrt(a)); // Distance in km

		return distace;
	}

	// takes list of PlacesResponse and returns map of type:id->distance where
	// distance is distance from LandValueDataDTO
	public Map<String, Double> getDistanceMapFromLand(List<PlacesResponse> responsOfAllPlaceTypes,
			LandValueDataDTO land) {

		Map<String, Double> distanceMap = new HashMap<>();
		for (PlacesResponse typeAndPlaces : responsOfAllPlaceTypes) {
			String type = typeAndPlaces.getType(); // type of current response
			for (Place place : typeAndPlaces.getPlaces()) {
				Double distance = findDistaceBetweenCoOrdinates(land.getLat(), land.getLng(),
						place.getLocation().getLatitude(), place.getLocation().getLongitude());
				String typeAndPlaceName = type + ":" + place.getId() + "-" + place.getDisplayName().getText();

				distanceMap.put(typeAndPlaceName, distance);
			}
		}

		return distanceMap;
	}

	// converts distance into remaining distance and converts to map1 of
	// percentage(influence) to neutralize with different ranges
	// and map2 of price influence
	public List<Map<String, Double>> convertDistanceMapToPercentageAndPriceMap(Map<String, Double> distanceMap,
			Float LandPrice) {

		// Map to store influence
		Map<String, Double> percentageMap = new HashMap<>();
		Double totalPercentage = 0.0;

		for (Entry<String, Double> entry : distanceMap.entrySet()) {
			String type = entry.getKey().split(":")[0];// get land from map
			Double range = projectConfigurations.valuePointRange().getOrDefault(type, 5.0);// get corresponding range to
																							// be considered

			Double percentage = ((range - entry.getValue()) / range) * 100; // represent distance as percentage to
																			// neutralize with different ranges
			totalPercentage += percentage;// find total percentage(influence) of individual of a land
			percentageMap.put(entry.getKey(), percentage);
		}

		for (Entry<String, Double> entry : percentageMap.entrySet()) {
			// converting remaining distance percentage to overall influence percentage on
			// LandData
			Double Percentage = (entry.getValue() / totalPercentage) * 100; // percentage when all value point is
																			// considered
			percentageMap.put(entry.getKey(), Percentage);
		}

		// Map to store price due to influence
		Map<String, Double> priceMap = new HashMap<>();

		for (Entry<String, Double> entry : percentageMap.entrySet()) {
			// Calculating price due to individual influence
			Double individualPrice = (entry.getValue() / 100) * LandPrice;
			// add individual price to price map
			priceMap.put(entry.getKey(), individualPrice);
		}

		// return influence and price map
		return Arrays.asList(percentageMap, priceMap);
	}

	// converts distance into remaining distance and converts to map1 of
	// percentage(influence) to neutralize with different ranges
	public Map<String, Double> converDistanceMapToPercentageMap(Map<String, Double> inDistance) {

		// Map to store influence percentage
		Map<String, Double> percentageMap = new HashMap<>();

		Double totalPercentage = 0.0;

		for (Entry<String, Double> entry : inDistance.entrySet()) {
			String type = entry.getKey().split(":")[0];// get land type to fetch its configured range
			Double range = projectConfigurations.valuePointRange().getOrDefault(type, 5.0);// get corresponding range to
																							// be considered

			Double percentage = ((range - entry.getValue()) / range) * 100; // represent distance as percentage to
																			// neutralize with different ranges
			totalPercentage += percentage;// find total percentage(influence) of individual of a land

			percentageMap.put(entry.getKey(), percentage);
		}

		for (Entry<String, Double> entry : percentageMap.entrySet()) {
			// converting remaining distance percentage to overall influence percentage on
			// LandData
			Double percentage = (entry.getValue() / totalPercentage) * 100; // percentage when all value point is
																			// considered

			percentageMap.put(entry.getKey(), percentage);
		}

		// return influence map
		return percentageMap;
	}

	public List<Object> computeNearbyLandsAndReturnModels(List<LandValueDataDTO> nearbyLandList) {

		// Map to track SimpleRegression model for each value point and type
		Map<String, SimpleRegression> valueNameAndSimpleRegressionMap = new HashMap<>();
		Map<String, SimpleRegression> valueTypeAndSimpleRegressionMap = new HashMap<>();

		// Set to track SimpleRegression model for each value point and type which have
		// more than one value added
		Set<String> valueNameAndSimpleRegressionKeyTrainedWithMoreThanOneDataSet = new HashSet<>();
		Set<String> valueTypeAndSimpleRegressionKeyTrainedWithMoreThanOneDataSet = new HashSet<>();

		// If SimpleRegression model trained with only single data them we can use
		// SimpleRegression to predict price
		// in such cases we use cross multiplication to predict price, below map is used
		// to store (x,y) data which is used to predict price of z
		// when cross multiply it becomes (zy/x) , so we can compute y/x now and
		// multiply z later
		Map<String, Double> valueNameAndCrossMultiplier = new HashMap<>();

		// Map to keep PlacesResponses data temporarily
		List<PlacesResponse> nearByValuePoints = new ArrayList<>();

		// Map to keep Distance data temporarily
		Map<String, Double> distanceMap = new HashMap<>();

		// Map to keep Percentage And Price data temporarily
		List<Map<String, Double>> percentageAndPriceMap = new ArrayList<>();

		// First get Distance map for every land data (nearbyLandList)
		for (LandValueDataDTO landValueDataDTO : nearbyLandList) {

			// getting nearby value point of current land
			nearByValuePoints = getNearByValuePoints(landValueDataDTO.getLat(), landValueDataDTO.getLng());

			// getting distanceMap for current land
			distanceMap = getDistanceMapFromLand(nearByValuePoints, landValueDataDTO);

			// getting percentageAndPriceMap for current land
			percentageAndPriceMap = convertDistanceMapToPercentageAndPriceMap(distanceMap, landValueDataDTO.getPrice());
			Map<String, Double> percentageMap = percentageAndPriceMap.get(0);
			Map<String, Double> priceMap = percentageAndPriceMap.get(1);

			System.out.println("training with land : " + landValueDataDTO.toString());
			// Create valueMap and update model as we traverse different land data maps
			for (Entry<String, Double> entry : percentageMap.entrySet()) {

				String currentKey = entry.getKey();
				String TypeAndName[] = currentKey.split(":", 2); // 2 makes two splits considering first occurrence of
																	// ":" , thus increses efficiency by not traversing
																	// further in string
				String typeOfVlauePoint = TypeAndName[0]; // type of current value point of current land
				String nameOfVlauePoint = TypeAndName[1]; // name of current value point of current land
				Double percentageInfuence = entry.getValue(); // percentageInfuence of current value point ON current
																// land
				Double priceInfluence = priceMap.get(entry.getKey()); // priceInfluence of current value point ON
																		// current land

				// check if model already exists if yes : train with current data , else :
				// create and train for both valueMap and TypeMap
				// Computing for value Name

				if (valueNameAndSimpleRegressionMap.containsKey(nameOfVlauePoint)) {
					// train
					valueNameAndSimpleRegressionMap.get(nameOfVlauePoint).addData(percentageInfuence, priceInfluence);
					System.out.println(
							"2] " + nameOfVlauePoint + " => " + percentageInfuence + " %  " + priceInfluence + " Rs");
					// tracking model trained with more than one data set
					valueNameAndSimpleRegressionKeyTrainedWithMoreThanOneDataSet.add(nameOfVlauePoint);

				} else {
					// else create new model
					valueNameAndSimpleRegressionMap.put(nameOfVlauePoint, new SimpleRegression());
					valueNameAndSimpleRegressionMap.get(nameOfVlauePoint).addData(percentageInfuence, priceInfluence);
					System.out.println(
							"1] " + nameOfVlauePoint + " => " + percentageInfuence + " %  " + priceInfluence + " Rs");
					valueNameAndCrossMultiplier.put(nameOfVlauePoint, (priceInfluence / percentageInfuence));
				}

				// Computing for value Type
				if (valueTypeAndSimpleRegressionMap.containsKey(typeOfVlauePoint)) {

					valueTypeAndSimpleRegressionMap.get(typeOfVlauePoint).addData(percentageInfuence, priceInfluence);
					valueTypeAndSimpleRegressionKeyTrainedWithMoreThanOneDataSet.add(typeOfVlauePoint);

				} else {
					valueTypeAndSimpleRegressionMap.put(typeOfVlauePoint, new SimpleRegression());
					valueTypeAndSimpleRegressionMap.get(typeOfVlauePoint).addData(percentageInfuence, priceInfluence);
				}

			}

		}
		System.out.println("----------name");
		valueNameAndSimpleRegressionMap.forEach((k, v) -> System.out.println(k + "-" + v));
		valueNameAndSimpleRegressionKeyTrainedWithMoreThanOneDataSet.forEach(k -> System.out.println(k));

		System.out.println("----------type");
		valueTypeAndSimpleRegressionMap.forEach((k, v) -> System.out.println(k + "-" + v));
		valueTypeAndSimpleRegressionKeyTrainedWithMoreThanOneDataSet.forEach(k -> System.out.println(k));

		return Arrays.asList(valueNameAndSimpleRegressionMap,
				valueNameAndSimpleRegressionKeyTrainedWithMoreThanOneDataSet, valueTypeAndSimpleRegressionMap,
				valueTypeAndSimpleRegressionKeyTrainedWithMoreThanOneDataSet, valueNameAndCrossMultiplier);
	}

	public Double priceForGivenArea(Double price, Double area) {

		SimpleRegression regression = new SimpleRegression();
		Map<Double, Double> CentMultiplier = projectConfigurations.priceChangeWithAreaData();
		CentMultiplier.forEach((k, v) -> {
			regression.addData(k, v);
		});

		Double finalPrice = price * regression.predict(area);

		return finalPrice;
	}

}
