import { Inject, Injectable } from "@angular/core";
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from "rxjs";
import { HttpService } from "../service/http.service";

@Injectable()
export class EnvironmentHttpInterceptor implements HttpInterceptor {
    constructor( private readonly httpService: HttpService) {}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const newRequest = req.clone({
            url:
                !req.url.startsWith('http')
                    ? this.httpService.enhanceURL(req.url)
                    : req.url
        });

        return next.handle(newRequest);
    }
}