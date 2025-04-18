import { Component, OnInit } from "@angular/core";
import { CategoryType } from "src/app/financial-clarity/category/enums/category-type.enum";
import { Category } from "src/app/financial-clarity/category/models/category.interface";
import { Expense } from "src/app/financial-clarity/expense/models/expense.interface";
import { Income } from "src/app/financial-clarity/income/models/income.interface";

@Component({
  selector: "app-transaction",
  templateUrl: "./transaction.component.html",
  styleUrls: ["./transaction.component.scss"],
})
export class TransactionComponent implements OnInit {
  selectedDate: Date | null = null;

  constructor() {}

  ngOnInit(): void {}

  onDateClicked(date: Date | null) {
    this.selectedDate = date;
  }
}
