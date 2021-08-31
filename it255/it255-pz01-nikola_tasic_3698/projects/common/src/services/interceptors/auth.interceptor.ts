import { Injectable } from "@angular/core";
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from "@angular/common/http";
import { Observable } from "rxjs";
import { CookieService } from "ngx-cookie-service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

	constructor(private cookieService: CookieService) {
	}

	intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
		request = request.clone({
			setHeaders: {
				Authorization: `Bearer ${this.cookieService.get("token")}`,
			},
		});
		return next.handle(request);
	}
}
