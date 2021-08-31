import { NgModule } from "@angular/core";
import { NavbarComponent } from "./components/navbar/navbar.component";
import { ScrollToFloatComponent } from "./components/scroll-to-float/scroll-to-float.component";
import { ScrollToModule } from "@nicky-lenaers/ngx-scroll-to";
import { BrowserModule } from "@angular/platform-browser";
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { ToDatePipe } from "./pipes/to-date.pipe";
import { ConfirmDialogComponent } from "./components/confirm-dialog/confirm-dialog.component";
import { AuthInterceptor } from "./services/interceptors/auth.interceptor";
import { CookieService } from "ngx-cookie-service";

@NgModule({
	imports: [
		ScrollToModule,
		BrowserModule,
		HttpClientModule,
	],
	exports: [
		NavbarComponent,
		ScrollToFloatComponent,
		ToDatePipe,
		ConfirmDialogComponent,
	],
	declarations: [
		NavbarComponent,
		ScrollToFloatComponent,
		ToDatePipe,
		ConfirmDialogComponent,
	],
	providers: [
		CookieService,
		{provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
	],
	bootstrap: [],
})
export class CommonModule {
}
