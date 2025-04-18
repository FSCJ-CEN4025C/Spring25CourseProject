import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { catchError, Observable, tap, throwError } from "rxjs";
import { environment } from "src/environments/environment";
import { Income } from "../models/income.interface";

@Injectable({
  providedIn: "root",
})
export class IncomeService {
  constructor(private http: HttpClient) {}

  addIncome(income: Income): Observable<Income> {
    console.log("\n\n income server");
    console.log(income);
    return this.http.post<Income>(`${environment.apiURL}/income`, income).pipe(
      tap((data: Income) => data),
      catchError((err) => throwError(() => err))
    );
  }

  getIncomes(categoryId?: number, date?: Date | null): Observable<Income[]> {
    var path = "";
    if (categoryId) {
      path = `/category/${categoryId}`;

      if (date) {
        const formattedDate = date.toISOString().split("T")[0]; // YYYY-MM-DD
        path += `/date/${formattedDate}`;
      }
    }

    return this.http.get<Income[]>(`${environment.apiURL}/income${path}`).pipe(
      tap((data: Income[]) => data),
      catchError((err) => throwError(() => err))
    );
  }

  getTotalIncome(
    categoryId?: number,
    date?: Date | null
  ): Observable<Income[]> {
    var path = "";
    if (categoryId) {
      path = `/category/${categoryId}`;

      if (date) {
        const formattedDate = date.toISOString().split("T")[0]; // YYYY-MM-DD
        path += `/date/${formattedDate}`;
      }
    }

    return this.http
      .get<Income[]>(`${environment.apiURL}/income/totalIncome${path}`)
      .pipe(
        tap((data: Income[]) => data),
        catchError((err) => throwError(() => err))
      );
  }

  getTotalIncomeByDate(date?: Date | null) {
    var path = "";
    if (date) {
      const formattedDate = date.toISOString().split("T")[0]; // YYYY-MM-DD
      path += `/date/${formattedDate}`;
    }

    return this.http
      .get<number>(`${environment.apiURL}/income/totalIncome${path}`)
      .pipe(
        tap((data: number) => data),
        catchError((err) => throwError(() => err))
      );
  }
}
