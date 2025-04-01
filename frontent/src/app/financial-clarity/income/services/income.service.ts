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
    return this.http.post<Income>(`${environment.apiURL}/incomes`, income).pipe(
      tap((data: Income) => data),
      catchError((err) => throwError(() => err))
    );
  }
}
