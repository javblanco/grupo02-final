import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlujoAddComponent } from './flujo-add.component';

describe('FlujoAddComponent', () => {
  let component: FlujoAddComponent;
  let fixture: ComponentFixture<FlujoAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FlujoAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FlujoAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
