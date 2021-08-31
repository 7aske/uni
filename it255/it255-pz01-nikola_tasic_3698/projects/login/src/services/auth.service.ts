import { Injectable } from "@angular/core";
import { environment } from "../environments/environment";
import { HttpClient } from "@angular/common/http";
import { LoginResponse } from "../../../common/src/@types/LoginResponse";

@Injectable({
	providedIn: "root",
})
export class AuthService {
	private baseUrl = `${environment.baseUrl}`;

	constructor(private http: HttpClient) {
	}

	public login(credentials: { username: string, password: string }) {
		return this.http.post<LoginResponse>(`${this.baseUrl}/login`, credentials).toPromise();
	}

	public validate(token: string) {
		return this.http.post<void>(`${this.baseUrl}/auth/validate`, {}, {
			headers: {
				"Authorization": `Bearer ${token}`,
			},
		}).toPromise();
	}
}
