import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
    providedIn: 'root'
})
export class SnackbarService {
    constructor(private snackBar: MatSnackBar) {}

    show(message: string, action: string = 'OK', duration: number = 3000) {
        this.snackBar.open(message, action, {
            duration,
            verticalPosition: 'top', // 'bottom' or 'top'
            horizontalPosition: 'center', // 'start', 'center', 'end', 'left', 'right'
            panelClass: ['snackbar-style'] // Custom styling (optional)
        });
    }
}
