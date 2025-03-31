import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { selectUser } from 'src/app/auth/state/auth.selectors';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  user$ = this.store.select(selectUser());

  constructor(private store: Store,) { }

  ngOnInit(): void {
  }

}
