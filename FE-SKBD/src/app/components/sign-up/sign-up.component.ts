import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ValidatorService } from '../../services/validate/validator.service';
import { AuthService } from '../../services/auth/auth.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent {
  signUpForm = {
    username: '',
    password: '',
    confirmPassword: '',
    email: '',
    phone: '',
    firstName: '',
    lastName: '',
    address: ''
  };

  validationErrors = {
    username: '',
    password: '',
    confirmPassword: '',
    email: '',
    phone: ''
  };

  showPassword = false;
  showConfirmPassword = false;

  constructor(
    private router: Router,
    private validator: ValidatorService,
    private authService: AuthService,
    private toastr: ToastrService
  ) { }

  validateField(field: string, value: string) {
    switch (field) {
      case 'username':
        const usernameResult = this.validator.validateUsername(value);
        this.validationErrors.username = usernameResult.message;
        break;
      case 'email':
        const emailResult = this.validator.validateEmail(value);
        this.validationErrors.email = emailResult.message;
        break;
      case 'phone':
        const phoneResult = this.validator.validatePhone(value);
        this.validationErrors.phone = phoneResult.message;
        break;
      case 'password':
        const passwordResult = this.validator.validatePassword(value);
        this.validationErrors.password = passwordResult.message;
        if (this.signUpForm.confirmPassword) {
          this.validateField('confirmPassword', this.signUpForm.confirmPassword);
        }
        break;
      case 'confirmPassword':
        const confirmResult = this.validator.validateConfirmPassword(
          this.signUpForm.password,
          value
        );
        this.validationErrors.confirmPassword = confirmResult.message;
        break;
    }
  }

  isFormValid(): boolean {
    return (
      !this.validationErrors.username &&
      !this.validationErrors.email &&
      !this.validationErrors.phone &&
      !this.validationErrors.password &&
      !this.validationErrors.confirmPassword &&
      this.signUpForm.firstName.trim() !== '' &&
      this.signUpForm.lastName.trim() !== '' &&
      this.signUpForm.address.trim() !== ''
    );
  }


  onSubmit() {
    if (this.isFormValid()) {
      const user = {
        ...this.signUpForm,
        enabled: false,
        roles: []
      };

      this.authService.register(user).subscribe({
        next: (response) => {
          this.authService.setRegisteredUser(user);
          this.toastr.success('Registration successful! Please verify your email.');
          this.router.navigate(['/verify-email']);
        },
        error: (error) => {
          this.toastr.error(error.error.message || 'Registration failed');
        }
      });
    }
  }

  goToLogin(): void {
    window.scrollTo(0, 0);
    this.router.navigate(['/login']);
  }

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

  toggleConfirmPasswordVisibility(): void {
    this.showConfirmPassword = !this.showConfirmPassword;
  }
}
