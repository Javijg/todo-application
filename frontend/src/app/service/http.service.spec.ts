import { TestBed } from "@angular/core/testing";
import { HttpService } from "./http.service";

describe('HttpService', () => {
    let service: HttpService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [],
            providers: [
                { provide: 'environment', useValue: {backendUrl: "http://localhost:8080/", apiUrl:"api/"} },
            ],
        });
        service = TestBed.inject(HttpService);
    });

    it('should enhance url', () => {
        expect(service.enhanceURL("url")).toBe("http://localhost:8080/api/url");
    });
});