import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Observable, throwError, map } from 'rxjs';
import { catchError, retry, tap } from 'rxjs/operators';
import { Product } from '../../interfaces/product.interface';

export interface PaginatedResponse {
  items: Product[];
  total: number;
}

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = 'http://localhost:3000/products';

  constructor(private http: HttpClient) { }

  getProducts(page: number = 1, pageSize: number = 8): Observable<PaginatedResponse> {
    const params = new HttpParams()
      .set('_page', page.toString())
      .set('_limit', pageSize.toString());

    return this.http.get<Product[]>(this.apiUrl, {
      params,
      observe: 'response'
    }).pipe(
      retry(3),
      catchError(this.handleError),
      map(response => ({
        items: response.body as Product[],
        total: Number(response.headers.get('X-Total-Count') || 0)
      }))
    );
  }

  getProductById(id: string): Observable<Product> {
    return this.http.get<Product>(`${this.apiUrl}?idProd=${id}`).pipe(
      tap(product => {
        console.log('Product data received:', product);
        if (!product) {
          throw new Error('Product not found');
        }
        if (!product.images || product.images.length === 0) {
          product.images = [{
            id: 0,
            url: 'assets/images/placeholder.jpg',
            alt: 'Product image not available',
            isPrimary: true
          }];
        }
      }),
      catchError(this.handleError)
    );
  }

  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiUrl);
  }

  getRandomProducts(count: number): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiUrl).pipe(
      map(products => {
        const shuffled = [...products].sort(() => 0.5 - Math.random());
        return shuffled.slice(0, count);
      })
    );
  }

  getCategories(): Observable<string[]> {
    return this.getAllProducts().pipe(
      map(products => {
        const categories = new Set(products.map(p => p.category.nameCat));
        return Array.from(categories).sort();
      })
    );
  }

  getProductsByCategory(category: string): Observable<Product[]> {
    if (!category) {
      return this.getAllProducts();
    }
    return this.http.get<Product[]>(`${this.apiUrl}?category=${category}`);
  }

  searchCategories(query: string): Observable<string[]> {
    return this.getCategories().pipe(
      map(categories => 
        categories.filter(cat => 
          cat.toLowerCase().includes(query.toLowerCase())
        )
      )
    );
  }

  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'An error occurred';
    if (error.error instanceof ErrorEvent) {
      errorMessage = `Error: ${error.error.message}`;
    } else {
      if (error.status === 0) {
        errorMessage = 'Unable to connect to the server. Please check if JSON Server is running.';
      } else {
        errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
      }
    }
    console.error(errorMessage);
    return throwError(() => errorMessage);
  }
}
