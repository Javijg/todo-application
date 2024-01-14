import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, map, of } from 'rxjs';
import { Task } from '../model/task';

@Injectable({
  providedIn: 'root',
})
export class TaskService {
  constructor(private readonly http: HttpClient) {}

  public getTasks$(): Observable<Task[]> {
    return this.http.get<Task[]>('task');
  }

  public saveTask$(task: Task): Observable<Task> {
    return this.http.post<Task>('task', task);
  }

  public deleteTask$(task: Task): Observable<boolean> {
    return this.http.delete<Task>('task').pipe(
      map(() => true),
      catchError(() => of(false))
    );
  }
}
