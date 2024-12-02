import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';
import { ToastrService } from 'ngx-toastr';
import { User } from '../../interfaces/user.interface';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm = {
    username: '',
    password: ''
  };

  showPassword = false;
  errorMessage = '';

  constructor(
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService
  ) { }

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

  onSubmit(): void {
    const user = new User();
    user.username = this.loginForm.username;
    user.password = this.loginForm.password;
    user.enabled = true;
    user.email = '';
    user.roles = [];

    this.authService.login(user).subscribe({
      next: (response) => {
        const jwt = response.headers.get('Authorization');
        if (jwt) {
          this.authService.saveToken(jwt);
          this.toastr.success('Login successful!');
          this.router.navigate(['/']);
        }
      },
      error: (err) => {
        this.errorMessage = 'Invalid username or password';
        this.toastr.error(this.errorMessage);
      }
    });
  }

  goToSignUp(): void {
    window.scrollTo(0, 0);
    this.router.navigate(['/sign-up']);
  }

  loginWithGoogle(): void {
    this.authService.loginWithGoogle().subscribe({
      next: (response) => {
        if (response && response.headers) {
          const jwt = response.headers.get('Authorization');
          if (jwt) {
            this.authService.saveToken(jwt);
            this.toastr.success('Google login successful!');
            this.router.navigate(['/']);
          }
        }
      },
      error: (err) => {
        console.error('Google login failed:', err);
        this.toastr.error('Google login failed');
      }
    });
  }

  goToForgetPass(): void {
    window.scrollTo(0, 0);
    this.router.navigate(['/forget-pass']);
  }
}
