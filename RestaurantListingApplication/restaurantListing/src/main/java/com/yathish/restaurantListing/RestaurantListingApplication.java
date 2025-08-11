package com.yathish.restaurantListing;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yathish.restaurantListing.entity.RestaurantEntity;
import com.yathish.restaurantListing.repository.RestaurantRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class RestaurantListingApplication {

	@Autowired
	private RestaurantRepository restaurantRepository;

	public static void main(String[] args) {
		SpringApplication.run(RestaurantListingApplication.class, args);
	}

	//@PostConstruct
	public void addRestaurantData() {
		List<RestaurantEntity> restaurants = List.of(
			new RestaurantEntity( "The Spice Route", "Bangalore", "123 Spice St", "Authentic Indian Cuisine"),
			new RestaurantEntity( "Sushi World", "Tokyo", "456 Sushi Ave", "Fresh Sushi and Sashimi"),
			new RestaurantEntity( "Pasta Palace", "Rome", "789 Pasta Blvd", "Traditional Italian Pasta Dishes"),
			new RestaurantEntity( "Taco Fiesta", "Mexico City", "101 Taco Lane", "Spicy Mexican Tacos"),
			new RestaurantEntity( "Curry Corner", "Delhi", "202 Curry Road", "Delicious Indian Curries"),
			new RestaurantEntity( "Burger Haven", "New York", "303 Burger Blvd", "Juicy American Burgers")
		);
		restaurantRepository.saveAll(restaurants);
	}

}
