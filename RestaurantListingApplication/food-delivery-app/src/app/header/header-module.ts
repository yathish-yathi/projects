import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HeaderRoutingModule } from './header-routing-module';
import { Header } from './component/header';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HeaderRoutingModule,
    Header
  ],
  exports: [Header]
})
export class HeaderModule { }
