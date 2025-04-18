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
    console.log("add expense");

    return this.http
      .post<Expense>(`${environment.apiURL}/expenses`, expense)
      .pipe(
        tap((data: Expense) => data),
        catchError((err) => throwError(() => err))
      );
  }

  getExpenses(categoryId?: number, date?: Date | null): Observable<Expense[]> {
    var path = "";
    if (categoryId) {
      path = `/category/${categoryId}`;

      if (date) {
        const formattedDate = date.toISOString().split("T")[0]; // YYYY-MM-DD
        path += `/date/${formattedDate}`;
      }
    }

    return this.http
      .get<Expense[]>(`${environment.apiURL}/expenses${path}`)
      .pipe(
        tap((data: Expense[]) => data),
        catchError((err) => throwError(() => err))
      );
  }

  getTotalExpense(categoryId?: number, date?: Date | null): Observable<number> {
    var path = "";
    if (categoryId) {
      path = `/category/${categoryId}`;

      if (date) {
        const formattedDate = date.toISOString().split("T")[0]; // YYYY-MM-DD
        path += `/date/${formattedDate}`;
      }
    }
    return this.http
      .get<number>(`${environment.apiURL}/expenses/totalExpense${path}`)
      .pipe(
        tap((data: number) => data),
        catchError((err) => throwError(() => err))
      );
  }

  getTotalExpenseByDate(date?: Date | null) {
    var path = "";
    if (date) {
      const formattedDate = date.toISOString().split("T")[0]; // YYYY-MM-DD
      path += `/date/${formattedDate}`;
    }

    return this.http
      .get<number>(`${environment.apiURL}/expenses/totalExpense${path}`)
      .pipe(
        tap((data: number) => data),
        catchError((err) => throwError(() => err))
      );
  }
}
