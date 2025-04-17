import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { TestComponent } from "./pages/test/test.component";

import { MaterialModule } from "src/app/material/material.module";
import { ReactiveFormsModule } from "@angular/forms";
import { AddTransactionRoutingModule } from "./add-transaction-routing.module";
import { AddTransactionModalComponent } from "./components/add-transaction-modal/add-transaction-modal.component";
import { ListTransactionsModalComponent } from "./components/list-transactions-modal/list-transactions-modal.component";

@NgModule({
  declarations: [
    TestComponent,
    AddTransactionModalComponent,
    ListTransactionsModalComponent,
  ],
  imports: [
    CommonModule,
    AddTransactionRoutingModule,
    MaterialModule,
    ReactiveFormsModule,
  ],
  exports: [AddTransactionModalComponent, ListTransactionsModalComponent],
})
export class AddTransactionModule {}
