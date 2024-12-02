import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';
import { ToastrService } from 'ngx-toastr';
import { User } from '../../interfaces/user.interface';
import { interval, Subscription } from 'rxjs';

@Component({
  selector: 'app-verify-email',
  templateUrl: './verify-email.component.html',
  styleUrls: ['./verify-email.component.css']
})
export class VerifyEmailComponent implements OnInit, OnDestroy {
  verifyForm!: FormGroup;
  user: User | null = null;
  err: string = "";
  isSubmitting = false;
  resendTimer = 0;
  private timerSubscription?: Subscription;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.verifyForm = this.formBuilder.group({
      code: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(6)]]
    });

    this.user = this.authService.getRegisteredUser();
    if (!this.user) {
      this.router.navigate(['/sign-up']);
      return;
    }

    this.startResendTimer();
  }

  ngOnDestroy(): void {
    if (this.timerSubscription) {
      this.timerSubscription.unsubscribe();
    }
  }

  onValidateEmail(): void {
    if (this.verifyForm.invalid || this.isSubmitting) {
      return;
    }

    this.isSubmitting = true;
    this.err = "";
    const code = this.verifyForm.get('code')?.value;

    this.authService.validateEmail(code).subscribe({
      next: () => {
        this.toastr.success('Email verified successfully!');
        
        if (this.user) {
          this.authService.login(this.user).subscribe({
            next: (response) => {
              const token = response.headers.get('Authorization');
              if (token) {
                this.authService.saveToken(token);
                this.router.navigate(['/']);
              }
            },
            error: (error) => {
              this.err = "Login failed after verification. Please try logging in manually.";
              this.router.navigate(['/login']);
            }
          });
        }
      },
      error: (error) => {
        this.isSubmitting = false;
        if (error.error?.errorCode === "INVALID_TOKEN") {
          this.err = "Invalid verification code. Please try again.";
        } else if (error.error?.errorCode === "EXPIRED_TOKEN") {
          this.err = "Verification code has expired. Please request a new one.";
        } else {
          this.err = "An error occurred. Please try again.";
        }
      }
    });
  }

  resendVerificationCode(): void {
    if (this.resendTimer > 0 || !this.user?.email) return;

    this.authService.resendVerificationCode(this.user.email).subscribe({
      next: () => {
        this.toastr.success('New verification code sent!');
        this.startResendTimer();
      },
      error: () => {
        this.toastr.error('Failed to send new verification code');
      }
    });
  }

  private startResendTimer(): void {
    this.resendTimer = 60;
    this.timerSubscription = interval(1000).subscribe(() => {
      if (this.resendTimer > 0) {
        this.resendTimer--;
      } else {
        this.timerSubscription?.unsubscribe();
      }
    });
  }
}
