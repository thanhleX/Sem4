import { Category } from './category.interface';
import { ProductImage } from './product-image.interface';

export interface Product {
  idProd: number;
  nameProd: string;
  price: number;
  rating: number;
  date: string;
  description: string;
  images: ProductImage[];
  category: Category;
}
