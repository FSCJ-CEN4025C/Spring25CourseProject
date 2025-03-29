import {CategoryType} from "../enums/category-type.enum";

export interface Category {
    id: number;
    name: string;
    type: CategoryType;
}

export interface NewCategory {
    name: string;
    type: CategoryType;
}
