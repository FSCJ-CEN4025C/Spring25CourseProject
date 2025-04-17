import { createSelector, createFeatureSelector } from '@ngrx/store';
import { BudgetState } from './budget.reducers';

export const selectBudgetState = createFeatureSelector<BudgetState>('budgetState');

export const selectBudgets = createSelector(
    selectBudgetState,
    (state: BudgetState) => state.budgets
);

export const selectBudgetById = (id: number) =>
    createSelector(selectBudgets, (budgets) =>
        budgets.find((budget) => budget.id === id) || null
    );


export const selectSelectedBudgetId = createSelector(
    selectBudgetState,
    (state: BudgetState) => state.selectedBudgetId
);

export const selectCurrentBudget = createSelector(
    selectBudgetState,
    selectSelectedBudgetId,
    (state, selectedBudgetId) => {
        if (selectedBudgetId === null) return null;
        return state.budgets.find(budget => budget.id === selectedBudgetId);
    }
);
