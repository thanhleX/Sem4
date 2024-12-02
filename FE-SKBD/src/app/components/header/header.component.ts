import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { Router } from '@angular/router';
import { Cart } from '../../interfaces/cart.interface';
import { CartService } from '../../services/cart/cart.service';
import { ToastrService } from 'ngx-toastr';
import { CartItem } from '../../interfaces/cart-item.interface';
import { User } from '../../interfaces/user.interface';
import { Product } from '../../interfaces/product.interface';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isLoginAsideOpen = false;
  isCartAsideOpen = false;
  username = '';
  password = '';
  cart: Cart = {
    id: 1,
    namecart: 'Default Cart',
    totalPrice: 0,
    cartItems: [],
    totalItems: 0
  };
  cartItems: CartItem[] = [];
  showPassword = false;

  constructor(
    private authService: AuthService,
    private router: Router,
    private cartService: CartService,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {
    if (this.isLoggedIn()) {
      this.loadCart();
    }
  }

  isLoggedIn(): boolean {
    return !!this.authService.isLoggedIn;
  }

  onLogin(): void {
    const user = new User();
    user.username = this.username;
    user.password = this.password;
    user.enabled = true;
    user.roles = [];
    
    this.authService.login(user).subscribe({
      next: (response) => {
        const jwt = response.headers.get('Authorization');
        if (jwt) {
          this.authService.saveToken(jwt);
          this.closeLoginAside();
          this.resetLoginForm();
          this.loadCart();
          this.toastr.success('Login successful!');
        }
      },
      error: (error) => {
        this.toastr.error('Login failed: ' + error.message);
      }
    });
  }

  private resetLoginForm(): void {
    this.username = '';
    this.password = '';
  }

  private loadCart(): void {
    this.cartService.getcartById(1).subscribe({
      next: (cart: Cart) => {
        this.cart = cart;
        this.cartItems = cart.cartItems;
      },
      error: (error: HttpErrorResponse) => {
        console.error('Error fetching cart:', error.message);
      }
    });
  }

  getTotalPrice(): number {
    return this.cartItems.reduce((total, item) => 
      total + item.totalWithTaxe, 0);
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
          this.cartItems = updatedCart.cartItems;
        },
        error: (error) => console.error('Error updating cart:', error)
      });
    }
  }

  removeItem(itemId: number): void {
    const itemToRemove = this.cartItems.find(item => item.id === itemId);
    if (itemToRemove) {
      this.cartService.removeItemFromCart(itemToRemove).subscribe({
        next: () => {
          this.loadCart();
        },
        error: (error) => console.error('Error removing item:', error)
      });
    }
  }

  // Navigation methods remain the same
  navigateToHome(): void {
    this.router.navigate(['/']);
  }

  goToSignup(): void {
    this.closeLoginAside();
    this.router.navigate(['/sign-up']);
  }

  goToForgetPass(): void {
    this.closeLoginAside();
    this.router.navigate(['/forget-pass']);
  }

  proceedToCheckout(): void {
    this.closeCartAside();
    this.router.navigate(['/checkout']);
  }

  // Aside control methods
  toggleLoginAside(): void {
    this.isLoginAsideOpen = !this.isLoginAsideOpen;
  }

  closeLoginAside(): void {
    this.isLoginAsideOpen = false;
  }

  handleCartClick(): void {
    if (this.isLoggedIn()) {
      this.toggleCartAside();
    } else {
      this.toggleLoginAside();
    }
  }

  toggleCartAside(): void {
    this.isCartAsideOpen = !this.isCartAsideOpen;
  }

  closeCartAside(): void {
    this.isCartAsideOpen = false;
  }

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }
}
