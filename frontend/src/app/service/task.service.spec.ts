import { TestBed } from "@angular/core/testing";
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TaskService } from "./task.sevice";
import { mockedTask } from "../mocks/mock-task";

describe('TaskCategoryService', () => {
    let service: TaskService;
    let http: HttpTestingController;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [HttpClientTestingModule],
        });
        service = TestBed.inject(TaskService);
        http = TestBed.inject(HttpTestingController);
    });

    afterEach(() => {
        http.verify();
    });

    it("getTasks$", () =>{
        service.getTasks$().subscribe()
        http.expectOne({ method: 'GET', url: 'task' });
    })

    it("saveTasks$", () =>{
        service.saveTask$(mockedTask).subscribe()
        const req = http.expectOne({ method: 'POST', url: 'task'});
        expect(req.request.body).toEqual(mockedTask);
    })

    it("deleteTasks$", () =>{
        service.deleteTask$(mockedTask).subscribe()
        http.expectOne({ method: 'DELETE', url: `task/${mockedTask.id}` });
    })
});