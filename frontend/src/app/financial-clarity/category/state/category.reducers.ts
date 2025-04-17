import { createReducer, on } from '@ngrx/store';
import { Category } from '../models/category.interface';
import {
    addCategoryState,
    modifyCategoryState,
    removeAllCategoryState,
    removeCategoryState,
    setCategoryList,
    setSelectedCategory
} from './category.actions';

export interface CategoryState {
    categories: ReadonlyArray<Category>;
    selectedCategoryId: number | null;
}

export const initialState: CategoryState = {
    categories: [],
    selectedCategoryId: null
};

export const categoryReducer = createReducer(
    initialState,
    on(setCategoryList, (state, { categories }) => {
        return { ...state, categories };
    }),
    on(removeCategoryState, (state, { categoryId }) => {
        const categoryIdAsNumber = Number(categoryId);
        if (isNaN(categoryIdAsNumber)) {
            return state;
        }
        return { ...state,
            categories: state.categories.filter(data => data.id !== categoryIdAsNumber)
        };
    }),
    on(addCategoryState, (state, { category }) => {
        const newCategory: Category = {
            ...category,
            id: (category as Category).id || 0
        };

        return {
            ...state,
            categories: [...state.categories, newCategory]
        };
    }),
    on(modifyCategoryState, (state, { category }) => {
        return {
            ...state,
            categories: state.categories.map(data => data.id === category.id ? category : data)
        };
    }),
    on(removeAllCategoryState, (state) => {
        return { ...state, categories: [] };
    }),
    on(removeCategoryState, (state, { categoryId }) => {
        return {
            ...state,
            categories: state.categories.filter(category => category.id !== categoryId)
        };
    }),
    on(setSelectedCategory, (state, { id }) => ({
        ...state, selectedCategoryId: id
    }))
);
