import { Component, OnInit } from '@angular/core';

import { Router, RouterModule } from '@angular/router';
import { RestaurantService } from '../service/restaurant.service';
import { Restaurant } from '../../shared/models/Restaurant';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-restautant-listing',
  imports: [CommonModule, RouterModule],
  templateUrl: './restautant-listing.html',
  styleUrl: './restautant-listing.css'
})
export class RestautantListing implements OnInit{

  public restaurantList: Restaurant[] = [];

   ngOnInit() {
    this.getAllRestaurants();
  }

  constructor(private router: Router, private restaurantService: RestaurantService) { }

  getAllRestaurants() {
    this.restaurantService.getAllRestaurants().subscribe(
      data => {
        this.restaurantList = data;
      }
    )
  }

   getRandomNumber(min: number, max: number): number {
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }


  getRandomImage(): string {
    const imageCount = 8; // Adjust this number based on the number of images in your asset folder
    const randomIndex = this.getRandomNumber(1, imageCount);
    return `${randomIndex}.jpg`; // Replace with your image filename pattern
  }

   onButtonClick(id: number) {
    this.router.navigate(['/food-catalogue', id]);
  }


}
