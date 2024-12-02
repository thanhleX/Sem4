import { Component, OnInit } from '@angular/core';
import { Product } from '../../interfaces/product.interface';
import { Category } from '../../interfaces/category.interface';
import { ProductService } from '../../services/product/product.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  featuredProducts: Product[] = [];
  categories: Category[] = [];
  loading = false;
  error = '';

  constructor(private productService: ProductService) {}

  ngOnInit() {
    this.loadCategories();
    this.loadFeaturedProducts();
  }

  loadCategories() {
    this.productService.getCategories().subscribe({
      next: (categories) => {
        this.categories = categories.map((catName, index) => ({
          idCat: index + 1,
          nameCat: catName,
          descriptionCat: `Explore our ${catName} collection`
        }));
      },
      error: (err) => {
        console.error('Error loading categories:', err);
      }
    });
  }

  loadFeaturedProducts() {
    this.loading = true;
    this.productService.getRandomProducts(4).subscribe({
      next: (products) => {
        this.featuredProducts = products;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load products';
        this.loading = false;
        console.error('Error:', err);
      }
    });
  }

  scrollToProducts() {
    const element = document.getElementById('featured-products');
    if (element) {
      element.scrollIntoView({ behavior: 'smooth' });
    }
  }
}
