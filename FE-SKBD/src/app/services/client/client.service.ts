import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API_BASE_URL } from '../../config/config';
import { Observable } from 'rxjs';
import { Client } from '../../interfaces/client.interface';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http: HttpClient) { }

  getAllClients(): Observable<Client[]> {
    return this.http.get<Client[]>(`${API_BASE_URL}/u`);
  }

  addClient(client: Client): Observable<Client> {
    return this.http.post<Client>(`${API_BASE_URL}/clients`, client);
  }
  deleteClient(id: number): Observable<Client> {
    return this.http.delete<Client>(`${API_BASE_URL}/clients/${id}`);
  }

  getClientById(id: number): Observable<Client> {
    return this.http.get<Client>(`${API_BASE_URL}/clients/${id}`);
  }
  updateClient(client: Client): Observable<Client> {
    return this.http.put<Client>(`${API_BASE_URL}/clients/${client.id}`, client);
  }

  changePassword(clientId: number, currentPassword: string, newPassword: string): Observable<any> {
    return this.http.post(`${API_BASE_URL}/clients/${clientId}/change-password`, {
      currentPassword,
      newPassword
    });
  }
}
