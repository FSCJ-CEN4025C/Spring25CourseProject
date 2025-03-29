import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { AppState } from 'src/app/state/app.state';
import { CommandBarActions } from '../../enums/command-bar-actions.enum';
import { TableActions } from '../../enums/table-actions.enum';
import { Budget } from '../../models/budget.interface';
import { BudgetActions } from '../../state/budget.actions';
import { selectBudgets } from '../../state/budget.selectors';


@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {
  // sample data of budget
  budgets: ReadonlyArray<Budget> = [];
  budgets$ = this.store.select(selectBudgets);

  headers: {headerName: string, fieldName: keyof Budget}[] = [
    {headerName: "Category ID", fieldName: "userId"},
    {headerName: "Amount", fieldName: "amount"},
    {headerName: "Period", fieldName: "period"},
    {headerName: "Start Date", fieldName: "startDate"},
    {headerName: "End Date", fieldName: "endDate"},
  ]

  constructor(
      private router: Router,
      private store: Store<AppState>,
  ) { }

  ngOnInit(): void {
    this.store.dispatch({type: BudgetActions.GET_BUDGET_LIST});
    this.assignBudget();
  }

  assignBudget() {
    this.budgets$.subscribe((data) => {
      this.budgets = data;
    });
  }

  selectBudget(data: {budget: Budget, action: TableActions}) {
    switch(data.action) {
      case TableActions.View: {
        this.router.navigate(['budgets', 'form', data.budget.id]);
        return;
      }
      case TableActions.Delete: {
        this.store.dispatch({type: BudgetActions.REMOVE_BUDGET_API, payload: data.budget.id});
        return;

      }
      default: ""
    }
  }

  executeCommandBarAction(action: CommandBarActions) {
    switch(action) {
      case CommandBarActions.Create: {
        this.router.navigate(["budgets", "form"]);
        return;
      }
      case CommandBarActions.DeleteAll: {
        this.store.dispatch({type: BudgetActions.REMOVE_ALL_BUDGET_API, payload: [...this.budgets.map(d => d.id)]})
        return;

      }
      default: ""

    }
  }

}
