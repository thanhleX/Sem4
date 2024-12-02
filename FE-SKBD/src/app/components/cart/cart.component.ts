import { Component, OnInit } from '@angular/core';
import { CartService } from '../../services/cart/cart.service';
import { Cart } from '../../interfaces/cart.interface';
import { CartItem } from '../../interfaces/cart-item.interface';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cart: Cart = {
    id: 1,
    namecart: 'My Cart',
    totalPrice: 0,
    cartItems: [],
    totalItems: 0
  };
  loading = false;
  error = '';

  constructor(
    private cartService: CartService,
    private router: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {
    this.loadCart();
  }

  loadCart(): void {
    this.loading = true;
    this.cartService.getcartById(1).subscribe({
      next: (cart) => {
        this.cart = cart;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load cart';
        this.loading = false;
        console.error('Error loading cart:', err);
      }
    });
  }

  updateQuantity(item: CartItem, change: number): void {
    const newQuantity = item.quantity + change;
    if (newQuantity >= 1) {
      item.quantity = newQuantity;
      item.totalExcludeTaxe = item.product.price * newQuantity;
      item.totalWithTaxe = item.totalExcludeTaxe * 1.2; // Assuming 20% tax
      
      this.cartService.updatecart(this.cart).subscribe({
        next: (updatedCart) => {
          this.cart = updatedCart;
          this.toastr.success('Cart updated successfully');
        },
        error: (err) => {
          console.error('Error updating cart:', err);
          this.toastr.error('Failed to update cart');
        }
      });
    }
  }

  removeItem(item: CartItem): void {
    this.cartService.removeItemFromCart(item).subscribe({
      next: () => {
        this.loadCart();
        this.toastr.success('Item removed from cart');
      },
      error: (err) => {
        console.error('Error removing item:', err);
        this.toastr.error('Failed to remove item');
      }
    });
  }

  clearCart(): void {
    this.cartService.clearAllcartItems().subscribe({
      next: () => {
        this.loadCart();
        this.toastr.success('Cart cleared successfully');
      },
      error: (err) => {
        console.error('Error clearing cart:', err);
        this.toastr.error('Failed to clear cart');
      }
    });
  }

  proceedToCheckout(): void {
    if (this.cart.cartItems.length === 0) {
      this.toastr.warning('Your cart is empty');
      return;
    }
    this.router.navigate(['/checkout']);
  }

  getTotalPrice(): number {
    return this.cart.cartItems.reduce((total, item) => 
      total + item.totalWithTaxe, 0);
  }

  continueShopping(): void {
    this.router.navigate(['/products']);
  }
}
