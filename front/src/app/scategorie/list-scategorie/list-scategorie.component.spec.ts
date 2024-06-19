import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListScategorieComponent } from './list-scategorie.component';

describe('ListScategorieComponent', () => {
  let component: ListScategorieComponent;
  let fixture: ComponentFixture<ListScategorieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListScategorieComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListScategorieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
