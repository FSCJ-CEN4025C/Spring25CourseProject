import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-transaction-model',
  templateUrl: './add-transaction-model.component.html',
  styleUrls: ['./add-transaction-model.component.scss']
})
export class AddTransactionModelComponent implements OnInit {
  categoryName = "food"
  constructor() { }

  ngOnInit(): void {
  }

}
