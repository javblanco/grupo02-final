import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Flujo.AddComponent } from './flujo.add.component';

describe('Flujo.AddComponent', () => {
  let component: Flujo.AddComponent;
  let fixture: ComponentFixture<Flujo.AddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Flujo.AddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Flujo.AddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
