import { Injectable } from "@angular/core";
import { environment } from "../environments/environment";
import { HttpClient } from "@angular/common/http";
import { Post } from "../../../common/src/@types/entity/Post";

@Injectable({
	providedIn: "root",
})
export class PostService {
	private baseUrl = `${environment.baseUrl}/posts`;

	constructor(private http: HttpClient) {
	}

	public getAll() {
		return this.http.get<Post[]>(`${this.baseUrl}`).toPromise();
	}

	public getById(id: number) {
		return this.http.get<Post>(`${this.baseUrl}/${id}`).toPromise();
	}

	public getBySlug(slug: string) {
		return this.http.get<Post>(`${this.baseUrl}/${slug}`).toPromise();
	}
}
