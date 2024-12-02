import { Product } from './product.interface';
import { Order } from './order.interface';
import { Cart } from './cart.interface';

export interface CartItem {
  id: number;
  cart: Cart;
  product: Product;
  quantity: number;
  totalExcludeTaxe: number;
  totalWithTaxe: number;
  order?: Order;
}
