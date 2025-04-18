import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListTransactionsModalComponent } from './list-transactions-modal.component';

describe('ListTransactionsModalComponent', () => {
  let component: ListTransactionsModalComponent;
  let fixture: ComponentFixture<ListTransactionsModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListTransactionsModalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListTransactionsModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
