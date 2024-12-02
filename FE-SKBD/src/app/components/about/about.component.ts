import { Component, OnInit } from '@angular/core';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css'],
  animations: [
    trigger('slideInOut', [
      state('true', style({
        maxHeight: '500px',
        opacity: 1,
        padding: '1.5rem'
      })),
      state('false', style({
        maxHeight: '0',
        opacity: 0,
        padding: '0 1.5rem'
      })),
      transition('false <=> true', animate('300ms ease-in-out'))
    ])
  ]
})
export class AboutComponent implements OnInit {
  activeFaqIndex: number | null = null;

  constructor(private route: ActivatedRoute) {}

  ngOnInit() {
    this.route.fragment.subscribe(fragment => {
      if (fragment === 'faq') {
        setTimeout(() => {
          const element = document.getElementById('faq-section');
          if (element) {
            element.scrollIntoView({ behavior: 'smooth' });
          }
        }, 100);
      }
    });
  }

  toggleFAQ(index: number) {
    this.activeFaqIndex = this.activeFaqIndex === index ? null : index;
  }
}
