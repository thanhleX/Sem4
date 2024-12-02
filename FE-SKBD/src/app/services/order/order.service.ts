import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Order } from '../../interfaces/order.interface';
import { API_BASE_URL } from '../../config/config';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  getAllOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(`${API_BASE_URL}/orders/`);
  }

  addOrder(order: Order): Observable<Order> {
    return this.http.post<Order>(`${API_BASE_URL}/orders/create`, order);
  }

  deleteOrder(id: number): Observable<Order> {
    return this.http.delete<Order>(`${API_BASE_URL}/orders/delete/${id}`);
  }

  getOrderById(id: number): Observable<Order> {
    return this.http.get<Order>(`${API_BASE_URL}/orders/get/${id}`);
  }

  // updateOrder(order: Order): Observable<Order> {
  //   return this.http.put<Order>(`${API_BASE_URL}/orders/${order.id}`, order);
  // }

  // Méthode pour mettre à jour une commande
  updateOrder(id: number, orderData: { idClient: number, state: string }): Observable<Order> {
    const url = `${API_BASE_URL}/orders/${id}`;
    return this.http.put<Order>(url, orderData);
  }
}
