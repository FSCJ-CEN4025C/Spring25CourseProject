import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Store } from '@ngrx/store';
import { AppState } from 'src/app/state/app.state';
import { selectCurrentBudget } from '../../state/budget.selectors';
import { CategoryService } from '../../../category/services/category.service';
import { Category, NewCategory } from '../../../category/models/category.interface';
import { Period } from '../../enums/period.enum';
import { CategoryType } from '../../../category/enums/category-type.enum';
import { SnackbarService } from '../../../../core/services/snackbar.service';
import {addBudgetState, modifyBudgetState} from "../../state/budget.actions";
import { NewBudget } from "../../models/budget.interface";

@Component({
  selector: 'app-budget-form',
  templateUrl: './budget-form.component.html',
  styleUrls: ['./budget-form.component.scss'],
})
export class BudgetFormComponent implements OnInit {
  @Output() action = new EventEmitter<any>();
  form!: FormGroup;
  categories: Category[] = [];
  categoryTypes = Object.values(CategoryType);
  periods = Object.values(Period);
  actionButtonLabel: string = 'Create';

  constructor(
      private fb: FormBuilder,
      private store: Store<AppState>,
      private categoryService: CategoryService,
      private router: Router,
      private snackbarService: SnackbarService
  ) {}

  ngOnInit(): void {
    this.initializeForm();
    this.fetchCategories();

    this.store.select(selectCurrentBudget).subscribe((budget) => {
      if (budget) {
        this.form.patchValue(budget);
        this.actionButtonLabel = 'Update';
        this.snackbarService.show('Budget loaded successfully!');
      }
    });
  }

  initializeForm() {
    this.form = this.fb.group(
        {
          id: [''],
          category: ['', Validators.required],
          categoryType: ['', Validators.required],
          amount: ['', [Validators.required, Validators.min(0.01)]],
          period: ['', Validators.required],
          startDate: ['', Validators.required],
          endDate: ['', Validators.required],
        },
        { validator: this.dateRangeValidator }
    );
  }

  fetchCategories() {
    this.categoryService.getCategories().subscribe((categories) => {
      this.categories = categories;
    });
  }

  submitForm() {
    if (this.form.valid) {
      const formValue = this.form.value;
      let category = this.categories.find((cat) => cat.name === formValue.category);
      this.action.emit({ type: 'submit', budget: this.form.value });

      if (!category) {
        const newCategory: NewCategory = {
          name: formValue.category,
          type: formValue.categoryType,
        };

        this.categoryService.createCategory(newCategory).subscribe(
            (createdCategory) => {
              this.snackbarService.show('New category created!');
              this.processBudget(createdCategory.id);
            },
            (error) => {
              this.snackbarService.show('Failed to create category', 'Close', 4000);
            }
        );
      } else {
        this.processBudget(category.id);
      }
    } else {
      this.snackbarService.show('Form is invalid. Please check the fields.', 'Close', 4000);
    }
  }

  processBudget(categoryId: number) {
    const formValue = this.form.value;

    if (formValue.id) {
      const updatedBudget = { ...formValue, categoryId };
      this.store.dispatch(modifyBudgetState({ budget: updatedBudget }));
      this.snackbarService.show('Budget updated successfully!');
    } else {
      const newBudget: NewBudget = {
        userId: 'testUser',
        categoryId,
        amount: formValue.amount,
        period: formValue.period,
        startDate: formValue.startDate,
        endDate: formValue.endDate,
      };

      this.store.dispatch(addBudgetState({ budget: newBudget }));
      this.snackbarService.show('Budget created successfully!');
    }

    this.router.navigate(['/budgets']);
  }

  dateRangeValidator(group: FormGroup) {
    const startDate = group.get('startDate')?.value;
    const endDate = group.get('endDate')?.value;
    return endDate && startDate && endDate < startDate ? { dateRange: true } : null;
  }

  get isUpdateMode(): boolean {
    return !!this.form?.get('id')?.value;
  }


  clear() {
    this.form.reset();
    this.snackbarService.show('Form cleared successfully');
  }
}
