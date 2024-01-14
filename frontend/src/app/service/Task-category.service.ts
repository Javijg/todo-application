import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from "rxjs";
import { TaskCategory } from "../model/task-category";

@Injectable({
    providedIn: 'root',
})
export class TaskCategoryService {

    constructor(private readonly http: HttpClient) {}

    public getTasksCategories$(): Observable<TaskCategory[]>{
        return this.http.get<TaskCategory[]>("task-category").pipe(
            catchError(() =>
                throwError(() => "There was an error while retrieving task categories")
            )
        )
    }
}