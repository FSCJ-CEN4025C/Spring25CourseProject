import { Component, OnInit } from "@angular/core";
import { catchError, Observable } from "rxjs";
import { CategoryType } from "src/app/financial-clarity/category/enums/category-type.enum";
import { Category } from "src/app/financial-clarity/category/models/category.interface";
import { CategoryService } from "src/app/financial-clarity/category/services/category.service";
import { ExpenseService } from "src/app/financial-clarity/expense/services/expense.service";
import { IncomeService } from "src/app/financial-clarity/income/services/income.service";

@Component({
  selector: "app-test",
  templateUrl: "./test.component.html",
  styleUrls: ["./test.component.scss"],
})
export class TestComponent implements OnInit {
  categoryIncomeForModal: Category = {
    id: 1,
    name: "Job 1",
    type: CategoryType.INCOME,
  };

  categoryExpenseForModal: Category = {
    id: 1,
    name: "Food",
    type: CategoryType.EXPENSE,
  };

  categories$ = this.categoryService.getCategories();
  isModalVisible = false;

  incomes$ = this.incomeService.getIncomes();
  totalIncome$ = this.incomeService.getTotalIncome();

  expenses$ = this.expenseService.getExpenses();
  totalExpense$ = this.expenseService.getTotalExpense();

  constructor(
    private categoryService: CategoryService,
    private incomeService: IncomeService,
    private expenseService: ExpenseService
  ) {}

  ngOnInit(): void {}

  openModal(): void {
    this.isModalVisible = true;
  }

  closeModal(): void {
    this.isModalVisible = false;
  }

  onModalSave(): void {
    this.closeModal();
  }
}
