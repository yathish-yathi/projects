import { Component } from '@angular/core';
import { FoodCataloguePage } from '../../shared/models/FoodCataloguePage';
import { FoodItem } from '../../shared/models/FoodItem';
import { ActivatedRoute, Router } from '@angular/router';
import { FoodItemService } from '../service/foodItem.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-food-catalog',
  imports: [CommonModule],
  templateUrl: './food-catalog.html',
  styleUrl: './food-catalog.css'
})
export class FoodCatalog {

  restaurantId: number;
  foodItemResponse: FoodCataloguePage;
  foodItemCart: FoodItem[] = [];
  orderSummary: FoodCataloguePage;


  constructor(private route: ActivatedRoute, private foodItemService: FoodItemService, private router: Router) {
  }

  ngOnInit() {

    this.route.paramMap.subscribe(params => {
      const idParam = params.get('id');
      this.restaurantId = idParam !== null ? +idParam : 0;
    });

    this.getFoodItemsByRestaurant(this.restaurantId);

  }

  getFoodItemsByRestaurant(restaurant: number) {
    this.foodItemService.getFoodItemsByRestaurantFromService(restaurant).subscribe(
      data => {
        this.foodItemResponse = data;
        console.log('Food items response:', this.foodItemResponse);
        // Initialize quantity for each food item
        this.foodItemResponse.foodItemsList.forEach(item => {
          item.quantity = 0;
        });
      }
    )
  }

  increment(food: any) {
    food.quantity++;
    const index = this.foodItemCart.findIndex(item => item.id === food.id);
    if (index === -1) {
      // If record does not exist, add it to the array
      this.foodItemCart.push(food);
    } else {
      // If record exists, update it in the array
      this.foodItemCart[index] = food;
    }
  }

  decrement(food: any) {
    if (food.quantity > 0) {
      food.quantity--;

      const index = this.foodItemCart.findIndex(item => item.id === food.id);
      if (this.foodItemCart[index].quantity == 0) {
        this.foodItemCart.splice(index, 1);
      } else {
        // If record exists, update it in the array
        this.foodItemCart[index] = food;
      }

    }
  }

  onCheckOut() {
    this.foodItemCart;
    this.orderSummary = {
      foodItemsList: [],
      restaurant: {} as any
    }
    console.log('foodItemCart:', this.foodItemCart);
    this.orderSummary.foodItemsList = this.foodItemCart;
    this.orderSummary.restaurant = this.foodItemResponse.restaurant;
    this.router.navigate(['/orderSummary'], { queryParams: { data: JSON.stringify(this.orderSummary) } });
  }


}
