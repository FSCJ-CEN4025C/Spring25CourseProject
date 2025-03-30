import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { AppState } from 'src/app/state/app.state';
import { Budget } from '../../models/budget.interface';
import { BudgetActions } from '../../state/budget.actions';
import { selectBudgetById } from '../../state/budget.selectors';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit {
  budget$: Observable<Budget | null | undefined>;
  budget: Budget | null = null;
  constructor(private router: ActivatedRoute, private store: Store<AppState>) {
    const id = this.router.snapshot.params['id'];
    this.budget$ = this.store.select(selectBudgetById(id));
    this.budget$.subscribe(d => {
      if(d) this.budget = d;
    });

  }

  ngOnInit(): void {

  }

  formAction(data: { value: Omit<Budget, 'id'>, action: string }) {
    switch (data.action) {
      case "Create": {
        this.store.dispatch({
          type: BudgetActions.ADD_BUDGET_API,
          payload: data.value
        });
        return;
      }
      case "Update": {
        this.store.dispatch({
          type: BudgetActions.MODIFY_BUDGET_API,
          payload: data.value
        });
        return;
      }
      default: ""
    }
  }


}
