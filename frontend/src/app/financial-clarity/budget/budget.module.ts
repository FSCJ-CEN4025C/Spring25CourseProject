import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { BudgetRoutingModule } from "./budget-routing.module";
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { MaterialModule } from "../../material/material.module";

import { BudgetCommandBarComponent } from './components/budget-command-bar/budget-command-bar.component';
import { BudgetFormComponent } from './components/budget-form/budget-form.component';
import { BudgetListComponent } from './components/budget-list/budget-list.component';
import { FormComponent } from './pages/form/form.component';
import { ListComponent } from './pages/list/list.component';

import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { budgetReducer } from './state/budget.reducers';
import { BudgetEffects } from './state/budget.effects';
import { ConfirmationDialogComponent } from "../../shared/components/confirmation-dialog.component";
import {AddTransactionModule} from "../add-transaction/add-transaction.module";

@NgModule({
    declarations: [
        BudgetCommandBarComponent,
        BudgetFormComponent,
        BudgetListComponent,

        FormComponent,
        ListComponent,
        ConfirmationDialogComponent
    ],
    imports: [
        CommonModule,
        RouterModule,
        BudgetRoutingModule,
        ReactiveFormsModule,
        MaterialModule,
        StoreModule.forFeature('budgetState', budgetReducer),
        EffectsModule.forFeature([BudgetEffects]),
        FormsModule,
        AddTransactionModule
    ],
    exports: [
        BudgetCommandBarComponent,
        BudgetFormComponent,
        BudgetListComponent,
        ConfirmationDialogComponent
    ]
})
export class BudgetModule { }
