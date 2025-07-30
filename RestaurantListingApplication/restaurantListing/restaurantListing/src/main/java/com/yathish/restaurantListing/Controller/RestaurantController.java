package com.yathish.restaurantListing.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yathish.restaurantListing.dto.RestaurantDTO;
import com.yathish.restaurantListing.entity.RestaurantEntity;
import com.yathish.restaurantListing.mapper.RestaurantMapper;
import com.yathish.restaurantListing.repository.RestaurantRepository;
import com.yathish.restaurantListing.service.RestaurantService;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/findAll")
    public ResponseEntity<List<RestaurantDTO>> findAllRestaurants() {
        List<RestaurantDTO> list = restaurantService.findAllRestaurants();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/add")
    public ResponseEntity<RestaurantDTO> addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        RestaurantDTO createdRestaurant = restaurantService.addRestaurant(restaurantDTO);
        return ResponseEntity.ok(createdRestaurant);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<RestaurantDTO> findRestaurantById(@PathVariable Integer id) {
        Optional<RestaurantEntity> restaurantEntity = restaurantRepository.findById(id);
        if(restaurantEntity.isPresent()) {
            RestaurantDTO restaurantDTO = RestaurantMapper.INSTANCE.restaurantEntityToRestaurantDTO(restaurantEntity.get());
            return ResponseEntity.ok(restaurantDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
