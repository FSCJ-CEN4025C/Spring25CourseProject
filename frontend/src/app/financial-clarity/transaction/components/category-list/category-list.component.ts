import { Component, OnInit } from "@angular/core";
import { Category } from "src/app/financial-clarity/category/models/category.interface";
import { CategoryService } from "src/app/financial-clarity/category/services/category.service";
import { ExpenseService } from "src/app/financial-clarity/expense/services/expense.service";
import { IncomeService } from "src/app/financial-clarity/income/services/income.service";

@Component({
  selector: "app-category-list",
  templateUrl: "./category-list.component.html",
  styleUrls: ["./category-list.component.scss"],
})
export class CategoryListComponent implements OnInit {
  categories: { category: Category; amount: any }[] = [];
  categories$ = this.categoryService.getCategories();

  constructor(
    private categoryService: CategoryService,
    private incomeService: IncomeService,
    private expenseService: ExpenseService
  ) {}

  ngOnInit(): void {
    console.log("cateoagaoseiroasdf \n\n\n");
    console.log(this.categories$);
    this.categories$.subscribe((categories) => {
      categories.forEach((category: Category) => {
        if (category.type === "INCOME") {
          this.incomeService
            .getTotalIncome(category.id)
            .subscribe((totalIncome) => {
              const item = {
                category: category,
                amount: totalIncome,
              };
              this.categories.push(item);
            });
        } else if (category.type === "EXPENSE") {
          this.expenseService
            .getTotalExpense(category.id)
            .subscribe((totalExpense) => {
              const item = {
                category: category,
                amount: totalExpense,
              };
              this.categories.push(item);
            });
        }
      });
    });
    console.log(this.categories$);
  }
}
// categories: any[] = [
//   { name: "Groceries", type: "expense", amount: 200 },
//   { name: "Rent", type: "expense", amount: 1200 },
//   { name: "Salary", type: "income", amount: 3500 },
//   { name: "Utilities", type: "expense", amount: 150 },
//   { name: "Freelance", type: "income", amount: 800 },
//   { name: "Dining Out", type: "expense", amount: 120 },
//   { name: "Transportation", type: "expense", amount: 80 },
//   { name: "Investments", type: "income", amount: 400 },
// ];
