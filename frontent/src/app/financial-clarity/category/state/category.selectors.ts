import { createSelector, createFeatureSelector } from '@ngrx/store';
import { CategoryState } from './category.reducers';

export const selectCategoryState = createFeatureSelector<CategoryState>('categoryState');

export const selectCategories = createSelector(
    selectCategoryState,
    (state: CategoryState) => state.categories
);

export const selectCategoryById = (id: number) =>
    createSelector(selectCategories, (categories) =>
        categories.find((category) => category.id === id) || null
    );

export const selectSelectedCategoryId = createSelector(
    selectCategoryState,
    (state: CategoryState) => state.selectedCategoryId
);

export const selectCurrentCategory = createSelector(
    selectCategoryState,
    selectSelectedCategoryId,
    (state, selectedCategoryId) => {
        if (selectedCategoryId === null) return null;
        return state.categories.find(category => category.id === selectedCategoryId);
    }
);
