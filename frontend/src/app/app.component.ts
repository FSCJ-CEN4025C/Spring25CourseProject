import { Component, OnInit } from "@angular/core";
import { NavigationStart, Router, Event } from "@angular/router";
import { filter } from "rxjs";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
})
export class AppComponent implements OnInit {
  title = "financial-clarity";
  showNavbar = true;

  constructor(private router: Router) {}

  ngOnInit() {
    this.router.events
      .pipe(
        filter(
          (event: Event): event is NavigationStart =>
            event instanceof NavigationStart
        )
      )
      .subscribe((event: NavigationStart) => {
        const excludedRoutes = ["/login", "/login/register"];
        this.showNavbar = !excludedRoutes.includes(event.url);
      });
  }
}
