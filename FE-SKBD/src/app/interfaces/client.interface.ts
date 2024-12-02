import { Order } from "./order.interface";

export interface Client {
  id: number;
  username: string;
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  address: string;
  state?: string; // "ACTIVE" or "INACTIVE"
  orders?: Order[];  
}
