import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskComponent } from './task.component';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { mockedTask } from '../../mocks/mock-task';
import { mockedCategories } from '../../mocks/mock-category';
import { SvgIconComponent } from '../svg-icon/svg-icon.component';
import { DebugElement } from '@angular/core';
import { By } from '@angular/platform-browser';

describe('TaskComponent', () => {
  let component: TaskComponent;
  let fixture: ComponentFixture<TaskComponent>;

  class Template {
    public static get taskForm(): HTMLElement {
      return fixture.nativeElement.querySelector('form');
    }

    public static get readonlyCard(): HTMLElement {
      return fixture.nativeElement.querySelector('div.readonly-container');
    }

    public static get icons(): DebugElement[] {
      return fixture.debugElement.queryAll(By.css('todo-svg-icon'));
    }

    public static get saveButton(): DebugElement {
      return fixture.debugElement.query(By.css('button'));
    }
  }


  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReactiveFormsModule],
      declarations: [ TaskComponent, SvgIconComponent ],
      providers: [FormBuilder]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TaskComponent);
    component = fixture.componentInstance;
    component.task = mockedTask;
    component.categories = mockedCategories;
    component.readOnly = true;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should display the readonly card', () => {
    expect(Template.readonlyCard).not.toBeNull()
  })

  
  describe('on delete', () => {
    beforeEach(() => {
      jest.spyOn(component.deleted, "emit");
      Template.icons[1].triggerEventHandler('click', null);
    })

    it("should emit delete", () => {
      expect(component.deleted.emit).toHaveBeenCalled()
    })
  })

  describe('on edit', () => {
    beforeEach(() => {
      Template.icons[0].triggerEventHandler('click', null);
      fixture.detectChanges()
    })

    it("should not render the readonly card", () => {
      expect(Template.readonlyCard).toBeNull()
    })

    it("should render form", () => {
      expect(Template.taskForm).not.toBeNull()
    })

    describe("on task save", () =>{
      beforeEach(() =>{
        jest.spyOn(component.saved, "emit");
        Template.saveButton.triggerEventHandler('click', null);
      })

      it("should emit save", () => {
        expect(component.saved.emit).toHaveBeenCalledWith(mockedTask)
      })
    })
  })
});
