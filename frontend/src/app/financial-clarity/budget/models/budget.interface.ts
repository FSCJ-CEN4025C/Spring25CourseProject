import {Period} from "../enums/period.enum";

export interface Budget {
    id: number;
    userId: string;
    categoryId: number;
    amount: number;
    period: Period;
    startDate: Date;
    endDate: Date;
}

export interface NewBudget {
    userId: string;
    categoryId: number;
    amount: number;
    period: Period;
    startDate: Date;
    endDate: Date;
}