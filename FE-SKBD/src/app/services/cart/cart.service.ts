import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cart } from '../../interfaces/cart.interface';
import { CartItem } from '../../interfaces/cart-item.interface';
import { Observable } from 'rxjs';
import { API_BASE_URL } from '../../config/config';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) { }

  addcart(cart: Cart): Observable<Cart> {
    return this.http.post<Cart>(`${API_BASE_URL}/orders/create`, cart);
  }

  deletecart(id: number): Observable<Cart> {
    return this.http.delete<Cart>(`${API_BASE_URL}/cart/${id}`);
  }

  getcartById(id: number): Observable<Cart> {
    return this.http.get<Cart>(`${API_BASE_URL}/cart/${id}`);
  }


  updatecart(cart: Cart): Observable<Cart> {
    return this.http.put<Cart>(`${API_BASE_URL}/Cart/${cart.id}`, cart);
  }


  getTotalPrice(): Observable<number> {
    return this.http.get<number>(`${API_BASE_URL}/Cart/1/totalPrice`);
  }

  addItemToCart(cartItem: CartItem): Observable<CartItem> {
    return this.http.post<CartItem>(`${API_BASE_URL}/Cart/items`, cartItem);
  }

  removeItemFromCart(cartItem: CartItem): Observable<void> {
    return this.http.delete<void>(`${API_BASE_URL}/Cart/1/items/1`, { body: cartItem });
  }

  clearAllcartItems(): Observable<void> {
    return this.http.delete<void>(`${API_BASE_URL}/cart/1`);
  }


}
