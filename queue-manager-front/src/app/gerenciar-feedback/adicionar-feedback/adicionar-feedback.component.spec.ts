import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AdicionarFeedBackComponent } from './adicionar-feedback.component';

describe('AdicionarFeedBackComponent', () => {
  let component: AdicionarFeedBackComponent;
  let fixture: ComponentFixture<AdicionarFeedBackComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdicionarFeedBackComponent],
    });
    fixture = TestBed.createComponent(AdicionarFeedBackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
