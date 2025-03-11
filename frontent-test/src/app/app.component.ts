import { Component } from '@angular/core';
import { NavigationEnd, NavigationStart, Router } from '@angular/router';
import { filter } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'superheroes';
  // isDisabled = true;
  // model = 'seiji';


  // Below checks to see if we should render the nav, if we are on a login screen it should not render
  showNavbar: boolean = true;

  constructor(private router: Router) {}

  ngOnInit() {
    // Subscribe to router events to handle route changes
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationStart) {
        // Hide navbar on specific routes
        const excludedRoutes = ['/', '/login', '/login/register'];
        this.showNavbar = !excludedRoutes.includes(event.url);
      }
    });
  }

}

