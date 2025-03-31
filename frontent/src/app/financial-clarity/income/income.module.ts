import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { IncomeRoutingModule } from './income-routing.module';
import { AddIncomeComponent } from './pages/add-income/add-income.component';
import { AuthModule } from 'src/app/auth/auth.module';
import { AddTransactionModelComponent } from './add-transaction-model/add-transaction-model.component';
import { MaterialModule } from 'src/app/material/material.module';



@NgModule({
  declarations: [
    AddIncomeComponent,
    AddTransactionModelComponent
  ],
  imports: [
    CommonModule,
    IncomeRoutingModule,
        MaterialModule,

  ]
})
export class IncomeModule { }
