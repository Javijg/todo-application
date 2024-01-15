import { DatePipe } from '@angular/common';
import {
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  Component,
  EventEmitter,
  Input,
  OnInit,
  Output,
} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SvgIcon } from 'src/app/model/enum/svg-icon.enum';
import { Task } from 'src/app/model/task';
import { TaskCategory } from 'src/app/model/task-category';

@Component({
  selector: 'todo-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [DatePipe]
})
export class TaskComponent implements OnInit {
  @Input() task: Task;
  @Input() categories: TaskCategory[];
  @Input() readOnly = true;

  @Output() public saved: EventEmitter<Task> = new EventEmitter<Task>();
  @Output() public deleted: EventEmitter<void> = new EventEmitter<void>();

  public taskForm: FormGroup;
  public SVG_ICON = SvgIcon;

  constructor(private formBuilder: FormBuilder, private datePipe: DatePipe) {}

  public ngOnInit(): void {
    this.taskForm = this.formBuilder.group({
      id: this.task?.id,
      name: [this.task?.name, Validators.required],
      description: this.task?.description,
      deadline: this.datePipe.transform(this.task?.deadline, "yyyy-MM-dd"),
      categoryName: [this.task?.category.name, Validators.required],
    });
  }

  public onDelete():void{
    this.deleted.emit();
  }

  public onEdit():void{
    this.readOnly = false;
  }

  public onSave(): void {
    this.saved.emit(this.taskInput);
    this.taskForm.reset();
  }

  public get taskInput(): Task {
    return {
      id: this.taskForm.value.id,
      name: this.taskForm.value.name,
      description: this.taskForm.value.description,
      deadline: this.taskForm.value.deadline,
      category: this.categoryInput,
    };
  }

  public get categoryInput(): TaskCategory {
    return this.categories.find(
      (category) => category.name == this.taskForm.value.categoryName
    );
  }
}
