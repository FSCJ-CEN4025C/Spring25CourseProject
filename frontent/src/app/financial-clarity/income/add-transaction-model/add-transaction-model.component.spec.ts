import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTransactionModelComponent } from './add-transaction-model.component';

describe('AddTransactionModelComponent', () => {
  let component: AddTransactionModelComponent;
  let fixture: ComponentFixture<AddTransactionModelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddTransactionModelComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddTransactionModelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
