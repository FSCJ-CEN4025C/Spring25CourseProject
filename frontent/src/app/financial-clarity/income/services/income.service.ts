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

  getIncomes(): Observable<Income[]> {
    return this.http.get<Income[]>(`${environment.apiURL}/income`).pipe(
      tap((data: Income[]) => data),
      catchError((err) => throwError(() => err))
    );
  }

  getTotalIncome(): Observable<Income[]> {
    return this.http
      .get<Income[]>(`${environment.apiURL}/income/totalIncome`)
      .pipe(
        tap((data: Income[]) => data),
        catchError((err) => throwError(() => err))
      );
  }
}

// {

//   "categoryId": 1,
//   "amount": 0,
//   "name": "test123"
// }
