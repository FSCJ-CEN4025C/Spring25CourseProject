import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { catchError, Observable, tap, throwError } from "rxjs";
import { environment } from "src/environments/environment";
import { Expense } from "../models/expense.interface";

@Injectable({
  providedIn: "root",
})
export class ExpenseService {
  constructor(private http: HttpClient) {}

  addExpense(expense: Expense): Observable<Expense> {
    return this.http
      .post<Expense>(`${environment.apiURL}/expenses`, expense)
      .pipe(
        tap((data: Expense) => data),
        catchError((err) => throwError(() => err))
      );
  }
}
