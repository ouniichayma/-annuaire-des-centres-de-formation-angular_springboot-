import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdduserformationComponent } from './adduserformation.component';

describe('AdduserformationComponent', () => {
  let component: AdduserformationComponent;
  let fixture: ComponentFixture<AdduserformationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdduserformationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdduserformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
