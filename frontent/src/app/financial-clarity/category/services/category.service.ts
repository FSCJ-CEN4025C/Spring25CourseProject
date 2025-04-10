import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Category } from '../models/category.interface';
import { Budget } from '../../budget/models/budget.interface'

@Injectable({
    providedIn: 'root'
})
export class CategoryService {

    constructor(private http: HttpClient) { }


    createCategory(category: Omit<Category, 'id'>): Observable<Category> {
        return this.http.post<Category>(`${environment.apiURL}/create`, category).pipe(
            tap((data: Category) => data),
            catchError(err => throwError(() => err))
        )
    }

    getCategories(): Observable<Category[]> {
        return this.http.get<Category[]>(`${environment.apiURL}/categories`).pipe(
            tap((data: Category[]) => data),
            catchError(err => throwError(() => err))
        )
    }

    getBudgetsByCategoryId(categoryId: number): Observable<Budget[]> {
        return this.http.get<Budget[]>(`${environment.apiURL}/budgets?categoryId=${categoryId}`).pipe(
            catchError(err => throwError(() => err))
        );
    }


    getCategory(id: number): Observable<Category> {
        return this.http.get<Category>(`${environment.apiURL}/categories/${id}`).pipe(
            tap((data: Category) => data),
            catchError(err => throwError(() => err))
        )
    }

    updateCategory(id: number, category: Category) : Observable<Category> {
        return this.http.put<Category>(`${environment.apiURL}/categories/${id}`, category).pipe(
            catchError(err => throwError(() => err))
        )
    }

    deleteCategory(id: number) : Observable<Category> {
        return this.http.delete<Category>(`${environment.apiURL}/categories/${id}`).pipe(
            catchError(err => throwError(() => err))
        )
    }

    // Use with Cole's API
    getCategorySummary(id: number): Observable<{ totalIncome: number; totalExpense: number }> {
        return this.http.get<{ totalIncome: number; totalExpense: number }>(
            `${environment.apiURL}/categories/${id}/summary`
        ).pipe(
            catchError(err => throwError(() => err))
        );
    }

}
