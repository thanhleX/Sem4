import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule, provideHttpClient, withFetch, withInterceptors, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common'
import { NgbPagination } from '@ng-bootstrap/ng-bootstrap';
import { ToastrModule } from 'ngx-toastr';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HeaderComponent } from './components/header/header.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { ProductDetailComponent } from './components/product-detail/product-detail.component';
import { CartComponent } from './components/cart/cart.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { ClientComponent } from './components/client/client.component';
import { LoginComponent } from './components/login/login.component';
import { AboutComponent } from './components/about/about.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { ForgetPassComponent } from './components/forget-pass/forget-pass.component';
import { FooterComponent } from './components/footer/footer.component';

import { TokenInterceptor } from './interceptor/token/token.interceptor';
import { SearchComponent } from './components/search/search.component';
import { UppercaseInputDirective } from './directives/uppercase-input.directive';
import { VerifyEmailComponent } from './components/verify-email/verify-email.component';



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    ProductListComponent,
    ProductDetailComponent,
    CartComponent,
    CheckoutComponent,
    HomepageComponent,
    SignUpComponent,
    ClientComponent,
    LoginComponent,
    AboutComponent,
    NotFoundComponent,
    ForgetPassComponent,
    SearchComponent,
    VerifyEmailComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    AppRoutingModule,
    RouterLink,
    CommonModule,
    FormsModule,
    NgbPagination,
    ReactiveFormsModule,
    ToastrModule.forRoot(),
    UppercaseInputDirective
  ],
  providers: [
    provideHttpClient(withFetch()),
    provideHttpClient(
      withFetch(),
      withInterceptors([
        (req, next) => {
          if (req.url.includes('/products')) {
            return next(req.clone({
              headers: req.headers.set('X-Total-Count', '*')
            }));
          }
          return next(req);
        }
      ])
    ),
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
