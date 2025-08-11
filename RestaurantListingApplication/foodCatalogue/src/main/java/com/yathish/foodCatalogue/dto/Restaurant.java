package com.yathish.foodCatalogue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {

    private Integer id;
    private String name;
    private String city;
    private String address;
    private String description;
    
}
