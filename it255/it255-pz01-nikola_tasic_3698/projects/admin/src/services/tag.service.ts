import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "../environments/environment";
import { Tag } from "../../../common/src/@types/entity/Tag";

@Injectable({
	providedIn: "root",
})
export class TagService {
	private baseUrl = `${environment.baseUrl}/tags`;

	constructor(private http: HttpClient) {
	}

	public getAll() {
		return this.http.get<Tag[]>(`${this.baseUrl}`).toPromise();
	}

	public save(tag: Tag) {
		return this.http.post<Tag>(`${this.baseUrl}`, tag).toPromise();
	}

	public update(tag: Tag) {
		return this.http.put<Tag>(`${this.baseUrl}`, tag).toPromise();
	}

	public delete(id: number | string) {
		return this.http.delete<void>(`${this.baseUrl}/${id}`).toPromise();
	}
}
