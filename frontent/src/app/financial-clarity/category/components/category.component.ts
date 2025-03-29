import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../services/category.service';
import { Category } from '../models/category.interface';
import { Observable } from 'rxjs';
import { catchError } from "rxjs/operators";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss']
})
export class CategoryComponent implements OnInit {
  categories$!: Observable<Category[]>;
  errorMessage: string = '';
  selectedCategory: Category | null = null;

  constructor(private categoryService: CategoryService) {}

  ngOnInit(): void {
    this.loadCategories();
  }

  loadCategories(): void {
    this.categories$ = this.categoryService.getCategories().pipe(
        catchError(err => {
          this.errorMessage = 'Error loading categories!';
          return [];
        })
    );
  }

  selectCategory(category: Category): void {
    this.selectedCategory = category;
    console.log('Selected Category:', category);
  }
}
