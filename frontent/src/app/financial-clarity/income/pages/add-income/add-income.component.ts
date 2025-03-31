import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { catchError, Observable } from 'rxjs';
import { User } from 'src/app/auth/models/user.interface';
import { selectError, selectUser } from 'src/app/auth/state/auth.selectors';
import { Category } from 'src/app/financial-clarity/category/models/category.interface';
import { CategoryService } from 'src/app/financial-clarity/category/services/category.service';

@Component({
  selector: 'app-add-income',
  templateUrl: './add-income.component.html',
  styleUrls: ['./add-income.component.scss']
})
export class AddIncomeComponent implements OnInit {
  user$ = this.store.select(selectUser());
  categories$: Observable<Category[]> | undefined;

  constructor(private store: Store, private categoryService: CategoryService) {}

  ngOnInit(): void {
    this.loadCategories();
  }

  loadCategories(): void {
    this.categories$ = this.categoryService.getCategories().pipe(
      catchError(err => {
        return [];
      })
    );
  }
  

}
