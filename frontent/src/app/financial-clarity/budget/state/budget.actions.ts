import { createAction, props } from '@ngrx/store';
import { Budget, NewBudget } from '../models/budget.interface';

export enum BudgetActions {
  GET_BUDGET_LIST = '[Budget] Get Budget list',
  SET_BUDGET_LIST = '[Budget] Set Budget list',
  ADD_BUDGET_API = '[Budget] Add Budget (API',
  ADD_BUDGET_STATE = '[Budget] Add Budget (STATE)',
  MODIFY_BUDGET_API = '[Budget] Modify Budget (API)',
  MODIFY_BUDGET_STATE = '[Budget] Modify Budget (STATE)',
  REMOVE_BUDGET_API = '[Budget] Remove Budget (API)',
  REMOVE_BUDGET_STATE = '[Budget] Remove Budget (STATE)',
  REMOVE_ALL_BUDGET_API = '[Budget] Remove All Budget (API)',
  REMOVE_ALL_BUDGET_STATE = '[Budget] Remove ALL Budget (STATE)',
}

export const getBudgetList = createAction(
  BudgetActions.GET_BUDGET_LIST,
);

export const setBudgetList = createAction(
BudgetActions.SET_BUDGET_LIST,
props<{ budgets: ReadonlyArray<Budget> }>(),
);

 
export const addBudgetState = createAction(
  BudgetActions.ADD_BUDGET_STATE,
  props<{ budget: Budget }>()
);

export const modifyBudgetState = createAction(
    BudgetActions.MODIFY_BUDGET_STATE,
    props<{ budget: Budget }>()
);
 
export const removeBudgetState = createAction(
    BudgetActions.REMOVE_BUDGET_STATE,
  props<{ budgetId: string }>()
);

export const removeAllBudgetState = createAction(
  BudgetActions.REMOVE_ALL_BUDGET_STATE
);

export const deleteBudget = createAction(
    '[Budget] Delete Budget',
    props<{ id: number }>()
);

export const setSelectedBudget = createAction(
    '[Budget] Set Selected Budget',
    props<{ id: number | null }>()
);
