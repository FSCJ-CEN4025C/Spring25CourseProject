import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { CategoryType } from "src/app/financial-clarity/category/enums/category-type.enum";
import { Category } from "src/app/financial-clarity/category/models/category.interface";
import { IncomeService } from "../../../income/services/income.service";
import { ExpenseService } from "../../../expense/services/expense.service";

@Component({
  selector: "app-add-transaction-modal",
  templateUrl: "./add-transaction-modal.component.html",
  styleUrls: ["./add-transaction-modal.component.scss"],
})
export class AddTransactionModalComponent implements OnInit {
  @Input() selectedCategory: Category | null = null;
  @Output() closeModalEvent = new EventEmitter<void>();
  @Output() onModalSave = new EventEmitter<void>();

  transactionForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private incomeService: IncomeService,
    private expenseService: ExpenseService
  ) {}

  ngOnInit(): void {
    this.transactionForm = this.fb.group({
      categoryId: [this.selectedCategory?.id, [Validators.required]],
      amount: [null, [Validators.required]],
    });
  }

  close(): void {
    this.closeModalEvent.emit();
  }

  saveTransaction(): void {
    console.log("Transaction save attempt in modal component");
    console.log(this.transactionForm.value);

    if (this.transactionForm.invalid) {
      console.error("Form is invalid. Cannot save.");
      this.transactionForm.markAllAsTouched(); // Show errors to user
      return;
    }

    if (this.selectedCategory?.type == CategoryType.INCOME) {
      console.log("\n\n test");
      console.log(this.transactionForm.value);
      const response = this.incomeService.addIncome(this.transactionForm.value);
      response.subscribe((data) => {
        console.log(data);
      });
      console.log(response);
    } else if (this.selectedCategory?.type == CategoryType.EXPENSE) {
      this.expenseService.addExpense(this.transactionForm.value);
    }

    this.onModalSave.emit();
  }
}
