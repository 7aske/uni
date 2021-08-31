import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { JwtService } from "./jwt.service";
import { User } from "../@types/entity/User";
import { environment } from "../environments/environment";

@Injectable({
	providedIn: "root",
})
export class UserInfoService {

	constructor(private http:HttpClient, private jwtService:JwtService) {
	}

	public getByUsername(username: string) {
		return this.http.get<User>(`${environment.baseUrl}/users/${username}`)
			.toPromise();
	}

	public getLoggedInUser(){
		const token = this.jwtService.getToken();
		return this.getByUsername(token.sub);
	}
}
