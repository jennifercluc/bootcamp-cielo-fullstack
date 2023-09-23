import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalharFeedbackEnviadoComponent } from './detalhar-feedback-enviado.component';

describe('DetalharFeedbackEnviadoComponent', () => {
  let component: DetalharFeedbackEnviadoComponent;
  let fixture: ComponentFixture<DetalharFeedbackEnviadoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DetalharFeedbackEnviadoComponent]
    });
    fixture = TestBed.createComponent(DetalharFeedbackEnviadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
