import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../../services/product/product.service';
import { Product } from '../../interfaces/product.interface';
import { CartService } from '../../services/cart/cart.service';
import { CartItem } from '../../interfaces/cart-item.interface';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  product: Product | null = null;
  quantity: number = 1;
  loading: boolean = true;
  error: string = '';

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private cartService: CartService
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const id = params['id'];
      this.loadProduct(id);
    });
  }

  loadProduct(id: string) {
    this.loading = true;
    this.productService.getProductById(id).subscribe({
      next: (product) => {
        this.product = product;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load product details';
        this.loading = false;
        console.error('Error loading product:', err);
      }
    });
  }

  addToCart() {
    if (!this.product) return;

    const cartItem: CartItem = {
      id: 0, // Will be set by backend
      product: this.product,
      quantity: this.quantity,
      totalExcludeTaxe: this.product.price * this.quantity,
      totalWithTaxe: this.product.price * this.quantity * 1.2, // Assuming 20% tax
      cart: {
        id: 1, // You might need to get this from a cart service
        namecart: 'Default Cart',
        totalPrice: 0,
        cartItems: [],
        totalItems: 0
      }
    };

    this.cartService.addItemToCart(cartItem).subscribe({
      next: (response) => {
        console.log('Item added to cart successfully', response);
        // You might want to show a success message or update cart count
      },
      error: (err) => {
        console.error('Error adding item to cart:', err);
        // Show error message to user
      }
    });
  }

  updateQuantity(change: number) {
    const newQuantity = this.quantity + change;
    if (newQuantity >= 1) {
      this.quantity = newQuantity;
    }
  }
}