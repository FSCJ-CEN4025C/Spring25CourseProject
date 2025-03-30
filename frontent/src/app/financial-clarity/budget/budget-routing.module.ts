import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BudgetListComponent } from './components/budget-list/budget-list.component';
import { BudgetFormComponent } from './components/budget-form/budget-form.component';
import { AuthGuard } from 'src/app/core/guards/auth.guard';

const routes: Routes = [
    { path: '', component: BudgetListComponent, canActivate: [AuthGuard] }, // Default to list view
    { path: 'create', component: BudgetFormComponent, canActivate: [AuthGuard] }, // Form for new budget
    { path: 'edit/:id', component: BudgetFormComponent, canActivate: [AuthGuard] }, // Edit existing budget
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class BudgetRoutingModule { }
