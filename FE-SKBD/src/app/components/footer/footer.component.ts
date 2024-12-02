import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {
  constructor(private router: Router) {}

  scrollToFAQ() {
    this.router.navigate(['/about']).then(() => {
      setTimeout(() => {
        const element = document.getElementById('faq-section');
        if (element) {
          element.scrollIntoView({ behavior: 'smooth' });
        }
      }, 100);
    });
  }

  noLink() {
    this.router.navigate(['/404-not-found'])
    .then(() => window.scrollTo(0, 0));
  }
}
