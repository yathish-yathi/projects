import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Header } from './header/component/header';
import { RestautantListingModule } from './restautant-listing/restautant-listing-module';
import { FoodCatalogModule } from './food-catalog/food-catalog-module';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet, 
    Header, 
    RestautantListingModule, 
    FoodCatalogModule
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('food-delivery-app');
  
}
