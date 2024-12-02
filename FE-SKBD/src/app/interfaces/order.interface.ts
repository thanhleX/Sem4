import { CartItem } from "./cart-item.interface";
import { Client } from "./client.interface";

export interface Order {
  id: number;
  idClient: number;
  state: OrderState;
  client: Client;
  date: string; // Ajout de la propriété de date
  cartItems: CartItem[];
  total: number;
}

export enum OrderState {
  CANCELED = 'CANCELED',
  OPTION = 'OPTION',
  CONFIRMED = 'CONFIRMED'
}

