import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FooterComponent } from './layout/footer/footer.component';
import { NavbarComponent } from './layout/navbar/navbar.component';
import { MaterialModule } from '../material/material.module';
import { devNavbarComponent } from './layout/devNavbar/devNavbar.component';


@NgModule({
  declarations: [
    devNavbarComponent,
    NavbarComponent,
    FooterComponent
  ],
  imports: [
    MaterialModule,
    CommonModule
  ],
  exports: [devNavbarComponent, NavbarComponent, FooterComponent]
})
export class SharedModule { }
