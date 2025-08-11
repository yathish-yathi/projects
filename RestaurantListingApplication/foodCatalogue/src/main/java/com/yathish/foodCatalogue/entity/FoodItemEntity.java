package com.yathish.foodCatalogue.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodItemEntity {

    public FoodItemEntity(String itemName, String itemDescription, double price, int restaurantId, boolean isVeg, int quantity) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.price = price;
        this.restaurantId = restaurantId;
        this.isVeg = isVeg; // Assuming default is vegetarian
        this.quantity = quantity; // Default quantity
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String itemName;
    private String itemDescription;
    private Boolean isVeg;
    private Double price;
    private Integer restaurantId;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer quantity;

}
