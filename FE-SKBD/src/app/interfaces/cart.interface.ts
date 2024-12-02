import { CartItem } from "./cart-item.interface";

export interface Cart {
  id: number;
  namecart: string;
  totalPrice: number;
  cartItems: CartItem[];
  totalItems: number;
}
