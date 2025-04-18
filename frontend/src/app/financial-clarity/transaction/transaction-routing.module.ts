import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { TransactionComponent } from "./pages/transaction/transaction.component";
import { AuthGuard } from "src/app/core/guards/auth.guard";

const routes: Routes = [
  { path: "", component: TransactionComponent, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class TransactionRoutingModule {}
