import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConexionAddComponent } from './conexion-add.component';

describe('ConexionAddComponent', () => {
  let component: ConexionAddComponent;
  let fixture: ComponentFixture<ConexionAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConexionAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConexionAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
