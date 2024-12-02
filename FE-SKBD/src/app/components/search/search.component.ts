import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from '../../services/product/product.service';
import { debounceTime, distinctUntilChanged, Subject, switchMap } from 'rxjs';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  searchQuery = '';
  categories: string[] = [];
  showDropdown = false;
  private searchSubject = new Subject<string>();

  constructor(
    private productService: ProductService,
    private router: Router
  ) {}

  ngOnInit() {
    this.searchSubject.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap(query => this.productService.searchCategories(query))
    ).subscribe(results => {
      this.categories = results;
      this.showDropdown = results.length > 0;
    });
  }

  onSearch(event: Event) {
    const target = event.target as HTMLInputElement;
    this.searchQuery = target.value;
    this.searchSubject.next(target.value);
  }

  selectCategory(category: string) {
    this.searchQuery = category;
    this.showDropdown = false;
    this.router.navigate(['/products', category.toLowerCase()]);
  }

  onFocus() {
    if (this.searchQuery) {
      this.showDropdown = true;
    }
  }

  onBlur() {
    setTimeout(() => {
      this.showDropdown = false;
    }, 200);
  }
}
