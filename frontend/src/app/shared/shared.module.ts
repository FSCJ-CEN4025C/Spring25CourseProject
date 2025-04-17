import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FooterComponent } from './layout/footer/footer.component';
import { NavbarComponent } from './layout/navbar/navbar.component';
import { MaterialModule } from '../material/material.module';
import { devNavbarComponent } from './layout/devNavbar/devNavbar.component';
import { CategoryComponent } from "../financial-clarity/category/components/category.component";
import {AddTransactionModule} from "../financial-clarity/add-transaction/add-transaction.module";
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    devNavbarComponent,
    NavbarComponent,
    FooterComponent,
    CategoryComponent
  ],
    imports: [
        MaterialModule,
        CommonModule,
        AddTransactionModule,
        ReactiveFormsModule
    ],
  exports: [devNavbarComponent, NavbarComponent, FooterComponent, CategoryComponent]
})
export class SharedModule { }
