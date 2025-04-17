import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from "@angular/forms";
import { ReactiveFormsModule } from '@angular/forms';

import { MaterialModule } from 'src/app/material/material.module';

import { CategoryService } from './services/category.service';
import { CategoryRoutingModule} from "./category-routing.module";

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        MaterialModule,
        CategoryRoutingModule,
    ],
    providers: [
        CategoryService,
    ],
})
export class CategoryModule {}
