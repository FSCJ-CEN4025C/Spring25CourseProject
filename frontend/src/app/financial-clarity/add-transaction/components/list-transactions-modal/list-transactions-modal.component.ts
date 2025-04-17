import { Expansion } from "@angular/compiler";
import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";
import { CategoryType } from "src/app/financial-clarity/category/enums/category-type.enum";
import { Category } from "src/app/financial-clarity/category/models/category.interface";
import { Expense } from "src/app/financial-clarity/expense/models/expense.interface";
import { ExpenseService } from "src/app/financial-clarity/expense/services/expense.service";
import { Income } from "src/app/financial-clarity/income/models/income.interface";
import { IncomeService } from "src/app/financial-clarity/income/services/income.service";

@Component({
  selector: "app-list-transactions-modal",
  templateUrl: "./list-transactions-modal.component.html",
  styleUrls: ["./list-transactions-modal.component.scss"],
})
export class ListTransactionsModalComponent implements OnInit {
  @Input() selectedCategory: Category | null = null;
  @Output() closeModalEvent = new EventEmitter<void>();

  transactions: (Income | Expense)[] = [];

  constructor(
    private incomeService: IncomeService,
    private expenseService: ExpenseService
  ) {}

  ngOnInit(): void {
    console.log("\n\n\n modal categ");
    console.log(this.selectedCategory);
    if (this.selectedCategory) {
      const id = this.selectedCategory.id;
      const type = this.selectedCategory.type;

      if (type == CategoryType.INCOME) {
        this.incomeService.getIncomes(id).subscribe((incomes) => {
          this.transactions = incomes;
        });
      } else if (type == CategoryType.EXPENSE) {
        this.expenseService.getExpenses(id).subscribe((expenses) => {
          this.transactions = expenses;
        });
      }
    }
  }

  close(): void {
    this.closeModalEvent.emit();
  }
}
