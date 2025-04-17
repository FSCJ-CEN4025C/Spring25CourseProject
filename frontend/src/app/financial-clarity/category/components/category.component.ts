import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

import { SnackbarService} from "../../../core/services/snackbar.service";
import { CategoryService } from '../services/category.service';
import { Category } from '../models/category.interface';
import { CategoryType } from '../enums/category-type.enum';
import { BudgetService } from "../../budget/services/budget.service";

import {
  getCategoryList,
  setTotals
} from '../state/category.actions';

import { selectCategories } from "../state/category.selectors";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss']
})
export class CategoryComponent implements OnInit {
  categories: Category[] = [];
  errorMessage: string = '';
  selectedCategory: Category | null = null;
  showTransactionModal: boolean = false;

  totalIncome: number = 0;
  totalExpenses: number = 0;
  showAddCategoryForm: boolean = false;
  categoryForm: FormGroup;
  categoryTypeEnum = CategoryType;

  constructor(
      private categoryService: CategoryService,
      private budgetService: BudgetService,
      private store: Store,
      private snackbarService: SnackbarService,
      private fb: FormBuilder
  ) {
    this.categoryForm = this.fb.group({
      name: ['', Validators.required],
      type: [CategoryType.EXPENSE, Validators.required]
    });
  }

  ngOnInit(): void {
    this.store.dispatch(getCategoryList());
    this.store.select(selectCategories).subscribe(categories => {
      this.categories = categories as Category[];
      this.calculateTotals();
      this.loadCategoryBudgets();
    });

    this.categoryForm = this.fb.group({
      name: ['', Validators.required],
      type: [CategoryType.EXPENSE, Validators.required]
    });
  }

  createCategory(): void {
    if (this.categoryForm.invalid) {
      return;
    }

    const newCategory = this.categoryForm.value;

    this.categoryService.createCategory(newCategory).subscribe({
      next: (res) => {
        this.loadCategories();
        this.showAddCategoryForm = false;
        this.categoryForm.reset({
          name: '',
          type: CategoryType.EXPENSE
        });

        this.snackbarService.show('Category created successfully!');
      },
      error: (err) => {
        console.error('Error creating category', err);
        this.snackbarService.show(
            'Error creating category. Please try again.', 'Close', 5000
        );
      }
    });
  }

  loadCategories(): void {
    this.categoryService.getCategories().pipe(
        catchError(err => {
          this.errorMessage = 'Error loading categories!';
          this.snackbarService.show(this.errorMessage, 'Close', 5000);
          return of([]);
        })
    ).subscribe((data: Category[]) => {
      this.categories = data;
      this.calculateTotals();
      this.loadCategoryBudgets();
    });
  }

  loadCategoryBudgets(): void {
    this.categories.forEach((category) => {
      let totalAmount = 0;

      if (category.type === CategoryType.INCOME) {
        totalAmount = this.mockIncomeTotal(category.id);
      } else if (category.type === CategoryType.EXPENSE) {
        totalAmount = this.mockExpenseTotal(category.id);
      }
      category.totalAmount = totalAmount;
    });
  }

  calculateTotals(): void {
    this.totalIncome = this.categories
        .filter(cat => cat.type === CategoryType.INCOME)
        .reduce((sum, cat) => sum + (cat.totalAmount || 0), 0);

    this.totalExpenses = this.categories
        .filter(cat => cat.type === CategoryType.EXPENSE)
        .reduce((sum, cat) => sum + (cat.totalAmount || 0), 0);


    this.store.dispatch(setTotals({ totalIncome: this.totalIncome, totalExpenses: this.totalExpenses }));
  }

  openTransactionModal(category: Category): void {
    this.selectedCategory = category;
    this.showTransactionModal = true;
  }

  closeTransactionModal(): void {
    this.showTransactionModal = false;
    this.selectedCategory = null;
  }

  refreshTotals(): void {
    this.closeTransactionModal();
    this.loadCategories();
  }

  mockIncomeTotal(categoryId: number): number {
    return Math.floor(Math.random() * 500);
  }

  mockExpenseTotal(categoryId: number): number {
    return Math.floor(Math.random() * 500);
  }

  openNewCategoryModal(): void {
    // TODO: add a "create category" modal
    console.log('Open new category modal - not implemented yet');
  }
}
