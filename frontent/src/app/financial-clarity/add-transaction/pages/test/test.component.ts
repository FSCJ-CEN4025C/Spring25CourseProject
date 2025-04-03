import { Component, OnInit } from "@angular/core";
import { catchError, Observable } from "rxjs";
import { CategoryType } from "src/app/financial-clarity/category/enums/category-type.enum";
import { Category } from "src/app/financial-clarity/category/models/category.interface";
import { CategoryService } from "src/app/financial-clarity/category/services/category.service";

@Component({
  selector: "app-test",
  templateUrl: "./test.component.html",
  styleUrls: ["./test.component.scss"],
})
export class TestComponent implements OnInit {
  categoryForModal: Category = {
    id: 0,
    name: "Food/Food",
    type: CategoryType.INCOME,
  };
  categories$: Observable<Category[]> | undefined;
  isModalVisible = false;

  constructor(private categoryService: CategoryService) {}

  ngOnInit(): void {
    this.loadCategories();
  }

  loadCategories(): void {
    this.categories$ = this.categoryService.getCategories().pipe(
      catchError((err) => {
        return [];
      })
    );
  }

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
