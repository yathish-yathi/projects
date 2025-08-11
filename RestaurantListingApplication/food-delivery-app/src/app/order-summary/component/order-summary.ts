import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderService } from '../service/order.service';
import { OrderDTO } from '../model/orderDTO';

@Component({
  selector: 'app-order-summary',
  imports: [],
  templateUrl: './order-summary.html',
  styleUrl: './order-summary.css'
})
export class OrderSummary {

  orderSummary?: OrderDTO;
  obj: any;
  total?: any;
  showDialog: boolean = false;

  constructor(private route: ActivatedRoute, private orderService: OrderService, private router: Router) { }
  
  ngOnInit() {
    const data = this.route.snapshot.queryParams['data'];
    this.obj = JSON.parse(data);
    console.log('Received obj:', this.obj);
    this.obj.userId=1;
    this.orderSummary = this.obj;
    console.log('Received orderSummary:', this.orderSummary);
    this.total = (this.orderSummary && this.orderSummary.foodItemsList)
      ? this.orderSummary.foodItemsList.reduce((accumulator, currentValue) => {
          return accumulator + (currentValue.quantity * (currentValue.price ?? 0));
        }, 0)
      : 0;

  }

  saveOrder() {
    this.orderService.saveOrder(this.orderSummary)
      .subscribe(
        response => {
            this.showDialog = true;
        },
        error => {
          console.error('Failed to save data:', error);
        }
      );
  }

  closeDialog() {
    this.showDialog = false;
    this.router.navigate(['/']); // Replace '/home' with the actual route for your home page
  }

}
