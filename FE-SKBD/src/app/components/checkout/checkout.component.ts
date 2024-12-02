import { Component, OnInit } from '@angular/core';
import { Order, OrderState } from '../../interfaces/order.interface';
import { CartService } from '../../services/cart/cart.service';
import { OrderService } from '../../services/order/order.service';
import { CartItem } from '../../interfaces/cart-item.interface';
import { Client } from '../../interfaces/client.interface';
import { ClientService } from '../../services/client/client.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  cartItems: CartItem[] = [];
  client: Client | null = null;
  isEditingAddress = false;
  loading = false;
  error = '';

  constructor(
    private cartService: CartService,
    private orderService: OrderService,
    private clientService: ClientService,
    private toastr: ToastrService,
    private router: Router
  ) {}

  ngOnInit() {
    this.loadCartItems();
    this.loadClientInfo();
  }

  private loadCartItems() {
    this.loading = true;
    this.cartService.getcartById(1).subscribe({
      next: (cart) => {
        this.cartItems = cart.cartItems;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load cart items';
        this.loading = false;
        console.error('Error loading cart:', err);
      }
    });
  }

  private loadClientInfo() {
    // Assuming we have the client ID from auth service or similar
    this.clientService.getClientById(1).subscribe({
      next: (client) => {
        this.client = client;
      },
      error: (err) => {
        console.error('Error loading client info:', err);
        this.toastr.error('Failed to load client information');
      }
    });
  }

  getTotalPrice(): number {
    return this.cartItems.reduce((total, item) => total + item.totalWithTaxe, 0);
  }

  updateClientInfo() {
    if (!this.client) return;
    
    this.clientService.updateClient(this.client).subscribe({
      next: (updatedClient) => {
        this.client = updatedClient;
        this.isEditingAddress = false;
        this.toastr.success('Address updated successfully');
      },
      error: (err) => {
        console.error('Error updating client info:', err);
        this.toastr.error('Failed to update address');
      }
    });
  }

  placeOrder() {
    if (!this.client) {
      this.toastr.error('Client information is required');
      return;
    }

    const newOrder: Order = {
      id: 0, // Will be set by backend
      idClient: this.client.id,
      state: OrderState.CONFIRMED,
      client: this.client,
      date: new Date().toISOString(),
      cartItems: this.cartItems,
      total: this.getTotalPrice()
    };

    this.orderService.addOrder(newOrder).subscribe({
      next: (order) => {
        this.toastr.success('Order placed successfully');
        this.cartService.clearAllcartItems().subscribe({
          next: () => {
            this.router.navigate(['/orders', order.id]);
          },
          error: (err) => console.error('Error clearing cart:', err)
        });
      },
      error: (err) => {
        console.error('Error placing order:', err);
        this.toastr.error('Failed to place order');
      }
    });
  }
}
