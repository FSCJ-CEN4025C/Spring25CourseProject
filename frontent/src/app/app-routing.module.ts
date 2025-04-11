import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AuthGuard } from "./core/guards/auth.guard";
import { HomeComponent } from "./financial-clarity/home/home.component";

const routes: Routes = [
  {
    path: "",
    component: HomeComponent,
  },
  {
    path: "login",
    loadChildren: () => import("./auth/auth.module").then((m) => m.AuthModule),
  },

  // Example used in module for, keeping it for now, to reference
  {
    path: "anti-heroes",
    loadChildren: () =>
      import("./anti-hero/anti-hero.module").then((m) => m.AntiHeroModule),
    canActivate: [AuthGuard],
  },
  {
    path: "budgets",
    loadChildren: () =>
      import("./financial-clarity/budget/budget.module").then(
        (m) => m.BudgetModule
      ),
    canActivate: [AuthGuard],
  },

  {
    path: "categories",
    loadChildren: () =>
        import("./financial-clarity/category/category.module").then(
            (m) => m.CategoryModule
        ),
    canActivate: [AuthGuard],
  },

  {
    path: "income",
    loadChildren: () =>
      import("./financial-clarity/add-transaction/add-transaction.module").then(
        (m) => m.AddTransactionModule
      ),
    canActivate: [AuthGuard],
  },

  {
    path: "**", // Wildcard route that catches any unrecognized path
    redirectTo: "", // Redirect to home if route is not recognized or the user is not authenticated
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
