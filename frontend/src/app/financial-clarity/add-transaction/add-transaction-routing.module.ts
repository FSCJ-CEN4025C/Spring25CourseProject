import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { TestComponent } from "./pages/test/test.component";
import { AuthGuard } from "src/app/core/guards/auth.guard";

const routes: Routes = [
  { path: "", component: TestComponent, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AddTransactionRoutingModule {}
