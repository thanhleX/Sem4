import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../../services/product/product.service';
import { Product } from '../../interfaces/product.interface';
import { Category } from '../../interfaces/category.interface';

interface Filter {
  price: {
    min: number;
    max: number;
  };
  sizes: string[];
  colors: string[];
  gender: string[];
}

interface ExpandedSections {
  price: boolean;
  size: boolean;
  color: boolean;
  gender: boolean;
}

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];
  loading = false;
  error = '';
  currentCategory: string = '';
  categories: Category[] = [];

  filter: Filter = {
    price: { min: 0, max: 1000 },
    sizes: [],
    colors: [],
    gender: []
  };

  // Filter options
  availableSizes = ['XS', 'S', 'M', 'L', 'XL', 'XXL'];
  availableColors = ['Black', 'White', 'Red', 'Blue', 'Green', 'Gray'];
  availableGender = ['Men', 'Women', 'Unisex'];
  priceRanges = [
    { min: 0, max: 50, label: 'Under $50' },
    { min: 50, max: 100, label: '$50 - $100' },
    { min: 100, max: 200, label: '$100 - $200' },
    { min: 200, max: 500, label: '$200 - $500' },
    { min: 500, max: 1000, label: 'Over $500' }
  ];

  expandedSections: ExpandedSections = {
    price: true,
    size: false,
    color: false,
    gender: false
  };

  currentSort: string = '';
  sortedProducts: Product[] = [];

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    // Load categories
    this.loadCategories();

    // Subscribe to route params to get category
    this.route.params.subscribe(params => {
      this.currentCategory = params['category'] || '';
      this.loadProducts();
    });
  }

  loadCategories() {
    this.productService.getCategories().subscribe({
      next: (categories) => {
        this.categories = categories.map(catName => ({
          idCat: 0,
          nameCat: catName,
          descriptionCat: ''
        }));
      },
      error: (err) => {
        console.error('Error loading categories:', err);
      }
    });
  }

  loadProducts() {
    this.loading = true;
    this.productService.getProductsByCategory(this.currentCategory).subscribe({
      next: (products) => {
        this.products = this.applyFilters(products);
        this.sortProducts();
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load products';
        this.loading = false;
        console.error('Error:', err);
      }
    });
  }

  toggleSize(size: string) {
    const index = this.filter.sizes.indexOf(size);
    if (index === -1) {
      this.filter.sizes.push(size);
    } else {
      this.filter.sizes.splice(index, 1);
    }
    this.loadProducts();
  }

  toggleColor(color: string) {
    const index = this.filter.colors.indexOf(color);
    if (index === -1) {
      this.filter.colors.push(color);
    } else {
      this.filter.colors.splice(index, 1);
    }
    this.loadProducts();
  }

  toggleGender(gender: string) {
    const index = this.filter.gender.indexOf(gender);
    if (index === -1) {
      this.filter.gender.push(gender);
    } else {
      this.filter.gender.splice(index, 1);
    }
    this.loadProducts();
  }

  setPriceRange(min: number, max: number) {
    this.filter.price = { min, max };
    this.loadProducts();
  }

  private applyFilters(products: Product[]): Product[] {
    return products.filter(product => {
      const priceMatch = product.price >= this.filter.price.min &&
        product.price <= this.filter.price.max;

      const sizeMatch = this.filter.sizes.length === 0;
      const colorMatch = this.filter.colors.length === 0;
      const genderMatch = this.filter.gender.length === 0;

      return priceMatch && sizeMatch && colorMatch && genderMatch;
    });
  }

  clearFilters() {
    this.filter = {
      price: { min: 0, max: 1000 },
      sizes: [],
      colors: [],
      gender: []
    };
    this.loadProducts();
  }

  toggleSection(section: keyof ExpandedSections): void {
    this.expandedSections[section] = !this.expandedSections[section];
  }

  hasActiveFilters(): boolean {
    return (
      this.filter.sizes.length > 0 ||
      this.filter.colors.length > 0 ||
      this.filter.gender.length > 0 ||
      this.filter.price.min !== 0 ||
      this.filter.price.max !== 1000
    );
  }

  viewProductDetails(productId: number) {
    this.router.navigate(['/product', productId]);
  }

  onSortChange(event: Event) {
    const select = event.target as HTMLSelectElement;
    this.currentSort = select.value;
    this.sortProducts();
  }

  sortProducts() {
    this.sortedProducts = [...this.products];
    
    switch (this.currentSort) {
      case 'price-asc':
        this.sortedProducts.sort((a, b) => a.price - b.price);
        break;
      case 'price-desc':
        this.sortedProducts.sort((a, b) => b.price - a.price);
        break;
      case 'rating-desc':
        this.sortedProducts.sort((a, b) => b.rating - a.rating);
        break;
      case 'rating-asc':
        this.sortedProducts.sort((a, b) => a.rating - b.rating);
        break;
      default:
        // Default sorting (you can implement your own default sort logic)
        break;
    }
  }
}
