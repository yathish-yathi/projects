import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RestautantListing } from './component/restautant-listing';

const routes: Routes = [
  {path: '', component: RestautantListing}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RestautantListingRoutingModule { }
