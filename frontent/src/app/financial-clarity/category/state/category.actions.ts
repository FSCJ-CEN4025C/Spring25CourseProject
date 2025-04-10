import { createAction, props } from '@ngrx/store';
import { Category } from '../models/category.interface';

export enum CategoryActions {
    GET_CATEGORY_LIST = '[Category] Get Category List',
    SET_CATEGORY_LIST = '[Category] Set Category List',
    SET_SELECTED_CATEGORY = '[Category] Set Selected Category',
    ADD_CATEGORY_API = '[Category] Add Category (API)',
    ADD_CATEGORY_SUCCESS = '[Category] Add Category Success',
    ADD_CATEGORY_STATE = '[Category] Add Category (State)',
    MODIFY_CATEGORY_API = '[Category] Modify Category (API)',
    MODIFY_CATEGORY_STATE = '[Category] Modify Category (State)',
    REMOVE_CATEGORY_API = '[Category] Remove Category (API)',
    REMOVE_CATEGORY_STATE = '[Category] Remove Category (State)',
    REMOVE_ALL_CATEGORY_API = '[Category] Remove All Categories (API)',
    REMOVE_ALL_CATEGORY_STATE = '[Category] Remove All Categories (State)',
    GET_BUDGETS_BY_CATEGORY = '[Category] Get Budgets by Category',
    SET_CATEGORY_BUDGETS = '[Category] Set Category Budgets',
    SET_TOTALS = '[Category] Set Totals',
    SET_ERROR = '[Category] Set Error',
}


export const getCategoryList = createAction(
    CategoryActions.GET_CATEGORY_LIST
);

export const setCategoryList = createAction(
    CategoryActions.SET_CATEGORY_LIST,
    props<{ categories: ReadonlyArray<Category> }>()
);

export const setSelectedCategory = createAction(
    CategoryActions.SET_SELECTED_CATEGORY,
    props<{ id: number }>()
);

export const getBudgetsByCategory = createAction(
    CategoryActions.GET_BUDGETS_BY_CATEGORY,
    props<{ categoryId: number }>()
);

export const setCategoryBudgets = createAction(
    CategoryActions.SET_CATEGORY_BUDGETS,
    props<{ categoryId: number, budgets: any[] }>()
);

export const setTotals = createAction(
    CategoryActions.SET_TOTALS,
    props<{ totalIncome: number, totalExpenses: number }>()
);

export const setError = createAction(
    CategoryActions.SET_ERROR,
    props<{ errorMessage: string }>()
);

export const addCategoryApi = createAction(
    CategoryActions.ADD_CATEGORY_API,
    props<{ newCategory: Category }>()
);

export const addCategorySuccess = createAction(
    CategoryActions.ADD_CATEGORY_SUCCESS,
    props<{ category: Category }>()
);

export const addCategoryState = createAction(
    CategoryActions.ADD_CATEGORY_STATE,
    props<{ category: Category }>()
);

export const modifyCategoryApi = createAction(
    CategoryActions.MODIFY_CATEGORY_API,
    props<{ category: Category }>()
);

export const modifyCategoryState = createAction(
    CategoryActions.MODIFY_CATEGORY_STATE,
    props<{ category: Category }>()
);

export const removeCategoryApi = createAction(
    CategoryActions.REMOVE_CATEGORY_API,
    props<{ categoryId: number }>()
);

export const removeCategoryState = createAction(
    CategoryActions.REMOVE_CATEGORY_STATE,
    props<{ categoryId: number }>()
);

export const removeAllCategoryApi = createAction(
    CategoryActions.REMOVE_ALL_CATEGORY_API,
    props<{ categoryIds: number[] }>()
);

export const removeAllCategoryState = createAction(
    CategoryActions.REMOVE_ALL_CATEGORY_STATE
);