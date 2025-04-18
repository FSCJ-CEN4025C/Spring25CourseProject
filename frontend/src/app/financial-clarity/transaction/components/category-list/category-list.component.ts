import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  OnInit,
  Output,
  SimpleChange,
  SimpleChanges,
} from "@angular/core";
import { Category } from "src/app/financial-clarity/category/models/category.interface";
import { CategoryService } from "src/app/financial-clarity/category/services/category.service";
import { ExpenseService } from "src/app/financial-clarity/expense/services/expense.service";
import { IncomeService } from "src/app/financial-clarity/income/services/income.service";
import { combineLatest, switchMap, map } from "rxjs";

@Component({
  selector: "app-category-list",
  templateUrl: "./category-list.component.html",
  styleUrls: ["./category-list.component.scss"],
})
export class CategoryListComponent implements OnInit, OnChanges {
  @Input() selectedDate: Date | null = null;
  @Output() onDateClicked = new EventEmitter<Date | null>();

  totalIncome: number = 0;
  totalExpense: number = 0;

  ngOnChanges(changes: SimpleChanges): void {
    if (changes["selectedDate"]) {
      this.getData();
    }
  }

  categories: { category: Category; amount: any }[] = [];
  categories$ = this.categoryService.getCategories();

  constructor(
    private categoryService: CategoryService,
    private incomeService: IncomeService,
    private expenseService: ExpenseService
  ) {}

  onAllTimeTransactions() {
    this.onDateClicked.emit(null);
  }

  ngOnInit(): void {
    this.getData();
  }

  getData() {
    this.getTotalExpenseAndIncomeForAllCategories();
    this.getCategoriesAndTotalAmount();
  }

  getTotalExpenseAndIncomeForAllCategories() {
    this.incomeService
      .getTotalIncomeByDate(this.selectedDate)
      .subscribe((totalIncome) => {
        this.totalIncome = totalIncome;
      });

    this.expenseService
      .getTotalExpenseByDate(this.selectedDate)
      .subscribe((totalExpense) => {
        this.totalExpense = totalExpense;
      });
  }

  getCategoriesAndTotalAmount() {
    this.categoryService
      .getCategories()
      .pipe(
        switchMap((categories) => {
          const categoryObservables = categories.map((category) => {
            if (category.type === "INCOME") {
              return this.incomeService
                .getTotalIncome(category.id, this.selectedDate)
                .pipe(map((amount) => ({ category, amount })));
            } else if (category.type === "EXPENSE") {
              return this.expenseService
                .getTotalExpense(category.id, this.selectedDate)
                .pipe(map((amount) => ({ category, amount })));
            } else {
              return [];
            }
          });

          return combineLatest(categoryObservables);
        })
      )
      .subscribe((categoryData) => {
        this.categories = categoryData;
      });
  }
}
