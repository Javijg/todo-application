import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { TaskComponent } from './component/task/task.component';
import { TaskOverviewComponent } from './component/task-overview/task-overview.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { EnvironmentHttpInterceptor } from './interceptor/environment.interceptor';
import { environment } from 'src/environments/environment';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SvgIconComponent } from './component/svg-icon/svg-icon.component';

@NgModule({
  declarations: [
    AppComponent,
    TaskOverviewComponent,
    TaskComponent,
    SvgIconComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    CommonModule
  ],
  providers: [
    {provide: 'environment', useValue: environment},
    { provide: HTTP_INTERCEPTORS, useClass: EnvironmentHttpInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
