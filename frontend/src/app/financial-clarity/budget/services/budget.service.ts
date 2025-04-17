import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Budget } from '../models/budget.interface';

@Injectable({
  providedIn: 'root'
})
export class BudgetService {

  constructor(private http: HttpClient) { }

  
  getBudgets(): Observable<Budget[]> {
    return this.http.get<Budget[]>(`${environment.apiURL}/budgets`).pipe(
      tap((data: Budget[]) => data),
      catchError(err => throwError(() => err))
   )
  }

  getBudget(id: number): Observable<Budget> {
    return this.http.get<Budget>(`${environment.apiURL}/budgets/${id}`).pipe(
       tap((data: Budget) => data),
       catchError(err => throwError(() => err))
    )
   }

    getBudgetsByCategoryId(categoryId: number): Observable<Budget[]> {
        return this.http.get<Budget[]>(`${environment.apiURL}/budgets?categoryId=${categoryId}`).pipe(
            tap((data: Budget[]) => {
                console.log('Fetched budgets for category:', categoryId, data);
            }),
            catchError(err => {
                console.error('Error fetching budgets for category', categoryId, err);
                throw err;
            })
        );
    }

    createBudget(budget: Omit<Budget, 'id'>): Observable<Budget> {
        return this.http.post<Budget>(`${environment.apiURL}/budgets`, budget).pipe(
            tap((createdBudget) => createdBudget),
            catchError(err => throwError(() => err))
        );
    }


  updateBudget(id:number, budget: Budget) : Observable<Budget> {
    return this.http.put<Budget>(`${environment.apiURL}/budgets/${id}`, budget).pipe(
      catchError(err => throwError(() => err))
   )
  }

   deleteBudget(id:string) : Observable<Budget> {
    return this.http.delete<Budget>(`${environment.apiURL}/budgets/${id}`).pipe(
      catchError(err => throwError(() => err))
   )
  }
}
