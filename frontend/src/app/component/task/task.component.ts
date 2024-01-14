import {
  ChangeDetectionStrategy,
  Component,
  EventEmitter,
  Input,
  OnInit,
  Output,
} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Task } from 'src/app/model/task';
import { TaskCategory } from 'src/app/model/task-category';

@Component({
  selector: 'todo-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.scss'],
  changeDetection:ChangeDetectionStrategy.OnPush
})
export class TaskComponent implements OnInit {
  @Input() task: Task;
  @Input() categories: TaskCategory[];
  @Input() readOnly = true;

  @Output() public saved: EventEmitter<Task> = new EventEmitter<Task>();

  public taskForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {}

  public ngOnInit(): void {
    console.log(this.categories);
    const a = this.categories;
    this.taskForm = this.formBuilder.group({
      id: this.task?.id,
      name: [this.task?.name, Validators.required],
      description: this.task?.description,
      deadline: this.task?.deadline,
      categoryName: [this.task?.category.name, Validators.required],
    });
  }

  public onSave(): void {
    this.saved.emit(this.taskInput)
  }

  public get taskInput(): Task {
    return {
      id: this.taskForm.value.id,
      name: this.taskForm.value.name,
      description: this.taskForm.value.description,
      deadline: this.taskForm.value.deadline,
      category: this.categoryInput
    };
  }

  public get categoryInput(): TaskCategory {
    return this.categories.find(
      (category) => category.name == this.taskForm.value.categoryName
    );
  }
}
