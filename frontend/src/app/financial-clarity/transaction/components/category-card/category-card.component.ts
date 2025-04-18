import { Component, Input, OnInit } from "@angular/core";
import { Category } from "src/app/financial-clarity/category/models/category.interface";

@Component({
  selector: "app-category-card",
  templateUrl: "./category-card.component.html",
  styleUrls: ["./category-card.component.scss"],
})
export class CategoryCardComponent implements OnInit {
  @Input() selectedDate: Date | null = null;
  @Input() category!: Category;
  @Input() amount!: number;

  selectedCategory: Category | null = null;

  isModalVisible = false;

  constructor() {}

  ngOnInit(): void {}

  cardClick(category: Category): void {
    this.selectedCategory = category;
    this.openModal();
  }

  openModal(): void {
    this.isModalVisible = true;
  }

  closeModal(): void {
    this.isModalVisible = false;
  }
}
