import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { TransactionRoutingModule } from "./transaction-routing.module";
import { TransactionComponent } from "./pages/transaction/transaction.component";
import { CalendarComponent } from "./components/calendar/calendar.component";
import { MaterialModule } from "src/app/material/material.module";
import { CategoryCardComponent } from "./components/category-card/category-card.component";
import { CategoryListComponent } from "./components/category-list/category-list.component";
import { AddTransactionModule } from "../add-transaction/add-transaction.module";

@NgModule({
  declarations: [
    TransactionComponent,
    CalendarComponent,
    CategoryCardComponent,
    CategoryListComponent,
  ],
  imports: [
    CommonModule,
    TransactionRoutingModule,
    MaterialModule,
    AddTransactionModule,
  ],
})
export class TransactionModule {}
