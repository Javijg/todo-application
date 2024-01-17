import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SvgIconComponent } from '../svg-icon/svg-icon.component';

describe('TaskComponent', () => {
  let component: SvgIconComponent;
  let fixture: ComponentFixture<SvgIconComponent>;


  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SvgIconComponent ],
    })
    .compileComponents();

    fixture = TestBed.createComponent(SvgIconComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
