import { TestBed } from "@angular/core/testing";
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TaskCategoryService } from "./task-category.service";

describe('TaskCategoryService', () => {
    let service: TaskCategoryService;
    let http: HttpTestingController;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [HttpClientTestingModule],
        });
        service = TestBed.inject(TaskCategoryService);
        http = TestBed.inject(HttpTestingController);
    });

    afterEach(() => {
        http.verify();
    });

    it("getTasksCategories$", () =>{
        service.getTasksCategories$().subscribe()
        http.expectOne({ method: 'GET', url: 'task-category' });
    })
});