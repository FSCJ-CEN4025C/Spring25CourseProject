import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { TableActions } from '../../enums/table-actions.enum';
import { Budget } from '../../models/budget.interface';
import { Store } from '@ngrx/store';
import {removeBudgetState} from '../../state/budget.actions';
import { AppState } from 'src/app/state/app.state';
import { ConfirmationDialogComponent } from "../../../../shared/components/confirmation-dialog.component";
import { SnackbarService } from "../../../../core/services/snackbar.service";

@Component({
  selector: 'app-budget-list',
  templateUrl: './budget-list.component.html',
  styleUrls: ['./budget-list.component.scss']
})
export class BudgetListComponent implements OnInit {
  @Input() headers: Array<{headerName: string, fieldName: keyof Budget}> = [];
  @Input() budgets: ReadonlyArray<Budget> = [];
  @Output() budgetSelected = new EventEmitter<{ budget: Budget, action: TableActions }>();

  headerFields: string[] = [];
  TableActions = TableActions;

  constructor(private store: Store<AppState>,
              private dialog: MatDialog,
              private router: Router,
              private snackbarService: SnackbarService) { }

  ngOnInit(): void {
    this.getHeaderFields();
  }

  getHeaderFields() {
    this.headerFields = this.headers.map((data) => data.fieldName);
    this.headerFields.push("actions");
  }

  selectBudget(budget: Budget, action: TableActions) {
    this.budgetSelected.emit({ budget, action });

    if (action === TableActions.Edit) {
      this.router.navigate(['/budgets/edit', budget.id]);
    }
  }


  confirmDelete(budget: Budget): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '350px',
      data: { message: 'Are you sure you want to delete this budget?' }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.store.dispatch(removeBudgetState({ budgetId: budget.id }));
        this.snackbarService.show('Budget deleted successfully!', 'OK', 3000);
      }
    });
  }

}
