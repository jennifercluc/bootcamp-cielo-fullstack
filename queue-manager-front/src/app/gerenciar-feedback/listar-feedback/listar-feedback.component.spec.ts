import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarFeedbackComponent } from './listar-feedback.component';

describe('ListarFeedbackComponent', () => {
  let component: ListarFeedbackComponent;
  let fixture: ComponentFixture<ListarFeedbackComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListarFeedbackComponent]
    });
    fixture = TestBed.createComponent(ListarFeedbackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
