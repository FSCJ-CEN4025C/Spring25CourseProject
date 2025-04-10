import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { EMPTY, forkJoin } from 'rxjs';
import { map, mergeMap, catchError, tap } from 'rxjs/operators';
import { CategoryService } from '../services/category.service';
import { CategoryActions } from './category.actions';
import { Category } from '../models/category.interface';

@Injectable()
export class CategoryEffects {

    getCategories$ = createEffect(() => {
        return this.actions$.pipe(
            ofType(CategoryActions.GET_CATEGORY_LIST),
            mergeMap(() => this.categoryService.getCategories()
                .pipe(
                    map(categories => ({
                        type: CategoryActions.SET_CATEGORY_LIST,
                        categories
                    })),
                    catchError(() => EMPTY)
                ))
        )
    }, { dispatch: true }
    );


    addCategory$ = createEffect(() => {
        return this.actions$.pipe(
            ofType(CategoryActions.ADD_CATEGORY_API),
            mergeMap((data: { type: string; payload: Omit<Category, 'id'> }) =>
                this.categoryService.createCategory(data.payload).pipe(
                    map((createdCategory) => ({
                        type: CategoryActions.ADD_CATEGORY_STATE,
                        category: createdCategory,
                    })),
                    tap(() => this.router.navigate(['categories'])),
                    catchError(() => EMPTY)
                )
            )
        );
    }, { dispatch: true }
    );

    modifyCategory$ = createEffect(() => {
        return this.actions$.pipe(
            ofType(CategoryActions.MODIFY_CATEGORY_API),
            mergeMap((data: { type: string; payload: Category }) =>
                this.categoryService.updateCategory(data.payload.id, data.payload).pipe(
                    map((category) => ({
                        type: CategoryActions.MODIFY_CATEGORY_STATE,
                        category: data.payload,
                    })),
                    tap(() => this.router.navigate(['categories'])),
                    catchError(() => EMPTY)
                )
            )
        );
    }, { dispatch: true }
    );

    removeCategory$ = createEffect(() => {
        return this.actions$.pipe(
            ofType(CategoryActions.REMOVE_CATEGORY_API),
            mergeMap((data: { payload: number }) =>
                this.categoryService.deleteCategory(data.payload).pipe(
                    map(() => ({
                        type: CategoryActions.REMOVE_CATEGORY_STATE,
                        categoryId: data.payload,
                    })),
                    catchError(() => EMPTY)
                )
            )
        );
    }, { dispatch: true }
    );

    removeAllCategories$ = createEffect(() => {
        return this.actions$.pipe(
            ofType(CategoryActions.REMOVE_ALL_CATEGORY_API),
            mergeMap((data: { type: string; payload: number[] }) =>
                forkJoin([...data.payload.map((id) => this.categoryService.deleteCategory(id))]).pipe(
                    map(() => ({ type: CategoryActions.REMOVE_ALL_CATEGORY_STATE })),
                    catchError(() => EMPTY)
                )
            )
        );
    }, { dispatch: true }
    );

    constructor(
        private actions$: Actions,
        private categoryService: CategoryService,
        private router: Router
    ) {}
}
