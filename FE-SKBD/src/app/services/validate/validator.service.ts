import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ValidatorService {
  
  validateUsername(username: string): { isValid: boolean; message: string } {
    if (!username) {
      return { isValid: false, message: 'Username is required' };
    }
    if (username.length < 6) {
      return { isValid: false, message: 'Username must be at least 6 characters' };
    }
    if (!/^[a-zA-Z0-9_]+$/.test(username)) {
      return { isValid: false, message: 'Username can only contain letters, numbers and underscore' };
    }
    return { isValid: true, message: '' };
  }

  validateEmail(email: string): { isValid: boolean; message: string } {
    if (!email) {
      return { isValid: false, message: 'Email is required' };
    }
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      return { isValid: false, message: 'Invalid email format' };
    }
    return { isValid: true, message: '' };
  }

  validatePhone(phone: string): { isValid: boolean; message: string } {
    if (!phone) {
      return { isValid: false, message: 'Phone number is required' };
    }
    const phoneRegex = /^[0-9]{10}$/;
    if (!phoneRegex.test(phone)) {
      return { isValid: false, message: 'Phone number must be 10 digits' };
    }
    return { isValid: true, message: '' };
  }

  validatePassword(password: string): { isValid: boolean; message: string } {
    if (!password) {
      return { isValid: false, message: 'Password is required' };
    }
    if (password.length < 8) {
      return { isValid: false, message: 'Password must be at least 8 characters' };
    }
    if (!/[A-Z]/.test(password)) {
      return { isValid: false, message: 'Password must contain at least one uppercase letter' };
    }
    if (!/[a-z]/.test(password)) {
      return { isValid: false, message: 'Password must contain at least one lowercase letter' };
    }
    if (!/[0-9]/.test(password)) {
      return { isValid: false, message: 'Password must contain at least one number' };
    }
    if (!/[!@#$%^&*]/.test(password)) {
      return { isValid: false, message: 'Password must contain at least one special character (!@#$%^&*)' };
    }
    return { isValid: true, message: '' };
  }

  validateConfirmPassword(password: string, confirmPassword: string): { isValid: boolean; message: string } {
    if (!confirmPassword) {
      return { isValid: false, message: 'Please confirm your password' };
    }
    if (password !== confirmPassword) {
      return { isValid: false, message: 'Passwords do not match' };
    }
    return { isValid: true, message: '' };
  }
} 