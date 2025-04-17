import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AuthRoutingModule} from "../../auth/auth-routing.module";

@Component({
  selector: 'app-home',
    imports: [CommonModule, AuthRoutingModule],
  standalone: true,
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  backgroundImg = 'assets/img/bkgrndimg.png';
}
