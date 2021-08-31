import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

import { LoginComponent } from "./login.component";
import { CommonModule } from "../../../common/src/common-module";
import { ReactiveFormsModule } from "@angular/forms";
import { LoginFormComponent } from "./login-form/login-form.component";
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { CookieService } from "ngx-cookie-service";
import { LoginRoutingModule } from "./login-routing.module";
import { ToastrModule } from "ngx-toastr";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { AuthInterceptor } from "../../../common/src/services/interceptors/auth.interceptor";

@NgModule({
	declarations: [
		LoginComponent,
		LoginComponent,
		LoginFormComponent,
	],
	imports: [
		BrowserModule,
		CommonModule,
		ReactiveFormsModule,
		BrowserAnimationsModule,
		HttpClientModule,
		LoginRoutingModule,
		ToastrModule.forRoot({
			timeOut: 5000,
			maxOpened: 5,
			preventDuplicates: true,
			enableHtml: true,
			positionClass: "toast-bottom-center",
		}),
	],
	providers: [
		CookieService,
		{provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
	],
	bootstrap: [LoginComponent],
})
export class LoginModule {
}
