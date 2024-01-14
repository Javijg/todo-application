import {Inject, Injectable} from '@angular/core';
import { Environment } from '../model/environment';

@Injectable({
    providedIn: 'root'
})
export class HttpService {
    private readonly urlPrefix: string;

    constructor(@Inject('environment') private readonly environment: Environment) {
        const host =  environment.backendUrl;
        this.urlPrefix = host + environment.apiUrl;
    }

    public enhanceURL(url: string): string {
        return this.urlPrefix + url;
    }
}
