import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstanciaAddComponent } from './instancia-add.component';

describe('InstanciaAddComponent', () => {
  let component: InstanciaAddComponent;
  let fixture: ComponentFixture<InstanciaAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstanciaAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InstanciaAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

 
});
