import { createReducer, on } from '@ngrx/store';
import { Budget } from '../models/budget.interface';
import { addBudgetState,
         modifyBudgetState,
         removeAllBudgetState,
         removeBudgetState,
         setBudgetList,
         deleteBudget,
         setSelectedBudget
} from './budget.actions';

export interface BudgetState {
    budgets: ReadonlyArray<Budget>;
    selectedBudgetId: number | null;
}

export const initialState: BudgetState = {
    budgets: [],
    selectedBudgetId: null
}

export const budgetReducer = createReducer(
    initialState,
    on(setBudgetList, (state, { budgets }) => {
        return { ...state, budgets };
    }),
    on(removeBudgetState, (state, { budgetId }) => {
        const budgetIdAsNumber = Number(budgetId);
        if (isNaN(budgetIdAsNumber)) {
            return state;
        }
        return { ...state,
            budgets: state.budgets.filter(data => data.id !== budgetIdAsNumber)
        };
    }),
    on(addBudgetState, (state, { budget }) => {
        return { ...state, budgets: [...state.budgets, budget] };
    }),
    on(modifyBudgetState, (state, { budget }) => {
        return { ...state,
            budgets: state.budgets.map(data => data.id === budget.id ? budget : data)
        };
    }),
    on(removeAllBudgetState, (state) => {
        return { ...state, budgets: [] };
    }),
    on(deleteBudget, (state, { id }) => {
        return {
            ...state,budgets: state.budgets.filter(budget => budget.id !== id)
        };
    }),
    on(setSelectedBudget, (state, { id }) => ({
        ...state, selectedBudgetId: id
    }))
);

