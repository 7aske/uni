import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "../environments/environment";
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

	public getById(id: number|string) {
		return this.http.get<Post>(`${this.baseUrl}/${id}`).toPromise();
	}

	public save(post: Post) {
		return this.http.post<Post>(`${this.baseUrl}`, post).toPromise();
	}

	public update(post: Post) {
		return this.http.put<Post>(`${this.baseUrl}`, post).toPromise();
	}

	public delete(id: number) {
		return this.http.delete<void>(`${this.baseUrl}/${id}`).toPromise();
	}
}
