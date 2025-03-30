import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
// import { MatButtonModule } from '@angular/material/button';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './home/login/login.component';
import { CreateAccountComponent} from './home/createaccount/createaccount.component';

@Component({
  selector: 'app-root',
  imports: [HomeComponent, LoginComponent, CreateAccountComponent, RouterOutlet, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'FinancialClarity';
}
