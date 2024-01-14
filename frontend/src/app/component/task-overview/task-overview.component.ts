import { Component, OnInit } from '@angular/core';
import { Observable, filter, tap } from 'rxjs';
import { Task } from 'src/app/model/task';
import { TaskCategory } from 'src/app/model/task-category';
import { TaskCategoryService } from 'src/app/service/Task-category.service';
import { TaskService } from 'src/app/service/task.sevice';

@Component({
  selector: 'todo-task-overview',
  templateUrl: './task-overview.component.html',
  styleUrls: ['./task-overview.component.scss']
})
export class TaskOverviewComponent {

    public tasks$: Observable<Task[]>
    public taskCategories$: Observable<TaskCategory[]>

    constructor(private readonly taskService: TaskService, private readonly taskCategoryService: TaskCategoryService){
        this.tasks$ = taskService.getTasks$();
        this.taskCategories$ = taskCategoryService.getTasksCategories$();
    }

    public onSave(task: Task){
        this.taskService.saveTask$(task).pipe(
            filter(task => Boolean(task)),
            tap(() => this.tasks$ = this.taskService.getTasks$())
        ).subscribe();
    }
}
