import { Routes } from '@angular/router';
import { RestautantListing } from './restautant-listing/component/restautant-listing';
import { FoodCatalogRoutingModule } from './food-catalog/food-catalog-routing-module';
import { FoodCatalog } from './food-catalog/component/food-catalog';
import { OrderSummary } from './order-summary/component/order-summary';

export const routes: Routes = [
    {path: '', component: RestautantListing},
    {path: 'food-catalogue/:id', component: FoodCatalog },
    { path: 'orderSummary', component: OrderSummary }
];
