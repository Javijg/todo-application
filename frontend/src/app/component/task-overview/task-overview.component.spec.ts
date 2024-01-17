import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TaskOverviewComponent } from './task-overview.component';
import { TaskService } from '../../service/task.sevice';
import { TaskCategoryService } from '../../service/task-category.service';
import { TaskComponent } from '../task/task.component';
import { DebugElement } from '@angular/core';
import { By } from '@angular/platform-browser';
import { mockedCategories } from '../../mocks/mock-category';
import { of } from 'rxjs';
import { FormBuilder } from '@angular/forms';
import { mockedTask } from '../../mocks/mock-task';
import { MockComponent } from 'ng-mocks';

describe('TaskOverviewComponent', () => {
  let component: TaskOverviewComponent;
  let fixture: ComponentFixture<TaskOverviewComponent>;
  let taskService: TaskService;
  let taskCategoryService: TaskCategoryService;

  class Template {
    public static get tasks(): DebugElement[] {
      return fixture.debugElement.queryAll(By.css('todo-task'));
    }
  }

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TaskOverviewComponent, MockComponent(TaskComponent)],
      providers: [
        {
          provide: TaskService,
          useValue: {
            getTasks$: jest.fn(),
            saveTask$: jest.fn().mockReturnValue(of(mockedTask)),
          },
        },
        {
          provide: TaskCategoryService,
          useValue: {
            getTasksCategories$: jest
              .fn()
              .mockReturnValue(of(mockedCategories)),
          },
        },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(TaskOverviewComponent);
    taskService = TestBed.inject(TaskService);
    taskCategoryService = TestBed.inject(TaskCategoryService);

    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should get tasks', () => {
    expect(taskService.getTasks$).toHaveBeenCalled();
  });

  it('should get task categories', () => {
    expect(taskCategoryService.getTasksCategories$).toHaveBeenCalled();
  });

  it('should display at least one creation tab', () => {
    expect(Template.tasks.length).toBeGreaterThan(0);
  });

  describe('on success task save', () => {
    beforeEach(() => {
      jest.clearAllMocks();
      Template.tasks[0].triggerEventHandler('saved', mockedTask);
    });

    it('should save the task', () => {
      expect(taskService.saveTask$).toHaveBeenCalledWith(mockedTask);
    });

    it('should reload the tasks', () => {
      expect(taskService.getTasks$).toHaveBeenCalledTimes(1);
    });
  });
});
