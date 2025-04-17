import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BudgetCommandBarComponent } from './budget-command-bar.component';

describe('BudgetCommandBarComponent', () => {
  let component: BudgetCommandBarComponent;
  let fixture: ComponentFixture<BudgetCommandBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BudgetCommandBarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BudgetCommandBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
