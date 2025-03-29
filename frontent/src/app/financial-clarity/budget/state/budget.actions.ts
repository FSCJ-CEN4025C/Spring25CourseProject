import { createAction, props } from '@ngrx/store';
import { Budget, NewBudget } from '../models/budget.interface';

export enum BudgetActions {
  GET_BUDGET_LIST = '[Budget] Get Budget List',
  SET_BUDGET_LIST = '[Budget] Set Budget List',
  SET_SELECTED_BUDGET = '[Budget] Set Selected Budget',
  ADD_BUDGET_API = '[Budget] Add Budget (API)',
  ADD_BUDGET_SUCCESS = '[Budget] Add Budget Success',
  ADD_BUDGET_STATE = '[Budget] Add Budget (State)',
  MODIFY_BUDGET_API = '[Budget] Modify Budget (API)',
  MODIFY_BUDGET_STATE = '[Budget] Modify Budget (State)',
  REMOVE_BUDGET_API = '[Budget] Remove Budget (API)',
  REMOVE_BUDGET_STATE = '[Budget] Remove Budget (State)',
  REMOVE_ALL_BUDGET_API = '[Budget] Remove All Budget (API)',
  REMOVE_ALL_BUDGET_STATE = '[Budget] Remove All Budget (State)',
}

// Action Creators (functions)
export const getBudgetList = createAction(
    BudgetActions.GET_BUDGET_LIST
);

export const setBudgetList = createAction(
    BudgetActions.SET_BUDGET_LIST,
    props<{ budgets: ReadonlyArray<Budget> }>()
);

export const setSelectedBudget = createAction(
    BudgetActions.SET_SELECTED_BUDGET,  // You can define this in the enum if it doesn't exist
    props<{ id: number }>()
);


export const addBudgetApi = createAction(
    BudgetActions.ADD_BUDGET_API,
    props<{ newBudget: NewBudget }>()
);

export const addBudgetSuccess = createAction(
    BudgetActions.ADD_BUDGET_SUCCESS,
    props<{ budget: Budget }>()
);

export const addBudgetState = createAction(
    BudgetActions.ADD_BUDGET_STATE,
    props<{ budget: NewBudget }>()
);

export const modifyBudgetApi = createAction(
    BudgetActions.MODIFY_BUDGET_API,
    props<{ budget: Budget }>()
);

export const modifyBudgetState = createAction(
    BudgetActions.MODIFY_BUDGET_STATE,
    props<{ budget: Budget }>()
);

export const removeBudgetApi = createAction(
    BudgetActions.REMOVE_BUDGET_API,
    props<{ budgetId: string }>()
);

export const removeBudgetState = createAction(
    BudgetActions.REMOVE_BUDGET_STATE,
    props<{ budgetId: number }>()
);

export const removeAllBudgetApi = createAction(
    BudgetActions.REMOVE_ALL_BUDGET_API,
    props<{ budgetIds: string[] }>()
);

export const removeAllBudgetState = createAction(
    BudgetActions.REMOVE_ALL_BUDGET_STATE
);
