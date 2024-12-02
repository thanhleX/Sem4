import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Client } from '../../interfaces/client.interface';
import { Order } from '../../interfaces/order.interface';
import { ClientService } from '../../services/client/client.service';
import { OrderService } from '../../services/order/order.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {
  activeTab: string = 'profile';
  client!: Client;
  orders: Order[] = [];
  isLoading: boolean = false;
  currentPassword: string = '';
  newPassword: string = '';
  confirmPassword: string = '';

  constructor(
    private clientService: ClientService,
    private orderService: OrderService,
    private router: Router,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.loadClientProfile();
    this.loadClientOrders();
  }

  loadClientProfile(): void {
    const clientId = localStorage.getItem('userId'); // Assuming you store userId in localStorage
    if (!clientId) {
      this.router.navigate(['/login']);
      return;
    }

    this.clientService.getClientById(Number(clientId)).subscribe({
      next: (client) => {
        this.client = client;
      },
      error: (error) => {
        this.toastr.error('Error loading profile');
        console.error('Error:', error);
      }
    });
  }

  loadClientOrders(): void {
    const clientId = localStorage.getItem('userId');
    if (!clientId) return;

    this.orderService.getAllOrders().subscribe({
      next: (orders) => {
        this.orders = orders;
      },
      error: (error) => {
        this.toastr.error('Error loading orders');
        console.error('Error:', error);
      }
    });
  }

  updateProfile(): void {
    if (!this.client) return;
    
    this.isLoading = true;
    this.clientService.updateClient(this.client).subscribe({
      next: (updatedClient) => {
        this.client = updatedClient;
        this.toastr.success('Profile updated successfully');
      },
      error: (error) => {
        this.toastr.error('Error updating profile');
        console.error('Error:', error);
      },
      complete: () => {
        this.isLoading = false;
      }
    });
  }

  changePassword(): void {
    if (this.newPassword !== this.confirmPassword) {
      this.toastr.error('Passwords do not match');
      return;
    }

    // Add password change logic here
    // You'll need to create a new method in ClientService for this
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('userId');
    this.router.navigate(['/login']);
  }

  switchTab(tab: string): void {
    this.activeTab = tab;
  }

  getOrderStatusClass(status: string): string {
    switch (status.toLowerCase()) {
      case 'delivered': return 'status-delivered';
      case 'processing': return 'status-processing';
      case 'shipped': return 'status-shipped';
      default: return 'status-pending';
    }
  }
}
