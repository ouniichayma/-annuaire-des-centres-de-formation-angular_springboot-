import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CentreDetailsComponent } from './centre-details.component';

describe('CentreDetailsComponent', () => {
  let component: CentreDetailsComponent;
  let fixture: ComponentFixture<CentreDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CentreDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CentreDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
