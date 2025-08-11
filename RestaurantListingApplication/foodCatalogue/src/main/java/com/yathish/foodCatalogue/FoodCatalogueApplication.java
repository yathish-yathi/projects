package com.yathish.foodCatalogue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.yathish.foodCatalogue.entity.FoodItemEntity;
import com.yathish.foodCatalogue.repository.FoodCatalogueRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class FoodCatalogueApplication {

	@Autowired
	private FoodCatalogueRepository foodCatalogueRepository;

	public static void main(String[] args) {
		SpringApplication.run(FoodCatalogueApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	//@PostConstruct
	public void addFoodCatalogueData() {
		List<FoodItemEntity> foodItems = List.of(
			new FoodItemEntity("Paneer Butter Masala", "Creamy paneer dish", 150.0, 1, true, 10),
			new FoodItemEntity("Chicken Biryani", "Spicy chicken rice dish", 200.0, 1, false, 15),
			new FoodItemEntity("Veg Fried Rice", "Stir-fried rice with vegetables", 120.0, 2, true, 20),
			new FoodItemEntity("Fish Curry", "Spicy fish curry", 250.0, 2, false, 5),
			new FoodItemEntity("Cheese Pizza", "Pizza topped with cheese", 300.0, 1, true, 8)
		);
		foodCatalogueRepository.saveAll(foodItems);
	}

}
