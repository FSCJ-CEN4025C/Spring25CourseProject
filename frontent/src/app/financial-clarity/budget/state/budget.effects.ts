import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { EMPTY, forkJoin } from 'rxjs';
import { map, mergeMap, catchError, tap } from 'rxjs/operators';
import { Budget } from '../models/budget.interface';
import { BudgetService } from '../services/budget.service';
import { BudgetActions } from './budget.actions';


@Injectable()
export class BudgetEffects {

  getBudgets$ = createEffect(() => {
    return this.actions$.pipe(
        ofType(BudgetActions.GET_BUDGET_LIST),
        mergeMap(() => this.budgetService.getBudgets()
          .pipe(
            map(budgets => ({ type: BudgetActions.SET_BUDGET_LIST, budgets })),
            catchError(() => EMPTY)
          ))
        )
    }, {dispatch: true}
  );

  // add budgets in the database
    addBudget$ = createEffect(() => {
        return this.actions$.pipe(
            ofType(BudgetActions.ADD_BUDGET_API),
            mergeMap((data: { type: string; payload: Omit<Budget, 'id'> }) =>
                this.budgetService.createBudget(data.payload).pipe(
                    map((createdBudget) => ({
                        type: BudgetActions.ADD_BUDGET_STATE,
                        budget: createdBudget,
                    })),
                    tap(() => this.router.navigate(["budgets"])),
                    catchError(() => EMPTY)
                )
            )
        );
    }, { dispatch: true });


   modifyBudget$ = createEffect(() =>{
    return this.actions$.pipe(
        ofType(BudgetActions.MODIFY_BUDGET_API),
        mergeMap((data: {type: string, payload: Budget}) => this.budgetService.updateBudget(data.payload.id, data.payload)
          .pipe(
            map(budgets => ({ type: BudgetActions.MODIFY_BUDGET_STATE, budget: data.payload })),
            tap(() =>  this.router.navigate(["budgets"])),
            catchError(() => EMPTY)
          ))
        )
    }, {dispatch: true})

  // remove budgets in the database
  removeBudget$ = createEffect(() => {
    return this.actions$.pipe(
        ofType(BudgetActions.REMOVE_BUDGET_API),
        mergeMap((data: { payload: string}) => this.budgetService.deleteBudget(data.payload)
          .pipe(
            map(() => ({ type: BudgetActions.REMOVE_BUDGET_STATE, budgetId: data.payload })),
            catchError(() => EMPTY)
          ))
        )
    }, {dispatch: true}
  );
  // remove all budgets in the database
  removeAllBudget$ = createEffect(() => {
    return this.actions$.pipe(
        ofType(BudgetActions.REMOVE_ALL_BUDGET_API),
        mergeMap((data: {type: string, payload: string[]}) =>
        forkJoin([...data.payload.map((id) => this.budgetService.deleteBudget(id))])
          .pipe(
            map(() => ({ type: BudgetActions.REMOVE_ALL_BUDGET_STATE })),
            catchError(() => EMPTY)
          ))
        )
    }, {dispatch: true}
  );

  constructor(
    private actions$: Actions,
    private budgetService: BudgetService,
    private router: Router
  ) {}
}