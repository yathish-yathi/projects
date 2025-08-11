import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FoodCatalog } from './component/food-catalog';

const routes: Routes = [
   { path: 'food-catalogue/:id', component: FoodCatalog }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FoodCatalogRoutingModule { }
