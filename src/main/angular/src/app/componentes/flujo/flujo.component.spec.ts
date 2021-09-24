import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlujosComponent } from './flujo.component';

describe('FlujoComponent', () => {
  let component: FlujosComponent;
  let fixture: ComponentFixture<FlujosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FlujosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FlujosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  
});
